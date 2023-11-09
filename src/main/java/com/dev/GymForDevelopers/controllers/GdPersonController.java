package com.dev.GymForDevelopers.controllers;

import com.dev.GymForDevelopers.converters.GdPersonConverter;
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
    private final GdPersonConverter gdPersonConverter;

    @Autowired
    public GdPersonController(GdPersonService gdPersonService, GdPersonConverter gdPersonConverter) {
        this.gdPersonService = gdPersonService;
        this.gdPersonConverter = gdPersonConverter;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody GdPersonDTO gdPersonDTO) {
        gdPersonService.save(gdPersonConverter.convertToEntity(gdPersonDTO));

        return ResponseEntity.ok("Пользователь успешно создан");
    }

    @GetMapping("/{id}")
    public GdPerson getPerson(@PathVariable("id") Long id) {
        return gdPersonService.findOne(id);
    }

}
