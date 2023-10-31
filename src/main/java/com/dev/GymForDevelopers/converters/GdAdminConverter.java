package com.dev.GymForDevelopers.converters;

import com.dev.GymForDevelopers.models.DTO.GdAdminDTO;
import com.dev.GymForDevelopers.models.entity.GdAdmin;
import org.springframework.stereotype.Service;

/**
 * Сервис для конвертации Entity в DTO и наоборот(Администратор)
 *
 * @author Ildar
 */
@Service
public class GdAdminConverter {
    /**
     * Из DTO в Entity
     */
    public GdAdmin convertToEntity(GdAdminDTO dto){
        return GdAdmin.builder()
                .name(dto.getName())
                .age(dto.getAge())
                .birthDate(dto.getBirthDate())
                .email(dto.getEmail())
                .birthDate(dto.getBirthDate())
                .phoneNumber(dto.getPhoneNumber())
                .build();
    }

    /**
     * из Entity в DTO
     */
    public GdAdminDTO convertToDTO(GdAdmin admin){
        return GdAdminDTO.builder()
                .name(admin.getName())
                .age(admin.getAge())
                .birthDate(admin.getBirthDate())
                .email(admin.getEmail())
                .phoneNumber(admin.getPhoneNumber())
                .build();
    }

}
