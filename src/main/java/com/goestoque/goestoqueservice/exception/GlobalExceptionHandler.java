package com.goestoque.goestoqueservice.exception;

import com.goestoque.goestoqueservice.exception.dtos.DefaultErrorResponseDTO;
import com.goestoque.goestoqueservice.exception.dtos.MapErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<DefaultErrorResponseDTO> handleItemNotFoundException(ItemNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DefaultErrorResponseDTO(exceptionName(ex), ex.getMessage()));
    }

    @ExceptionHandler(ItemAlreadyExistsException.class)
    public ResponseEntity<String> handleItemAlreadyExistsException(ItemAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<DefaultErrorResponseDTO> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DefaultErrorResponseDTO(exceptionName(ex), ex.getMessage()));
    }

    //trata as constraints de validação de entrada de dados na api
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MapErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((org.springframework.validation.FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MapErrorResponseDTO(exceptionName(ex), errors));
    }

    //trata as exceções de autenticação
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<DefaultErrorResponseDTO> handleBadCredentialsException(AuthenticationException ex){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "There was an error during the authentication process. Please try again.";
        if(ex instanceof BadCredentialsException) {
            status = HttpStatus.UNAUTHORIZED;
            message = "The username or password you entered is incorrect. Please try again.";
        }
        if(ex instanceof DisabledException) {
            status = HttpStatus.FORBIDDEN;
            message = "Your account is not enabled. Please contact support.";
        }
        return ResponseEntity.status(status).body(new DefaultErrorResponseDTO(exceptionName(ex), message));
    }

    private String exceptionName(Exception ex) {
        String string = ex.getClass().getName();
        return string.substring(string.lastIndexOf('.') + 1);
    }
}
/*
public class ValidationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((org.springframework.validation.FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
*/
/*
@ExceptionHandler(ConstraintViolationException.class)
public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
    Map<String, String> errors = new HashMap<>();
    Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
    for (ConstraintViolation<?> violation : violations) {
        String fieldName = violation.getPropertyPath().toString();
        String errorMessage = violation.getMessage();
        errors.put(fieldName, errorMessage);
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
}
*/
