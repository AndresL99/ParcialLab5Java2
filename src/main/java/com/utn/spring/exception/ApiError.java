package com.utn.spring.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@NoArgsConstructor
public class ApiError
{
    private String message;
    private HttpStatus httpStatus;
    private List<String>errors;

    public ApiError(String message, HttpStatus httpStatus, List<String> errors) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.errors = errors;
    }
}
