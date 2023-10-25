package com.dev.GymForDevelopers.controllers;

import com.dev.GymForDevelopers.models.DTO.GdAdminDTO;
import com.dev.GymForDevelopers.services.GdAdminService;
import com.dev.GymForDevelopers.converters.GdConverterAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class GdAdminController {
    private final GdAdminService adminService;
    private final GdConverterAdmin gdConverterAdmin;

    @Autowired
    public GdAdminController(GdAdminService adminService, GdConverterAdmin gdConverterAdmin) {
        this.adminService = adminService;
        this.gdConverterAdmin = gdConverterAdmin;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody GdAdminDTO adminDTO) {
        adminService.save(gdConverterAdmin.convertToEntity(adminDTO));
        return ResponseEntity.ok("Админ успешно создан");
    }
}
