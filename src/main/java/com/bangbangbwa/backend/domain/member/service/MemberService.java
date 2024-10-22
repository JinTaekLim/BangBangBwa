package com.bangbangbwa.backend.domain.member.service;

import com.bangbangbwa.backend.domain.member.common.Member;
import com.bangbangbwa.backend.domain.oauth.common.OAuthInfoDto;
import com.bangbangbwa.backend.domain.token.common.TokenDto;
import com.bangbangbwa.backend.domain.token.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final TokenService tokenService;

  public TokenDto signup(OAuthInfoDto oAuthInfo, Member member, MultipartFile profileFile) {
    member.addOAuthInfo(oAuthInfo);
    return tokenService.getToken(member);
  }
}
