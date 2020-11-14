package com.novko.api.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandlerDataIntegrityViolation {

    @ResponseStatus(value=HttpStatus.CONFLICT,
            reason="Data integrity violation")  // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void conflict() {}

    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public ResponseEntity<String> databaseError() {
        return new ResponseEntity<>("Database error", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleError(HttpServletRequest req, Exception ex) {
        return new ResponseEntity<>("Database error", HttpStatus.CONFLICT);
    }

}
