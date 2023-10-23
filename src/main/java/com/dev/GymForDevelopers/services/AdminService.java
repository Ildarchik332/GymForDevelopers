package com.dev.GymForDevelopers.services;

import com.dev.GymForDevelopers.models.DTO.AdminDTO;
import com.dev.GymForDevelopers.models.entity.Admin;
import com.dev.GymForDevelopers.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public void save(AdminDTO adminDTO) {
        if (adminDTO == null) {
            throw new RuntimeException("В качестве параметра был передан null");
        }
        adminRepository.save(
                Admin.builder()
                        .name(adminDTO.getName())
                        .age(adminDTO.getAge())
                        .birthDate(adminDTO.getBirthDate())
                        .email(adminDTO.getEmail())
                        .phoneNumber(adminDTO.getPhoneNumber())
                        .build());
    }
}
