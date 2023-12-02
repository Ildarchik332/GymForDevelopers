package com.dev.GymForDevelopers.services;

import com.dev.GymForDevelopers.enums.StatusEnum;
import com.dev.GymForDevelopers.exceptions.ExceptionConst;
import com.dev.GymForDevelopers.exceptions.GdNotFoundException;
import com.dev.GymForDevelopers.exceptions.GdRuntimeException;
import com.dev.GymForDevelopers.models.entity.GdNote;
import com.dev.GymForDevelopers.models.entity.GdPerson;
import com.dev.GymForDevelopers.repositories.GdNoteHistoryRepository;
import com.dev.GymForDevelopers.repositories.GdNoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Value("${spring.datasource.url}")
    private String URL;

    @Value("${spring.datasource.username}")
    private String USERNAME;

    @Value("${spring.datasource.password}")
    private String PASSWORD;

    @Value("${note.number}")
    private String numberNote;

    @Value("${Gd.mail.username}")
    private String email;

    private final static String SUBJECT = "Статус заметки изменен!";
    private final static String TEXT = "! Статус заметки был изменен. Присвоен статус: ";

    //todo Сдеалть полноценный доступ к полям user'а (email, name...) после настройки Security + также написать свою почту сервиса
    private JavaMailSender mailSender;

    @Autowired(required = false)
    public void setMailSender(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

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

        DateFormat dateFormat = new SimpleDateFormat(numberNote);
        String number;

        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT nextval('gd_note_number_sequence')");

            if (resultSet.next()) {
                number = dateFormat
                        .format(new Date()) + StringUtils.leftPad(String
                        .valueOf(resultSet.getLong("nextval")), 4, "0");
                gdNote.setNumber(number);
            }
            connection.close();
        } catch (SQLException e) {
            log.error("Не удалось сохранить заметку {}", e.getMessage());
            throw new GdRuntimeException("Не удалось сохранить заметку", "note.save.failed");
        }

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
        GdPerson user = new GdPerson();
        try {
            gdNoteRepository.saveNewStatus(id, StatusEnum.ACCEPTED.getCode());
        } catch (Exception e) {
            log.error("Ошибка в ходе принятия заметки с идентификатором {} : {}", id, e.getMessage());
            throw new GdRuntimeException("Ошибка в ходе принятия заметки с идентификатором", "note.accept.failed");
        }
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(email);
        message.setTo("andrew_1375@mail.ru");
        message.setSubject(SUBJECT);
        message.setText(user.getName() + TEXT + StatusEnum.ACCEPTED.getDescription());

        mailSender.send(message);

    }

    /**
     * Метод для отклонения заметки
     *
     * @param id
     */
    public void rejectNote(Integer id) {
        GdPerson user = new GdPerson();

        try {
            gdNoteRepository.saveNewStatus(id, StatusEnum.REJECTED.getCode());
        } catch (Exception e) {
            log.error("Ошибка в ходе отклонения заметки с идентификатором {} : {}", id, e.getMessage());
            throw new GdRuntimeException("Ошибка в ходе отклонения заметки с идентификатором", "note.reject.failed");
        }
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(email);
        message.setTo(user.getEmail());
        message.setSubject(SUBJECT);
        message.setText(user.getName() + TEXT + StatusEnum.REJECTED.getDescription());

        mailSender.send(message);

    }

    /**
     * Метод удаления заметки (из Note)
     *
     * @param id
     */
    public void deleteFromNoteToHistory(Integer id) {
        GdPerson user = new GdPerson();
        try {
            gdNoteHistoryRepository.save(id, StatusEnum.DELETED.getCode());
        } catch (Exception e) {
            log.error("Ошибка в ходе удаления заметки с идентификатором {} : {}", id, e.getMessage());
            throw new GdRuntimeException("Ошибка в ходе удаления заметки с идентификатором", "note.delete.failed");
        }

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(email);
        message.setTo(user.getEmail());
        message.setSubject(SUBJECT);
        message.setText(user.getName() + TEXT + StatusEnum.DELETED.getDescription());

        mailSender.send(message);
    }

    /**
     * Метод для востановления заметки (из Note_History)
     *
     * @param id
     */
    public void recoveredNote(Integer id) {
        GdPerson user = new GdPerson();
        try {
            gdNoteRepository.save(id, StatusEnum.RECOVERED.getCode());
        } catch (Exception e) {
            log.error("Ошибка в ходе востановления заметки с идентификатором {} : {}", id, e.getMessage());
            throw new GdRuntimeException("Ошибка в ходе востановления заметки с идентификатором", "note.recovered.failed");
        }
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(email);
        message.setTo(user.getEmail());
        message.setSubject(SUBJECT);
        message.setText(user.getName() + TEXT + StatusEnum.RECOVERED.getDescription());

        mailSender.send(message);
    }

    /**
     * Метод ежемесячного удаления заметок из архива
     */
    @Scheduled(cron = "@monthly")
    public void deleteInAMonth() {
        try {
            gdNoteHistoryRepository.deleteInAMonth();
        } catch (Exception e) {
            log.error("Ошибка в ходе ежемесячного удаления заметок : {}", e.getMessage());
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
