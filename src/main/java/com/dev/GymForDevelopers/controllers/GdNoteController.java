package com.dev.GymForDevelopers.controllers;

import com.dev.GymForDevelopers.converters.GdNoteConverter;
import com.dev.GymForDevelopers.models.DTO.GdNoteDTO;
import com.dev.GymForDevelopers.models.entity.GdNote;
import com.dev.GymForDevelopers.services.GdNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<String> create(@RequestBody GdNoteDTO gdNoteDTO) {
        gdNoteService.save(gdConverterNote.convertToEntity(gdNoteDTO));
        return ResponseEntity.ok("Заметка успешно создана");
    }

    @GetMapping()
    public List<GdNote> getNotes() {
        return gdNoteService.findAll();
    }

    @GetMapping("/statusReview")
    public List<GdNote> ReviewStatus() {
        return gdNoteService.findAllReviewStatus();
    }

    @GetMapping("/{id}")
    public GdNote getNote(@PathVariable("id") Integer id) {
        return gdNoteService.findOne(id);
    }

    @GetMapping("/accept/{id}")
    public void accept(@PathVariable("id") Integer id) {
        gdNoteService.acceptNote(id);
    }

    @GetMapping("/reject/{id}")
    public void reject(@PathVariable("id") Integer id) {
        gdNoteService.rejectNote(id);
    }

    @PostMapping("/deleteFromNote/{id}")
    public void deleteFromNote(@PathVariable("id") Integer id) {
        gdNoteService.deleteFromNoteToHistory(id);
    }

    @PostMapping("/recoveredNote/{id}")
    public void recovered(@PathVariable("id") Integer id) {
        gdNoteService.recoveredNote(id);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        gdNoteService.delete(id);
        return ResponseEntity.ok("Заметка удалена");
    }


}
