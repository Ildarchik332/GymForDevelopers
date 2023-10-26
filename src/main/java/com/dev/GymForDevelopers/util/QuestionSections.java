package com.dev.GymForDevelopers.util;

public enum QuestionSections {
    HIBERNATE, SPRING, JAVA, SQL, PATTERNS;

    private Integer code;
    private String  title;
    private String description;

    QuestionSections(){}

    QuestionSections(Integer code, String title, String description) {
        this.code = code;
        this.title = title;
        this.description = description;
    }
}
