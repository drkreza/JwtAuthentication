package com.example.reza.mysecurityjwtdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

   @GetMapping("/all")
   public String testAl(){
      return "test All";
   }

   @GetMapping("/secured-url")
   public String securedUrl(){
      return "Secured";
   }

}
