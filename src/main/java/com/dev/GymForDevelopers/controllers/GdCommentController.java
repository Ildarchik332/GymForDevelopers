package com.dev.GymForDevelopers.controllers;

import com.dev.GymForDevelopers.config.SwaggerConfig;
import com.dev.GymForDevelopers.converters.GdCommentConverter;
import com.dev.GymForDevelopers.models.DTO.GdCommentDTO;
import com.dev.GymForDevelopers.models.entity.GdComment;
import com.dev.GymForDevelopers.services.GdCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = SwaggerConfig.COMMENT_TAG)
@RestController
@RequestMapping("/comment")
public class GdCommentController {

    private final GdCommentService gdCommentService;
    private final GdCommentConverter gdCommentConverter;

    @Autowired
    public GdCommentController(GdCommentService gdCommentService, GdCommentConverter gdCommentConverter) {
        this.gdCommentService = gdCommentService;
        this.gdCommentConverter = gdCommentConverter;
    }

    @ApiOperation("Метод для создания комментария")
    @PostMapping("/create")
    public GdComment create(@RequestBody GdCommentDTO gdCommentDTO) {
        return gdCommentService.create(gdCommentDTO.getNoteId(), gdCommentConverter.convertToEntity(gdCommentDTO));
    }

}
