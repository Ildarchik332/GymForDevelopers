package com.dev.GymForDevelopers.controllers;

import com.dev.GymForDevelopers.config.SwaggerConfig;
import com.dev.GymForDevelopers.converters.GdNoteConverter;
import com.dev.GymForDevelopers.models.DTO.GdNoteDTO;
import com.dev.GymForDevelopers.models.entity.GdNote;
import com.dev.GymForDevelopers.services.GdNoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = SwaggerConfig.NOTE_TAG)
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

    @ApiOperation("Метод для создания заметки")
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody GdNoteDTO gdNoteDTO) {
        gdNoteService.save(gdConverterNote.convertToEntity(gdNoteDTO));
        return ResponseEntity.ok("Заметка успешно создана");
    }

    @ApiOperation("Метод для просмотра всех заметок")
    @GetMapping()
    public List<GdNote> getNotes() {
        return gdNoteService.findAll();
    }

    @ApiOperation("Метод для поиска заметок со статусом IN_REVIEW")
    @GetMapping("/statusReview")
    public List<GdNote> ReviewStatus() {
        return gdNoteService.findAllReviewStatus();
    }

    @ApiOperation("Метод для поиска заметки по индентификатору")
    @GetMapping("/{id}")
    public GdNote getNote(@PathVariable("id") Long id) {
        return gdNoteService.findOne(id);
    }

    @ApiOperation("Метод для принятия заметки")
    @GetMapping("/accept/{id}")
    public void accept(@PathVariable("id") Long id) {
        gdNoteService.acceptNote(id);
    }

    @ApiOperation("Метод для отклонения заметки")
    @GetMapping("/reject/{id}")
    public void reject(@PathVariable("id") Long id) {
        gdNoteService.rejectNote(id);
    }

    @ApiOperation("Метод удаления заметки (из Note)")
    @PostMapping("/deleteFromNote/{id}")
    public void deleteFromNote(@PathVariable("id") Long id) {
        gdNoteService.deleteFromNoteToHistory(id);
    }

    @ApiOperation("Метод для востановления заметки (из Note_History)")
    @PostMapping("/recoveredNote/{id}")
    public void recovered(@PathVariable("id") Long id) {
        gdNoteService.recoveredNote(id);
    }


    @ApiOperation("Метод для удаления заметок")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        gdNoteService.delete(id);
        return ResponseEntity.ok("Заметка удалена");
    }

    @ApiOperation("Метод для создания заметки из JSON'а")
    @PostMapping("/create/json")
    public ResponseEntity<String> createJson(@RequestBody String noteJson) {
        gdNoteService.save(gdConverterNote.convertToEntityFromString(noteJson));
        return ResponseEntity.ok("Заметка успешно создана");
    }

    @ApiOperation("Метод для проставления лайка для заметки")
    @PostMapping("/like/{id}")
    public ResponseEntity<Long> like(@PathVariable("id") Long id) {
        Long actualLike = gdNoteService.like(id);
        return ResponseEntity.ok(actualLike);
    }

    @ApiOperation("Метод для проставления дизлайка для заметки")
    @PostMapping("/dislike/{id}")
    public ResponseEntity<Long> dislikes(@PathVariable("id") Long id) {
        Long actualDislike = gdNoteService.dislike(id);
        return ResponseEntity.ok(actualDislike);
    }

}
