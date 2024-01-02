package com.dixonpa.store.products.exceptions;

import com.dixonpa.store.products.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDTO> notFoundExceptionHandler(ProductNotFoundException ex){
        ErrorDTO error = ErrorDTO.builder().message(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(SameProductException.class)
    public ResponseEntity<ErrorDTO> sameExceptionHandler(SameProductException ex){
        ErrorDTO error = ErrorDTO.builder().message(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorDTO>> fieldProductNotValid(MethodArgumentNotValidException ex){
        List<ErrorDTO> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError -> new ErrorDTO(FieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDTO>deserializeException(HttpMessageNotReadableException ex){
        ErrorDTO error = ErrorDTO.builder().message("Only numbers are allowed, check the fields, 'code' and 'price'").build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDTO>stringForPathException(MethodArgumentTypeMismatchException ex){
        ErrorDTO error = ErrorDTO.builder().message("You are trying to enter a word instead of a number").build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
