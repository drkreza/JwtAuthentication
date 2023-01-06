package com.example.reza.mysecurityjwtdemo.service;

import com.example.reza.mysecurityjwtdemo.payload.dto.request.SignupRequest;
import com.example.reza.mysecurityjwtdemo.payload.dto.response.SignupResponse;

import java.util.Optional;

public interface SignupService {

   Optional<SignupResponse> signup(SignupRequest signupRequest);

}
