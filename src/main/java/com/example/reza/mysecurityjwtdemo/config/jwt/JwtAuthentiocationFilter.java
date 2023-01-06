package com.example.reza.mysecurityjwtdemo.config.jwt;

import com.example.reza.mysecurityjwtdemo.config.security.CustomUserDetails;
import com.example.reza.mysecurityjwtdemo.config.security.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthentiocationFilter extends OncePerRequestFilter {
  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private CustomUserDetails customUserDetails;
  @Autowired
  private CustomUserDetailsService customUserDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {

    String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      String token = authHeader.substring(7);
      if (jwtUtils.validateJwtToken(token)) {
        String username = jwtUtils.getUserNameFromJwtToken(token);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken authenticate =
          new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());

        authenticate.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticate);

      } else {
        throw new RuntimeException("jwtToken is not valid");
      }
    } else {
      filterChain.doFilter(request, response);
      return;
    }

    filterChain.doFilter(request, response);

  }
}
