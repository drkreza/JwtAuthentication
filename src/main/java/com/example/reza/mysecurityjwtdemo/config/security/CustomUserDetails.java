package com.example.reza.mysecurityjwtdemo.config.security;

import com.example.reza.mysecurityjwtdemo.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CustomUserDetails implements UserDetails {

  private Long id;

  private String username;

  @JsonIgnore
  private String password;

  private String email;

  private Collection<? extends GrantedAuthority> grantedAuthorities;

  public CustomUserDetails(Long id,
                           String username,
                           String password,
                           String email,
                           Collection<? extends GrantedAuthority> grantedAuthorities) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.email = email;
    this.grantedAuthorities = grantedAuthorities;
  }

  public Collection<? extends GrantedAuthority> getGrantedAuthorities() {
    return grantedAuthorities;
  }

  public static CustomUserDetails build(UserEntity userEntity) {

    return new CustomUserDetails(
      userEntity.getId(),
      userEntity.getUsername(),
      userEntity.getPassword(),
      userEntity.getEmail(),
      Collections.emptyList());

  }

   /*public static CustomUserDetails build(UserEntity userEntity) {
      List<GrantedAuthority> authorities = userEntity.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
        .collect(Collectors.toList());

      return new CustomUserDetails(
        userEntity.getId(),
        userEntity.getUsername(),
        userEntity.getEmail(),
        userEntity.getPassword(),
        authorities);
   }*/

//  public Long getId() {
//    return id;
//  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.emptyList();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

//  public String getEmail() {
//    return email;
//  }

//  public void setEmail(String email) {
//    this.email = email;
//  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    CustomUserDetails user = (CustomUserDetails) o;
    return Objects.equals(id, user.id);
  }
}
