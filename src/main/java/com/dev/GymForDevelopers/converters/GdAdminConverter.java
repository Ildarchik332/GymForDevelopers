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
    public GdAdmin convertToEntity(GdAdminDTO gdAdminDTO) {
        return GdAdmin.builder()
                .name(gdAdminDTO.getName())
                .age(gdAdminDTO.getAge())
                .birthDate(gdAdminDTO.getBirthDate())
                .email(gdAdminDTO.getEmail())
                .birthDate(gdAdminDTO.getBirthDate())
                .phoneNumber(gdAdminDTO.getPhoneNumber())
                .build();
    }

    /**
     * из Entity в DTO
     */
    public GdAdminDTO convertToDTO(GdAdmin gdAdmin) {
        return GdAdminDTO.builder()
                .name(gdAdmin.getName())
                .age(gdAdmin.getAge())
                .birthDate(gdAdmin.getBirthDate())
                .email(gdAdmin.getEmail())
                .phoneNumber(gdAdmin.getPhoneNumber())
                .build();
    }

}
