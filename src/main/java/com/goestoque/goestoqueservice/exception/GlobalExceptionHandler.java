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
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DefaultErrorResponseDTO(extractExceptionName(ex), ex.getMessage()));
    }

    @ExceptionHandler(ItemAlreadyExistsException.class)
    public ResponseEntity<String> handleItemAlreadyExistsException(ItemAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<DefaultErrorResponseDTO> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DefaultErrorResponseDTO(extractExceptionName(ex), ex.getMessage()));
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
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MapErrorResponseDTO(extractExceptionName(ex), errors));
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
        return ResponseEntity.status(status).body(new DefaultErrorResponseDTO(extractExceptionName(ex), message));
    }

    @ExceptionHandler(InputNotFoundException.class)
    public ResponseEntity<DefaultErrorResponseDTO> handleInputNotFoundException(InputNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DefaultErrorResponseDTO(extractExceptionName(ex), ex.getMessage()));
    }

    @ExceptionHandler(InputItemNotFoundException.class)
    public ResponseEntity<DefaultErrorResponseDTO> handleInputItemNotFoundException(InputItemNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DefaultErrorResponseDTO(extractExceptionName(ex), ex.getMessage()));
    }

    @ExceptionHandler(PurchaseNotFoundException.class)
    public ResponseEntity<DefaultErrorResponseDTO> handlePurchaseNotFoundException(PurchaseNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DefaultErrorResponseDTO(extractExceptionName(ex), ex.getMessage()));
    }

    @ExceptionHandler(OutputNotFoundException.class)
    public ResponseEntity<DefaultErrorResponseDTO> handleOutputNotFoundException(OutputNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DefaultErrorResponseDTO(extractExceptionName(ex), ex.getMessage()));
    }

    @ExceptionHandler(SaleNotFoundException.class)
    public ResponseEntity<DefaultErrorResponseDTO> handleSaleNotFoundException(SaleNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DefaultErrorResponseDTO(extractExceptionName(ex), ex.getMessage()));
    }

    private String extractExceptionName(Exception ex) {
        String string = ex.getClass().getName();
        return string.substring(string.lastIndexOf('.') + 1);
    }
}
