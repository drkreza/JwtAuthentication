package com.example.reza.mysecurityjwtdemo.service.impl;

import com.example.reza.mysecurityjwtdemo.entity.UserEntity;
import com.example.reza.mysecurityjwtdemo.payload.dto.request.SignupRequest;
import com.example.reza.mysecurityjwtdemo.payload.dto.response.SignupResponse;
import com.example.reza.mysecurityjwtdemo.repository.UserRepository;
import com.example.reza.mysecurityjwtdemo.service.SignupService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignupServiceImpl implements SignupService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Override
  public Optional<SignupResponse> signup(SignupRequest signupRequest) {
    if (userRepository.existsByEmail(signupRequest.getEmail())){
      throw new RuntimeException("Email already exist");
    }
    if (userRepository.existsByUsername(signupRequest.getUsername())){
      throw new RuntimeException("Username already exist");
    }

    signupRequest.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
    UserEntity userEntity = modelMapper.map(signupRequest, UserEntity.class);
    userRepository.save(userEntity);
    SignupResponse response = new SignupResponse();
    response.setMessage("registered successfully");

    return Optional.of(response);
  }


}
