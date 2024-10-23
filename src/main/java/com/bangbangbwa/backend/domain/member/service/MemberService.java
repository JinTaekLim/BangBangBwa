package com.bangbangbwa.backend.domain.member.service;

import com.bangbangbwa.backend.domain.member.business.MemberUpdater;
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
  private final MemberUpdater memberUpdater;

  public TokenDto signup(OAuthInfoDto oAuthInfo, Member member, MultipartFile profileFile) {
    memberUpdater.updateProfile(member, profileFile);
    memberUpdater.updateOAuthInfo(member, oAuthInfo);
    return tokenService.getToken(member);
  }
}
