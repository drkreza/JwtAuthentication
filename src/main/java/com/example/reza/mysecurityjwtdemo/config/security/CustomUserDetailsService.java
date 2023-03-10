package com.example.reza.mysecurityjwtdemo.config.security;

import com.example.reza.mysecurityjwtdemo.entity.UserEntity;
import com.example.reza.mysecurityjwtdemo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;
//  private CustomUserDetails customUserDetails;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    UserEntity userEntity = userRepository.findUserByUsername(username);

    if (userEntity == null) throw new UsernameNotFoundException("username not found");

    return CustomUserDetails.build(userEntity);
  }


}
