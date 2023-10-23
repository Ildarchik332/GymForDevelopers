package com.dev.GymForDevelopers.controllers;

import com.dev.GymForDevelopers.models.DTO.AdminDTO;
import com.dev.GymForDevelopers.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody AdminDTO adminDTO) {
        adminService.save(adminDTO);
        return ResponseEntity.ok("Админ успешно создан");
    }
}
