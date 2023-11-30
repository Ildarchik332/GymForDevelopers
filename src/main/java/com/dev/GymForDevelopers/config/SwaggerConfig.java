package com.dev.GymForDevelopers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String PERSON_TAG = "Контроллер для работы с пользователями";
    public static final String ADMIN_TAG = "Контроллер для работы с админами";
    public static final String NOTE_TAG = "Контроллер для работы с заметками";
    public static final String QUESTION_TAG = "Контроллер для работы с вопросами";
    public static final String ANSWER_TAG = "Контроллер для работы с ответами";
    public static final String COMMENT_TAG = "Контроллер для работы с комментариями";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    public ApiInfo apiInfo() {
        return new ApiInfo("Gym For Developers",
                "Some custom description of API",
                "Spring Boot - 2.7.7",
                "https://github.com/Ildarchik332/GymForDevelopers",
                new Contact("Ildar Ainetdinov", "live:.cid.f25a1ae711d2391a", "kitpes1230@yandex.ru"),
                "License of API", "https://swagger.io/docs/",
                Collections.emptyList());
    }
}
