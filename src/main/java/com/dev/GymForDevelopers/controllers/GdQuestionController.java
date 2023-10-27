package com.dev.GymForDevelopers.controllers;

import com.dev.GymForDevelopers.converters.GdConvertQuestion;
import com.dev.GymForDevelopers.models.DTO.GdQuestionDTO;
import com.dev.GymForDevelopers.services.GdQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/question")
public class GdQuestionController {
    private final GdQuestionService gdQuestionService;
    private final GdConvertQuestion gdConvertQuestion;

    @Autowired
    public GdQuestionController(GdQuestionService gdQuestionService, GdConvertQuestion gdConvertQuestion) {
        this.gdQuestionService = gdQuestionService;
        this.gdConvertQuestion = gdConvertQuestion;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody GdQuestionDTO questionDTO) {
        gdQuestionService.save(gdConvertQuestion.convertToEntity(questionDTO));
        return ResponseEntity.ok("Вопрос успешно создан");
    }

}
