package com.utn.spring.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handledConstraintViolation(ConstraintViolationException ex, WebRequest request)
    {
        List<String>errors = new ArrayList<>();
        for(ConstraintViolation constraintViolation: ex.getConstraintViolations())
        {
            errors.add(constraintViolation.getRootBeanClass().getName()+""+constraintViolation.getMessage());
        }
        ApiError apiError = new ApiError(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST,errors);
        return new ResponseEntity<Object>(apiError,new HttpHeaders(),apiError.getHttpStatus());
    }
}
