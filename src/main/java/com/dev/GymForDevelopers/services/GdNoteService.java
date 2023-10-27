package com.dev.GymForDevelopers.services;

import com.dev.GymForDevelopers.exceptions.ExceptionConst;
import com.dev.GymForDevelopers.exceptions.GdNotFoundException;
import com.dev.GymForDevelopers.exceptions.GdRuntimeException;
import com.dev.GymForDevelopers.models.entity.GdNote;
import com.dev.GymForDevelopers.repositories.GdNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Сервис для работы с заметками
 *
 * @author Ildar
 */
@Service
public class GdNoteService {

    private final GdNoteRepository gdNoteRepository;

    @Autowired
    public GdNoteService(GdNoteRepository gdNoteRepository) {
        this.gdNoteRepository = gdNoteRepository;
    }

    /**
     * Метод для создания заметки
     *
     * @param note Критерии заметки
     */
    public void save(GdNote note) {
        if (note == null) {
            throw new GdRuntimeException(ExceptionConst.MESSAGE_RT, ExceptionConst.ERRORS_CODE_RT);
        }
        gdNoteRepository.save(
                GdNote.builder()
                        .section(note.getSection())
                        .advice(note.getAdvice())
                        .dateOfCreation(LocalDate.now())
                        .whoCreated(note.getWhoCreated())
                        .build()
        );
    }

    public GdNote findOne(int id) {
        return gdNoteRepository.findById(id)
                .orElseThrow(() -> {
                    throw new GdNotFoundException(ExceptionConst.MESSAGE_NF, ExceptionConst.ERRORS_CODE_NF);
                });
    }
}
