package com.example.reza.mysecurityjwtdemo;

import com.example.reza.mysecurityjwtdemo.config.security.CustomUserDetails;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MySecurityJwtDemoApplication {

  @Bean
  public ModelMapper modelMapper(){
    return new ModelMapper();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  public CustomUserDetails customUserDetails(){
    return new CustomUserDetails();
  }

  public static void main(String[] args) {
    SpringApplication.run(MySecurityJwtDemoApplication.class, args);
  }

}
