package com.example.reza.mysecurityjwtdemo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "users", uniqueConstraints = {
  @UniqueConstraint(columnNames = "email")
  , @UniqueConstraint(columnNames = "username")})
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(message = "username can not be null")
  private String username;

  @NotNull(message = "email can not be null")
  private String email;

  @NotNull(message = "password can not be null")
  @Size(min = 4, message = "password must be greater than 4 characters")
  private String password;


}
