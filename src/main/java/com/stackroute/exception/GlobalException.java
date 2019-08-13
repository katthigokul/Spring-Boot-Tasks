package com.stackroute.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    //Handles TTrackAlreadyExistsException, TrackNotFoundException.class

    @ExceptionHandler(value
            = {TrackAlreadyExistsException.class, TrackNotFoundException.class})
    protected ResponseEntity<Object> handleConflict(
            Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    //Handles Internal_Server_Error i.e if database connection fails
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> databaseConnectionFailsException(Exception exception) {
        return new ResponseEntity<>("Database connectivity is lost", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
