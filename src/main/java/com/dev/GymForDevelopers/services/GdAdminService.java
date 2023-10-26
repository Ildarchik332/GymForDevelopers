package com.dev.GymForDevelopers.services;

import com.dev.GymForDevelopers.exceptions.GdNotFoundException;
import com.dev.GymForDevelopers.models.entity.GdAdmin;
import com.dev.GymForDevelopers.repositories.GdAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Сервис для работы с администратором
 *
 * @author Ildar
 */
@Service
public class GdAdminService {
    private final GdAdminRepository adminRepository;

    @Autowired
    public GdAdminService(GdAdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    /**
     * Метод для создания администратора
     *
     * @param admin Данные администратора
     */
    public void save(GdAdmin admin) {
        if (admin.getName() == null) {
            throw new RuntimeException("В качестве параметра был передан null");
        }

     //  if (admin == null) {
     //      throw new GdRuntimeException();
     //  }

        adminRepository.save(
                GdAdmin.builder()
                        .name(admin.getName())
                        .age(admin.getAge())
                        .birthDate(admin.getBirthDate())
                        .email(admin.getEmail())
                        .phoneNumber(admin.getPhoneNumber())
                        .build());
    }

    public GdAdmin findByName(String name){
        Optional<GdAdmin> foundAdmin = adminRepository.findByName(name);

        return foundAdmin.orElseThrow(GdNotFoundException::new);

      // return adminRepository.findByName(name);
      //  return foundPerson.orElse(null);
    }
}
