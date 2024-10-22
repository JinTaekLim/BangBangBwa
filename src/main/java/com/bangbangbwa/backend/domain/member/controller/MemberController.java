package com.bangbangbwa.backend.domain.member.controller;

import com.bangbangbwa.backend.domain.member.common.Member;
import com.bangbangbwa.backend.domain.member.common.MemberMapper;
import com.bangbangbwa.backend.domain.member.common.MemberSignupDto;
import com.bangbangbwa.backend.domain.member.service.MemberService;
import com.bangbangbwa.backend.domain.member.service.MemberValidator;
import com.bangbangbwa.backend.domain.oauth.common.OAuthInfoDto;
import com.bangbangbwa.backend.domain.oauth.service.OAuthService;
import com.bangbangbwa.backend.domain.token.common.TokenDto;
import com.bangbangbwa.backend.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

  private final OAuthService oAuthService;
  private final MemberService memberService;
  private final MemberValidator memberValidator;

  @PostMapping(value = "/{snsType}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  public ApiResponse<?> signup(
      @PathVariable("snsType") String snsType,
      @RequestPart(value = "file", required = false) MultipartFile file,
      @RequestPart @Valid MemberSignupDto.Request request
  ) {
    memberValidator.validate(request, snsType);
    String oauthToken = request.oauthToken();
    OAuthInfoDto oAuthInfo = oAuthService.getInfoByToken(snsType, oauthToken);
    Member member = MemberMapper.INSTANCE.dtoToEntity(request);
    TokenDto token = memberService.signup(oAuthInfo, member, file);
    return ApiResponse.ok(token);
  }
}
