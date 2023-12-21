package com.dev.GymForDevelopers.controllers;

import com.dev.GymForDevelopers.config.SwaggerConfig;
import com.dev.GymForDevelopers.converters.GdQuestionConverter;
import com.dev.GymForDevelopers.models.DTO.GdQuestionDTO;
import com.dev.GymForDevelopers.models.entity.GdQuestion;
import com.dev.GymForDevelopers.services.GdQuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = SwaggerConfig.QUESTION_TAG)
@RestController
@RequestMapping("/question")
public class GdQuestionController {
    private final GdQuestionService gdQuestionService;
    private final GdQuestionConverter gdQuestionConverter;

    @Autowired
    public GdQuestionController(GdQuestionService gdQuestionService, GdQuestionConverter gdQuestionConverter) {
        this.gdQuestionService = gdQuestionService;
        this.gdQuestionConverter = gdQuestionConverter;
    }

    @ApiOperation("Метод для создания вопроса")
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody GdQuestionDTO gdQuestionDTO) {
        gdQuestionService.save(gdQuestionConverter.convertToEntity(gdQuestionDTO));
        return ResponseEntity.ok("Вопрос успешно создан");
    }

    @ApiOperation("Метод для получения случайных вопросов")
    @GetMapping("/random/{count}")
    public List<String> getRandom(@PathVariable("count") Integer count) {
        return gdQuestionService.getRandomQuestion(count);
    }

    @ApiOperation("Метод для получения случейных вопрос из конкретного раздела")
    @GetMapping("/random/{section}/{count}")
    public List<String> getRandomQuestionBySection(@PathVariable("section") String section,
                                                   @PathVariable("count") Integer count) {
        return gdQuestionService.getRandomBySection(section, count);
    }
}
