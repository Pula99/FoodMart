package com.userservice.app.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@JsonIgnoreProperties
public class ErrorResponse {

    private int status;
    private String message;
    private List<String> errors;
}
