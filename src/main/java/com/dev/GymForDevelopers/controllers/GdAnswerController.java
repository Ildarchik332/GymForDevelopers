package com.dev.GymForDevelopers.controllers;

import com.dev.GymForDevelopers.converters.GdConvertAnswer;
import com.dev.GymForDevelopers.models.DTO.GdAnswerDTO;
import com.dev.GymForDevelopers.models.entity.GdAnswer;
import com.dev.GymForDevelopers.services.GdAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answer")
public class GdAnswerController {
    private final GdAnswerService gdAnswerService;
    private final GdConvertAnswer gdConvertAnswer;

    @Autowired
    public GdAnswerController(GdAnswerService gdAnswerService, GdConvertAnswer gdConvertAnswer) {
        this.gdAnswerService = gdAnswerService;
        this.gdConvertAnswer = gdConvertAnswer;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody GdAnswerDTO answerDTO) {
        gdAnswerService.save(gdConvertAnswer.convertToEntity(answerDTO));
        return ResponseEntity.ok("Ваш ответ успешно добавлен");
    }
}
