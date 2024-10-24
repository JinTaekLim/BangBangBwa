package com.bangbangbwa.backend.domain.token.business;

import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.token.common.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenProvider {

  private final TokenGenerator tokenGenerator;
  private final AuthenticationProvider authProvider;

  public TokenDto getToken(Member member) {
    Authentication authentication = authProvider.getAuthentication(member);
    return generateToken(authentication);
  }

  private TokenDto generateToken(Authentication authentication) {
    String accessToken = tokenGenerator.generateAccessToken(authentication);
    String refreshToken = tokenGenerator.generateRefreshToken(authentication);
    return TokenDto.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .build();
  }
}
