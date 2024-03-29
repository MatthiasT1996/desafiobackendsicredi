package com.desafiosicredi.SpringBootRestApp.exceptions;

import com.desafiosicredi.SpringBootRestApp.Response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleAnyExceptions(Exception exception, WebRequest request){
        String errorDescription = exception.getLocalizedMessage();
        if(errorDescription == null){
            errorDescription = exception.toString();
        }
        ErrorMessage errorMessage = new ErrorMessage(new Date(), errorDescription);
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
