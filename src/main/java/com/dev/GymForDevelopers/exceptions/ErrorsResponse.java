package com.dev.GymForDevelopers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder(toBuilder = true)
@AllArgsConstructor
@Data
public class ErrorsResponse implements ExceptionConst {

        String errors;
        String message;

    // private String errorsCode;
    // private String message;


}
