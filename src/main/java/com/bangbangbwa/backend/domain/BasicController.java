package com.bangbangbwa.backend.domain;

import com.bangbangbwa.backend.global.error.exception.BusinessException;
import com.bangbangbwa.backend.global.error.exception.ForbiddenException;
import com.bangbangbwa.backend.global.error.exception.ServerException;
import com.bangbangbwa.backend.global.error.exception.UnAuthenticatedException;
import com.bangbangbwa.backend.global.response.ApiResponse;
import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BasicController implements BasicApi {

  @GetMapping("/health-check")
  public ApiResponse<String> healthCheck() {
    return ApiResponse.ok("The server is available");
  }

  @GetMapping("/error/{name}")
  public ApiResponse<Null> error(@PathVariable String name) {
    switch (name) {
      case "UNAUTHENTICATED" -> throw new UnAuthenticatedException();
      case "FORBIDDEN" -> throw new ForbiddenException();
      case "BAD_REQUEST" -> throw new BusinessException();
      case "SERVER" -> throw new ServerException();
      case "INTERNAL_SERVER_ERROR" -> throw new RuntimeException("INTERNAL_SERVER_ERROR");
      default -> throw new RuntimeException("default");
    }
  }
}
