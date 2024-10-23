package com.bangbangbwa.backend.domain.member.service;

import com.bangbangbwa.backend.domain.member.business.MemberUpdater;
import com.bangbangbwa.backend.domain.member.common.Member;
import com.bangbangbwa.backend.domain.oauth.common.OAuthInfoDto;
import com.bangbangbwa.backend.domain.token.business.AuthenticationProvider;
import com.bangbangbwa.backend.domain.token.business.TokenProvider;
import com.bangbangbwa.backend.domain.token.common.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberUpdater memberUpdater;
  private final TokenProvider tokenProvider;
  private final AuthenticationProvider authProvider;

  public TokenDto signup(OAuthInfoDto oAuthInfo, Member member, MultipartFile profileFile) {
    memberUpdater.updateProfile(member, profileFile);
    memberUpdater.updateOAuthInfo(member, oAuthInfo);
    Authentication authentication = authProvider.getAuthentication(member);
    return tokenProvider.getToken(authentication);
  }
}
