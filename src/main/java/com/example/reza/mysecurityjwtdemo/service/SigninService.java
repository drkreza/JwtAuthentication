package com.example.reza.mysecurityjwtdemo.service;

import com.example.reza.mysecurityjwtdemo.payload.dto.request.SigninRequest;
import com.example.reza.mysecurityjwtdemo.payload.dto.response.SigninResponse;

import java.util.Optional;


public interface SigninService {

  Optional<SigninResponse> signin(SigninRequest signinRequest);

}
