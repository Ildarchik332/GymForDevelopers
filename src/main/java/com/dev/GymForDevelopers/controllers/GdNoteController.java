package com.dev.GymForDevelopers.controllers;

import com.dev.GymForDevelopers.converters.GdNoteConverter;
import com.dev.GymForDevelopers.models.DTO.GdNoteDTO;
import com.dev.GymForDevelopers.models.entity.GdNote;
import com.dev.GymForDevelopers.services.GdNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/note")
public class GdNoteController {

    private final GdNoteService gdNoteService;
    private final GdNoteConverter gdConverterNote;

    @Autowired
    public GdNoteController(GdNoteService gdNoteService, GdNoteConverter gdConverterNote) {
        this.gdNoteService = gdNoteService;
        this.gdConverterNote = gdConverterNote;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody GdNoteDTO noteDTO) {
        gdNoteService.save(gdConverterNote.convertToEntity(noteDTO));
        return ResponseEntity.ok("Заметка успешно создана");
    }

    @GetMapping("/{id}")
    public GdNote getNote(@PathVariable("id") int id) {
        return gdNoteService.findOne(id);
    }


}
