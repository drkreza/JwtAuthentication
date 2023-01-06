package com.example.reza.mysecurityjwtdemo.service.impl;

import com.example.reza.mysecurityjwtdemo.config.jwt.JwtUtils;
import com.example.reza.mysecurityjwtdemo.config.security.CustomUserDetails;
import com.example.reza.mysecurityjwtdemo.entity.RefreshTokenEntity;
import com.example.reza.mysecurityjwtdemo.payload.dto.request.SigninRequest;
import com.example.reza.mysecurityjwtdemo.payload.dto.response.SigninResponse;
import com.example.reza.mysecurityjwtdemo.service.RefreshTokenService;
import com.example.reza.mysecurityjwtdemo.service.SigninService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SigninServiceImpl implements SigninService {
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private RefreshTokenService refreshTokenService;
  @Autowired
  private JwtUtils jwtUtils;


  @Override
  public Optional<SigninResponse> signin(SigninRequest signinRequest) {

    UsernamePasswordAuthenticationToken authenticationToken =
      new UsernamePasswordAuthenticationToken(signinRequest.getUsername(),
        signinRequest.getPassword());
    Authentication authenticateObject = authenticationManager.authenticate(authenticationToken);
    SecurityContextHolder.getContext().setAuthentication(authenticateObject);
    CustomUserDetails userDetails = (CustomUserDetails)authenticateObject.getPrincipal();
    System.out.println(userDetails.toString());
    RefreshTokenEntity refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
    String accessToken = jwtUtils.generateTokenFromUsername(userDetails.getUsername());
    SigninResponse response = new SigninResponse();
    response.setRefreshToken(refreshToken.getToken());
    response.setToken(accessToken);
    return Optional.of(response);
  }


}
