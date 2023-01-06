package com.example.reza.mysecurityjwtdemo.payload.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignupRequest {

   @NotNull(message = "username can not be null")
   private String username;

   @NotNull(message = "password can not be null")
   @Size(min = 4, message = "password must be greater than 4 characters")
   private String password;

   @NotNull(message = "email can not be null")
   private String email;

}
