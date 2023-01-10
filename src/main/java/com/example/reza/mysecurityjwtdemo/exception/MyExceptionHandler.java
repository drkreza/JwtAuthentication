package com.example.reza.mysecurityjwtdemo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleAllExceptions(Exception e, WebRequest request) {

    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage(e.getMessage() + " " + request.getDescription(false));
    errorResponse.setTimestamp(System.currentTimeMillis());
    errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

  }


  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers,
                                                                HttpStatus status,
                                                                WebRequest request) {

    List<String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(x -> x.getDefaultMessage())
            .collect(Collectors.toList());

    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setTimestamp(System.currentTimeMillis());
    errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
    errorResponse.setMessage(errors.toString());


    /*Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", LocalDate.now());
    body.put("status", status.value());

    //Error Fileds
*//*    List<String> collect = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getField()).collect(Collectors.toList());
    for (String s : collect) {
      System.out.println("errorFields : "+s);
    }
    body.put("error_fileds",collect);*//*

    List<String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(x -> x.getDefaultMessage())
            .collect(Collectors.toList());

    body.put("errors", errors);

    for (String s : errors) {
      System.out.println("error : "+s);
    }*/

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

  }

}
