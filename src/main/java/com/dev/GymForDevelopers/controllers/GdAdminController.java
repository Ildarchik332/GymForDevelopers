package com.dev.GymForDevelopers.controllers;

import com.dev.GymForDevelopers.converters.GdAdminConverter;
import com.dev.GymForDevelopers.models.DTO.GdAdminDTO;
import com.dev.GymForDevelopers.models.entity.GdAdmin;
import com.dev.GymForDevelopers.services.GdAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class GdAdminController {
    private final GdAdminService adminService;
    private final GdAdminConverter gdAdminConverter;

    @Autowired
    public GdAdminController(GdAdminService adminService, GdAdminConverter gdAdminConverter) {
        this.adminService = adminService;
        this.gdAdminConverter = gdAdminConverter;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody GdAdminDTO adminDTO) {
        adminService.save(gdAdminConverter.convertToEntity(adminDTO));
        return ResponseEntity.ok("Админ успешно создан");
    }

    @GetMapping("/{name}")
    public GdAdmin getAdmin(@PathVariable("name") String name) {
        return adminService.findByName(name);
    }


}
