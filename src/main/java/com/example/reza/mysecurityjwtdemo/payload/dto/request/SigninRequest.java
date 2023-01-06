package com.example.reza.mysecurityjwtdemo.payload.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SigninRequest {

   private String username;

   private String password;

}
