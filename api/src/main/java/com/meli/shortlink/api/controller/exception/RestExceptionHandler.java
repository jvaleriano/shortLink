package com.meli.shortlink.api.controller.exception;

import com.meli.shortlink.api.dto.ApiError;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<? extends ApiError> handleBadRequestException(
            BadRequestException ex) {
        return ResponseEntity.badRequest().body(new ApiError(400l, ex.getMessage()));
    }
}