package com.bangbangbwa.backend.domain.test;

import com.bangbangbwa.backend.domain.member.common.Member;
import com.bangbangbwa.backend.domain.member.service.MemberService;
import com.bangbangbwa.backend.domain.oauth.common.OAuthInfoDto;
import com.bangbangbwa.backend.domain.token.common.TokenDto;
import com.bangbangbwa.backend.global.response.ApiResponse;
import java.lang.reflect.Field;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

  private final MemberService memberService;

  @PostMapping(value = "/file/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  public ApiResponse<?> fileUpload(@RequestPart("file") MultipartFile file) {
    OAuthInfoDto oAuthInfoDto = new OAuthInfoDto();
    Member member = Member.builder().build();
    memberService.signup(oAuthInfoDto, member, file);
    return ApiResponse.ok();
  }

  @GetMapping("/getToken")
  public ApiResponse<?> getToken() throws IllegalAccessException, NoSuchFieldException {
    OAuthInfoDto oAuthInfoDto = new OAuthInfoDto();
    Member member = Member.builder().build();
    Field idField = Member.class.getDeclaredField("id");
    idField.setAccessible(true);
    idField.set(member, 1L);

    TokenDto tokenDto = memberService.signup(oAuthInfoDto, member, null);
    return ApiResponse.ok(tokenDto);
  }
}
