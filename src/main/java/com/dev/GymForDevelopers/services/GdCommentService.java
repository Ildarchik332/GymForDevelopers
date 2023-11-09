package com.dev.GymForDevelopers.services;

import com.dev.GymForDevelopers.exceptions.ExceptionConst;
import com.dev.GymForDevelopers.exceptions.GdRuntimeException;
import com.dev.GymForDevelopers.models.entity.GdComment;
import com.dev.GymForDevelopers.models.entity.GdNote;
import com.dev.GymForDevelopers.repositories.GdCommentRepository;
import com.dev.GymForDevelopers.repositories.GdNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Сервис для работы с комментариями
 *
 * @author Ildar
 */
@Service
public class GdCommentService {
    private final GdCommentRepository gdCommentRepository;
    private final GdNoteRepository gdNoteRepository;

    @Autowired
    public GdCommentService(GdCommentRepository gdCommentRepository, GdNoteRepository gdNoteRepository) {
        this.gdCommentRepository = gdCommentRepository;
        this.gdNoteRepository = gdNoteRepository;
    }

    /**
     * Метод для создания комментария
     *
     * @param noteId    Идентификатор заметки
     * @param gdComment комментарий
     */
    //todo поле author будет браться из токена
    @Transactional
    public GdComment create(Integer noteId, GdComment gdComment) {
        if (gdComment == null) {
            throw new GdRuntimeException("В качестве комментария был передан null", ExceptionConst.ERRORS_CODE_RT);
        }

        GdNote note = gdNoteRepository.findNoteById(noteId);
        if (note == null) {
            throw new GdRuntimeException("В поле noteId был передан null", ExceptionConst.ERRORS_CODE_RT);
        }

        gdComment.setNote(note);
        gdCommentRepository.save(gdComment);

        return gdComment;
    }
}
