package com.dev.GymForDevelopers.exceptions;

import lombok.Getter;

public class GdNotFoundException extends RuntimeException {

    @Getter
    private final String code;

    public GdNotFoundException(String message,  String code) {
        super(message);
        this.code = code;
    }

}
