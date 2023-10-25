package com.dev.GymForDevelopers.controllers;

import com.dev.GymForDevelopers.converters.GdConverterPerson;
import com.dev.GymForDevelopers.models.DTO.GdPersonDTO;
import com.dev.GymForDevelopers.services.GdPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people")
public class GdPersonController {
    private final GdPersonService gdPersonService;
    private final GdConverterPerson gdConverterPerson;

    @Autowired
    public GdPersonController(GdPersonService gdPersonService, GdConverterPerson gdConverterPerson) {
        this.gdPersonService = gdPersonService;
        this.gdConverterPerson = gdConverterPerson;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody GdPersonDTO personDTO) {
        gdPersonService.save(gdConverterPerson.convertToEntity(personDTO));

        return ResponseEntity.ok("Пользователь успешно создан");
    }
    }
