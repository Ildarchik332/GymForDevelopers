package com.dev.GymForDevelopers.controllers;

import com.dev.GymForDevelopers.config.SwaggerConfig;
import com.dev.GymForDevelopers.converters.GdPersonConverter;
import com.dev.GymForDevelopers.models.DTO.GdPersonDTO;
import com.dev.GymForDevelopers.models.entity.GdPerson;
import com.dev.GymForDevelopers.services.GdPersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = SwaggerConfig.PERSON_TAG)
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

    @ApiOperation("Метод для создания пользователя")
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody GdPersonDTO gdPersonDTO) {
        gdPersonService.save(gdPersonConverter.convertToEntity(gdPersonDTO));

        return ResponseEntity.ok("Пользователь успешно создан");
    }

    @ApiOperation("Метод для поиска пользователя по индентификатору")
    @GetMapping("/{id}")
    public GdPerson getPerson(@PathVariable("id") Long id) {
        return gdPersonService.findOne(id);
    }

    @ApiOperation("Метод для сохранения/обновления дополнительной информации о пользователе")
    @PostMapping("/extraInfo/save/{id}")
    public ResponseEntity<String> saveExtraInfo(@PathVariable("id") Long id, @RequestBody GdPerson.ExtraInfo extraInfo) {
        gdPersonService.saveInfo(id, extraInfo);
        return ResponseEntity.ok("Дополнительня информация успешно сохраненна");
    }

    @ApiOperation("Метод для поиска дополнительной информации о пользователе по его индентификатору")
    @GetMapping("/extraInfo/{id}")
    public ResponseEntity<GdPerson.ExtraInfo> findExtraInfoById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(gdPersonService.findExtraInfo(id));
    }
}
