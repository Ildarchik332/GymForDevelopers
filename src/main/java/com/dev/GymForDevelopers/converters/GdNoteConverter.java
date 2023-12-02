package com.dev.GymForDevelopers.converters;

import com.dev.GymForDevelopers.exceptions.ExceptionConst;
import com.dev.GymForDevelopers.exceptions.GdRuntimeException;
import com.dev.GymForDevelopers.models.DTO.GdNoteDTO;
import com.dev.GymForDevelopers.models.entity.GdNote;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * Сервис для конвертации Entity в DTO и наоборот(Заметка)
 *
 * @author Ildar
 */
@Service
public class GdNoteConverter {
    /**
     * Из DTO в Entity
     */
    public GdNote convertToEntity(GdNoteDTO gdNoteDTO) {
        return GdNote.newBuilder()
                .advice(gdNoteDTO.getAdvice())
                .whoCreated(gdNoteDTO.getWhoCreated())
                .section(gdNoteDTO.getSection())
                .status(gdNoteDTO.getStatus())
                .build();
    }

    /**
     * из Entity в DTO
     */
    public GdNoteDTO convertToDTO(GdNote gdNote) {
        return GdNoteDTO.newBuilderDTO()
                .advice(gdNote.getAdvice())
                .section(gdNote.getSection())
                .whoCreated(gdNote.getWhoCreated())
                .dataOfCreation(gdNote.getDateOfCreation())
                .build();
    }

    /**
     * из String в Entity
     */
    public GdNote convertToEntityFromString(String gdNote) {
        JSONObject jsonObject = new JSONObject(gdNote);

        String advice;
        String section;

        if (jsonObject.isNull("section")) {
            throw new GdRuntimeException(ExceptionConst.MESSAGE_RT, ExceptionConst.ERRORS_CODE_RT);
        }
        if (jsonObject.has("section")) {
            section = jsonObject.getString("section");
        } else {
            throw new GdRuntimeException("В качестве ключа был передан null", ExceptionConst.ERRORS_CODE_RT);
        }

        if (jsonObject.isNull("advice")) {
            throw new GdRuntimeException(ExceptionConst.MESSAGE_RT, ExceptionConst.ERRORS_CODE_RT);
        }
        if (jsonObject.has("advice")) {
            advice = jsonObject.getString("advice");
        } else {
            throw new GdRuntimeException("В качестве ключа был передан null", ExceptionConst.ERRORS_CODE_RT);
        }

        return GdNote.newBuilder()
                .section(section)
                .advice(advice)
                .build();
    }

}
