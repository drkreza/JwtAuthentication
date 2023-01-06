package com.example.reza.mysecurityjwtdemo.service;

import com.example.reza.mysecurityjwtdemo.entity.RefreshTokenEntity;
import com.example.reza.mysecurityjwtdemo.payload.dto.response.SigninResponse;

public interface RefreshTokenService {

  RefreshTokenEntity createRefreshToken(Long userId);

  SigninResponse updateAccessToken(String refreshToken);

}
