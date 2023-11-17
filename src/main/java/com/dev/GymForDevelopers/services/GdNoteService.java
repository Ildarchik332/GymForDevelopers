package com.dev.GymForDevelopers.services;

import com.dev.GymForDevelopers.enums.StatusEnum;
import com.dev.GymForDevelopers.exceptions.ExceptionConst;
import com.dev.GymForDevelopers.exceptions.GdNotFoundException;
import com.dev.GymForDevelopers.exceptions.GdRuntimeException;
import com.dev.GymForDevelopers.models.entity.GdNote;
import com.dev.GymForDevelopers.repositories.GdNoteHistoryRepository;
import com.dev.GymForDevelopers.repositories.GdNoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для работы с заметками
 *
 * @author Ildar
 */
@Service
@Slf4j
public class GdNoteService {

    private final GdNoteRepository gdNoteRepository;
    private final GdNoteHistoryRepository gdNoteHistoryRepository;

    @Autowired
    public GdNoteService(GdNoteRepository gdNoteRepository, GdNoteHistoryRepository gdNoteHistoryRepository) {
        this.gdNoteRepository = gdNoteRepository;
        this.gdNoteHistoryRepository = gdNoteHistoryRepository;
    }

    /**
     * Метод для создания заметки
     *
     * @param gdNote Критерии заметки
     */
    //todo Как только подключим Security, нужно будет продумать логику статуса заметки. Отталкиваться будем от роли создателя.
    public void save(GdNote gdNote) {
        if (gdNote == null) {
            throw new GdRuntimeException(ExceptionConst.MESSAGE_RT, ExceptionConst.ERRORS_CODE_RT);
        }
        gdNote.setStatus(StatusEnum.IN_REVIEW.getCode());
        gdNoteRepository.save(gdNote);
    }


    /**
     * Метод для поиска заметки по id
     *
     * @param id Идентификатор заметки
     */
    public GdNote findOne(Integer id) {
        return gdNoteRepository.findById(id)
                .orElseThrow(() -> new GdNotFoundException(ExceptionConst.MESSAGE_NF, ExceptionConst.ERRORS_CODE_NF));
    }

    /**
     * Метод для поиска всех заметок
     */
    public List<GdNote> findAll() {
        return gdNoteRepository.findAll();
    }

    /**
     * Метод для поиска заметок со статусом IN_REVIEW
     */
    public List<GdNote> findAllReviewStatus() {
        return gdNoteRepository.findByStatusEquals(StatusEnum.IN_REVIEW.getCode());
    }

    /**
     * Метод для принятия заметки
     *
     * @param id
     */
    public void acceptNote(Integer id) {
        try {
            gdNoteRepository.saveNewStatus(id, StatusEnum.ACCEPTED.getCode());
        } catch (Exception e) {
            log.error("Ошибка в ходе принятия заметки с идентификатором {} : {}", id, e.getMessage());
            throw new GdRuntimeException("Ошибка в ходе принятия заметки с идентификатором", "note.accept.failed");
        }
    }

    /**
     * Метод для отклонения заметки
     *
     * @param id
     */
    public void rejectNote(Integer id) {
        try {
            gdNoteRepository.saveNewStatus(id, StatusEnum.REJECTED.getCode());
        } catch (Exception e) {
            log.error("Ошибка в ходе отклонения заметки с идентификатором {} : {}", id, e.getMessage());
            throw new GdRuntimeException("Ошибка в ходе отклонения заметки с идентификатором", "note.reject.failed");
        }

    }

    /**
     * Метод удаления заметки (из Note)
     *
     * @param id
     */
    public void deleteFromNoteToHistory(Integer id) {
        try {
            gdNoteHistoryRepository.save(id, StatusEnum.DELETED.getCode());
        } catch (Exception e) {
            log.error("Ошибка в ходе удаления заметки с идентификатором {} : {}", id, e.getMessage());
            throw new GdRuntimeException("Ошибка в ходе удаления заметки с идентификатором", "note.delete.failed");
        }
    }

    /**
     * Метод для востановления заметки (из Note_History)
     *
     * @param id
     */
    public void recoveredNote(Integer id) {
        try {
            gdNoteRepository.save(id, StatusEnum.RECOVERED.getCode());
        } catch (Exception e) {
            log.error("Ошибка в ходе востановления заметки с идентификатором {} : {}", id, e.getMessage());
            throw new GdRuntimeException("Ошибка в ходе востановления заметки с идентификатором", "note.recovered.failed");
        }
    }

    /**
     * Метод ежемесячного удаления заметок из архива
     */
    @Scheduled(cron = "@monthly")
    public void deleteInAMonth() {
        try {
            gdNoteHistoryRepository.deleteInAMonth();
        } catch (Exception e) {
            log.error("Ошибка в ходе ежемесячного удаления заметок : {}",  e.getMessage());
            throw new GdRuntimeException("Ошибка в ходе ежемесячного удаления заметок", "note.delete.failed");
        }
    }

    /**
     * Метод для удаления конкретной заметки из БД
     *
     * @param id
     */
    public void delete(Integer id) {
        try {
            gdNoteRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Ошибка в ходе удаления заметки {} : {}", id, e.getMessage());
            throw new GdRuntimeException("Ошибка в ходе удаления заметки", "note.delete.failed");
        }
    }


}
