package com.example.reza.mysecurityjwtdemo.service.impl;

import com.example.reza.mysecurityjwtdemo.config.jwt.JwtUtils;
import com.example.reza.mysecurityjwtdemo.config.security.CustomUserDetails;
import com.example.reza.mysecurityjwtdemo.entity.RefreshTokenEntity;
import com.example.reza.mysecurityjwtdemo.payload.dto.response.SigninResponse;
import com.example.reza.mysecurityjwtdemo.repository.RefreshTokenRepository;
import com.example.reza.mysecurityjwtdemo.repository.UserRepository;
import com.example.reza.mysecurityjwtdemo.service.RefreshTokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RefreshTokenRepository refreshTokenRepository;

  @Autowired
  private JwtUtils jwtUtils;

  @Override
  public RefreshTokenEntity createRefreshToken(Long userId) {
    RefreshTokenEntity refreshToken = new RefreshTokenEntity();

    refreshToken.setUserEntity(userRepository.findById(userId).get());
    refreshToken.setToken(UUID.randomUUID().toString());

    refreshToken = refreshTokenRepository.save(refreshToken);
    return refreshToken;
  }

  @Override
  public SigninResponse updateAccessToken(String refreshToken) {

    RefreshTokenEntity refreshTokenEntity = refreshTokenRepository.findByToken(refreshToken).orElseThrow(() -> new RuntimeException("refresh token is not valid"));

    String accessToken = jwtUtils.generateTokenFromUsername(refreshTokenEntity.getUserEntity().getUsername());

    SigninResponse response = new SigninResponse();
    response.setToken(accessToken);
    response.setRefreshToken(refreshToken);

    /*CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String accessToken = jwtUtils.generateTokenFromUsername(userDetails.getUsername());
    Long userId = userDetails.getId();
    RefreshTokenEntity refreshTokenEntityNew = createRefreshToken(userId);

    SigninResponse response = new SigninResponse();
    response.setToken(accessToken);
    response.setRefreshToken(refreshTokenEntityNew.getToken());
*/
    return response;

  }

}
