package com.bangbangbwa.backend.domain.token.business;

import com.bangbangbwa.backend.domain.token.common.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenProvider {

  private final TokenGenerator tokenGenerator;

  public TokenDto getToken(Authentication authentication) {
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
