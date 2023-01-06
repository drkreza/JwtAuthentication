package com.example.reza.mysecurityjwtdemo.config.security;

import com.example.reza.mysecurityjwtdemo.config.jwt.JwtAuthenticationEntryPoint;
import com.example.reza.mysecurityjwtdemo.config.jwt.JwtAuthentiocationFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class AppSecurityConfig {

  /*@Autowired
  private JwtAuthenticationEntryPoint authenticationEntryPoint;

  @Autowired
  private JwtAuthentiocationFilter jwtAuthentiocationFilter;

  @Autowired
  private CustomUserDetailsService customUserDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().and().cors().disable();
    http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
      .and()
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .authorizeRequests()
      .antMatchers("/api/v1/auth/**").permitAll()
      .antMatchers("/api/test/**").permitAll()
      .anyRequest()
      .authenticated()
      .and();

    http.addFilterBefore(jwtAuthentiocationFilter, UsernamePasswordAuthenticationFilter.class);

  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
  }*/

  @Autowired
  CustomUserDetailsService userDetailsService;

  @Autowired
  private JwtAuthenticationEntryPoint unauthorizedHandler;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Bean
  public JwtAuthentiocationFilter authenticationJwtTokenFilter() {
    return new JwtAuthentiocationFilter();
  }

//  @Override
//  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//    authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(bCryptPasswordEncoder);

    return authProvider;
  }

//  @Bean
//  @Override
//  public AuthenticationManager authenticationManagerBean() throws Exception {
//    return super.authenticationManagerBean();
//  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }

//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http.cors().and().csrf().disable()
//        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//        .authorizeRequests().antMatchers("/api/auth/**").permitAll()
//        .antMatchers("/api/test/**").permitAll()
//        .anyRequest().authenticated();
//
//    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable()
      .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
      .authorizeRequests().antMatchers("/api/v1/auth/**").permitAll()
      .anyRequest().authenticated();

    http.authenticationProvider(authenticationProvider());

    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

}
