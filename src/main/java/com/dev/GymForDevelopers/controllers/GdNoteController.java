package com.dev.GymForDevelopers.controllers;

import com.dev.GymForDevelopers.models.DTO.GdNoteDTO;
import com.dev.GymForDevelopers.services.GdNoteService;
import com.dev.GymForDevelopers.converters.GdConverterNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/note")
public class GdNoteController {

    private final GdNoteService gdNoteService;
    private final GdConverterNote gdConverterNote;

    @Autowired
    public GdNoteController(GdNoteService gdNoteService, GdConverterNote gdConverterNote) {
        this.gdNoteService = gdNoteService;
        this.gdConverterNote = gdConverterNote;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody GdNoteDTO noteDTO){
        gdNoteService.save(gdConverterNote.convertToEntity(noteDTO));
        return ResponseEntity.ok("Заметка успешно создана");
    }



}
