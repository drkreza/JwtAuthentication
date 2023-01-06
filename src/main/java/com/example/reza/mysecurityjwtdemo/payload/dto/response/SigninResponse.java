package com.example.reza.mysecurityjwtdemo.payload.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SigninResponse {

   @JsonProperty("access_token")
   private String token;

   @JsonProperty("refresh_token")
   private String refreshToken;
}
