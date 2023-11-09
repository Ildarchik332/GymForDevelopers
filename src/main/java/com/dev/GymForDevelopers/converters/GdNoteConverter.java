package com.dev.GymForDevelopers.converters;

import com.dev.GymForDevelopers.models.DTO.GdNoteDTO;
import com.dev.GymForDevelopers.models.entity.GdNote;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
                .dateOfCreation(LocalDate.now())
                .build();
    }

    /**
     * из Entity в DTO
     */
    public GdNoteDTO convertToDTO(GdNote gdNote) {
        return GdNoteDTO.newBuilderDTO()
                .dataOfCreation(LocalDate.now())
                .advice(gdNote.getAdvice())
                .section(gdNote.getSection())
                .whoCreated(gdNote.getWhoCreated())
                .build();
    }

}
