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
    public GdNote convertToEntity(GdNoteDTO noteDTO) {
        return GdNote.newBuilder()
                .advice(noteDTO.getAdvice())
                .whoCreated(noteDTO.getWhoCreated())
                .section(noteDTO.getSection())
                .dateOfCreation(LocalDate.now())
                .build();
    }

    /**
     * из Entity в DTO
     */
    public GdNoteDTO convertToDTO(GdNote note) {
        return GdNoteDTO.newBuilderDTO()
                .dataOfCreation(LocalDate.now())
                .advice(note.getAdvice())
                .section(note.getSection())
                .whoCreated(note.getWhoCreated())
                .build();
    }

}
