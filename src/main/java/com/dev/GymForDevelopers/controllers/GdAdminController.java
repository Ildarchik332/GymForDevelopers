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
    private final GdAdminService gdAdminService;
    private final GdAdminConverter gdAdminConverter;

    @Autowired
    public GdAdminController(GdAdminService gdAdminService, GdAdminConverter gdAdminConverter) {
        this.gdAdminService = gdAdminService;
        this.gdAdminConverter = gdAdminConverter;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody GdAdminDTO gdadminDTO) {
        gdAdminService.save(gdAdminConverter.convertToEntity(gdadminDTO));
        return ResponseEntity.ok("Админ успешно создан");
    }

    @GetMapping("/{name}")
    public GdAdmin getAdmin(@PathVariable("name") String name) {
        return gdAdminService.findByName(name);
    }


}
