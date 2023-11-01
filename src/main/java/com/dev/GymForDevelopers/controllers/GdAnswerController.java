package com.dev.GymForDevelopers.controllers;

import com.dev.GymForDevelopers.converters.GdAnswerConverter;
import com.dev.GymForDevelopers.models.DTO.GdAnswerDTO;
import com.dev.GymForDevelopers.services.GdAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answer")
public class GdAnswerController {
    private final GdAnswerService gdAnswerService;
    private final GdAnswerConverter gdAnswerConverter;

    @Autowired
    public GdAnswerController(GdAnswerService gdAnswerService, GdAnswerConverter gdAnswerConverter) {
        this.gdAnswerService = gdAnswerService;
        this.gdAnswerConverter = gdAnswerConverter;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody GdAnswerDTO answerDTO) {
        gdAnswerService.save(answerDTO.getQuestionId(), gdAnswerConverter.convertToEntity(answerDTO));
        return ResponseEntity.ok("Ваш ответ успешно добавлен");
    }
}