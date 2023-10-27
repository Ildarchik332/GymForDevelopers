package com.dev.GymForDevelopers.exceptions;

import lombok.Getter;

public class GdRuntimeException extends RuntimeException{

    @Getter
    private final String code;

    public GdRuntimeException(String message, String code) {
        super(message);
        this.code = code;
    }
}
