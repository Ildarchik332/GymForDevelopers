package com.dev.GymForDevelopers.converters;

import com.dev.GymForDevelopers.models.DTO.GdNoteDTO;
import com.dev.GymForDevelopers.models.entity.GdNote;
import org.springframework.stereotype.Service;
/**
 * Сервис для конвертации Entity в DTO и наоборот(Заметка)
 *
 * @author Ildar
 */
@Service
public class GdConverterNote {
    /**
     * Из DTO в Entity
     */
    public GdNote convertToEntity(GdNoteDTO noteDTO) {
       return GdNote.builder()
               .advice(noteDTO.getAdvice())
               .whoCreated(noteDTO.getWhoCreated())
               .section(noteDTO.getSection())
               .build();
    }

    /**
     * из Entity в DTO
     */
    public GdNoteDTO convertToDTO(GdNote note){
        return GdNoteDTO.builder()
                .advice(note.getAdvice())
                .section(note.getSection())
                .whoCreated(note.getWhoCreated())
                .build();
    }
}
