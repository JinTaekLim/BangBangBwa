package com.bangbangbwa.backend.domain;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BasicController {

  @GetMapping("/health-check")
  public ResponseEntity<?> healthCheck(){
    return ResponseEntity.ok("The server is available");
  }
}
