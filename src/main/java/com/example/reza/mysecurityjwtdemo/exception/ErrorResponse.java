package com.example.reza.mysecurityjwtdemo.exception;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ErrorResponse {

   @JsonProperty("status_code")
   private int statusCode;

   private String message;

   private long timestamp;

}
