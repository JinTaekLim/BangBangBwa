package com.bangbangbwa.backend.domain;

import com.bangbangbwa.backend.domain.member.common.Member;
import com.bangbangbwa.backend.domain.member.service.MemberService;
import com.bangbangbwa.backend.domain.oauth.common.OAuthInfoDto;
import com.bangbangbwa.backend.global.error.exception.BusinessException;
import com.bangbangbwa.backend.global.error.exception.ForbiddenException;
import com.bangbangbwa.backend.global.error.exception.ServerException;
import com.bangbangbwa.backend.global.error.exception.UnAuthenticatedException;
import com.bangbangbwa.backend.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class BasicController implements BasicApi {

  private final MemberService memberService;

  @GetMapping("/health-check")
  public ApiResponse<?> healthCheck() {
    return ApiResponse.ok("The server is available");
  }

  @GetMapping("/error/{name}")
  public ApiResponse<?> error(@PathVariable String name) {
    switch (name) {
      case "UNAUTHENTICATED" -> throw new UnAuthenticatedException();
      case "FORBIDDEN" -> throw new ForbiddenException();
      case "BAD_REQUEST" -> throw new BusinessException();
      case "SERVER" -> throw new ServerException();
      case "INTERNAL_SERVER_ERROR" -> throw new RuntimeException("INTERNAL_SERVER_ERROR");
      default -> throw new RuntimeException("default");
    }
  }

  @PostMapping(value = "/file/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  public ApiResponse<?> fileUpload(@RequestPart("file") MultipartFile file) {
    OAuthInfoDto oAuthInfoDto = new OAuthInfoDto();
    Member member = new Member();
    memberService.signup(oAuthInfoDto, member, file);
    return ApiResponse.ok();
  }
}
