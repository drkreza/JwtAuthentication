package com.example.reza.mysecurityjwtdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class MyExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleAllExceptions(Exception e, WebRequest request) {

    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage(e.getMessage() + " " + request.getDescription(false));
    errorResponse.setTimestamp(System.currentTimeMillis());
    errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

  }

}
