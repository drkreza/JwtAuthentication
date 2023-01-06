package com.example.reza.mysecurityjwtdemo.repository;

import com.example.reza.mysecurityjwtdemo.entity.RefreshTokenEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
  Optional<RefreshTokenEntity> findByToken(String refreshToken);

}
