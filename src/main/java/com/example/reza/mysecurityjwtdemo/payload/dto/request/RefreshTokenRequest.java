package com.example.reza.mysecurityjwtdemo.payload.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class RefreshTokenRequest {

   @JsonProperty("refresh_token")
   private String refreshToken;

}
