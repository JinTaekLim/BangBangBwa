package com.bangbangbwa.backend.domain.token.service;

import com.bangbangbwa.backend.domain.member.common.Member;
import com.bangbangbwa.backend.domain.token.business.AuthProvider;
import com.bangbangbwa.backend.domain.token.business.TokenProvider;
import com.bangbangbwa.backend.domain.token.common.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

  private final AuthProvider authProvider;
  private final TokenProvider tokenProvider;

  public TokenDto getToken(Member member) {
    Authentication authentication = authProvider.getAuthentication(member);
    return generateToken(authentication);
  }

  private TokenDto generateToken(Authentication authentication) {
    String accessToken = tokenProvider.generateAccessToken(authentication);
    String refreshToken = tokenProvider.generateRefreshToken(authentication);
    return TokenDto.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .build();
  }


}
