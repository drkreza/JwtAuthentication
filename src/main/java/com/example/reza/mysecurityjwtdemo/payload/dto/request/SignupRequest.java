package com.example.reza.mysecurityjwtdemo.payload.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignupRequest {

   private String username;

   private String password;

   private String email;

}
