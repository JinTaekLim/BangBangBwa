package com.bangbangbwa.backend.domain;

import com.bangbangbwa.backend.global.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BasicController {

  @GetMapping("/health-check")
  public ApiResponse<?> healthCheck() {
    return ApiResponse.ok("The server is available");
  }
}
