package com.example.reza.mysecurityjwtdemo.controller;

import com.example.reza.mysecurityjwtdemo.payload.dto.request.RefreshTokenRequest;
import com.example.reza.mysecurityjwtdemo.payload.dto.request.SigninRequest;
import com.example.reza.mysecurityjwtdemo.payload.dto.request.SignupRequest;
import com.example.reza.mysecurityjwtdemo.payload.dto.response.SigninResponse;
import com.example.reza.mysecurityjwtdemo.payload.dto.response.SignupResponse;
import com.example.reza.mysecurityjwtdemo.service.RefreshTokenService;
import com.example.reza.mysecurityjwtdemo.service.SigninService;
import com.example.reza.mysecurityjwtdemo.service.SignupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  @Autowired
  private SignupService signupService;

  @Autowired
  private SigninService signinService;

  @Autowired
  private RefreshTokenService refreshTokenService;

  @PostMapping("/signup")
  public ResponseEntity<SignupResponse> signup(@Valid @RequestBody SignupRequest signupRequest) {
    SignupResponse response = signupService.signup(signupRequest).orElseThrow(() -> new RuntimeException("a problem accured"));
    return ResponseEntity.ok(response);
  }

  @PostMapping("/signin")
  public ResponseEntity<SigninResponse> signin(@Valid @RequestBody SigninRequest singinRequest) {

    SigninResponse response = signinService.signin(singinRequest).get();

    return ResponseEntity.ok(response);
  }

  @PostMapping("/refresh-token")
  public ResponseEntity<SigninResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
    SigninResponse response = refreshTokenService.updateAccessToken(refreshTokenRequest.getRefreshToken());
    return ResponseEntity.ok(response);
  }

}
