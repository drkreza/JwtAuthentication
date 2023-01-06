package com.example.reza.mysecurityjwtdemo.repository;

import com.example.reza.mysecurityjwtdemo.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

  UserEntity findUserByUsername(String username);

  boolean existsByEmail(String email);

  boolean existsByUsername(String username);

}
