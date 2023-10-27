package com.dev.GymForDevelopers.controllers;

import com.dev.GymForDevelopers.converters.GdConverterPerson;
import com.dev.GymForDevelopers.models.DTO.GdPersonDTO;
import com.dev.GymForDevelopers.models.entity.GdPerson;
import com.dev.GymForDevelopers.services.GdPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public GdPerson getPerson(@PathVariable("id") long id) {
        return gdPersonService.findOne(id);
    }

}
