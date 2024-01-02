package com.dixonpa.store.users.exceptions;
import com.dixonpa.store.users.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerExceptionsUser {

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<ErrorDTO> userExistHandler(UserExistException userExist){
        ErrorDTO error = ErrorDTO.builder().message(userExist.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
