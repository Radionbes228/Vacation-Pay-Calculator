package com.radion.vacationpaycalculator.exception;

import com.radion.vacationpaycalculator.exception.model.ExceptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final MessageSource messageSource;

    @ExceptionHandler(FieldException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleFieldException(FieldException e) {
        return ResponseEntity.badRequest().body(response(e.getMessage()));
    }
    @ExceptionHandler(InvalidRangeDateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleFieldException(InvalidRangeDateException e) {
        return ResponseEntity.badRequest().body(response(e.getMessage()));
    }
    @ExceptionHandler(DateFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleDateFormatException(DateFormatException e) {
        return ResponseEntity.badRequest().body(response(e.getMessage()));
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleMissingServletRequestParameterException() {
        return ResponseEntity.badRequest().body(response(messageSource.getMessage("field.error", null, Locale.getDefault())));
    }

    private ExceptionResponse response(String message) {
        var response = new ExceptionResponse();
        response.setError(message);
        return response;
    }
}
