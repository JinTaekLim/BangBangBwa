package com.bangbangbwa.backend.domain.token.service;

import com.bangbangbwa.backend.domain.member.business.MemberReader;
import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.token.business.TokenDeleter;
import com.bangbangbwa.backend.domain.token.business.TokenProvider;
import com.bangbangbwa.backend.domain.token.business.TokenReader;
import com.bangbangbwa.backend.domain.token.business.TokenValidator;
import com.bangbangbwa.backend.domain.token.common.dto.TokenDto;
import com.bangbangbwa.backend.domain.token.common.entity.Token;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TokenService {

  private final TokenProvider tokenProvider;
  private final TokenReader tokenReader;
  private final TokenValidator tokenValidator;
  private final TokenDeleter tokenDeleter;
  private final MemberReader memberReader;

  public TokenDto getToken(Member member) {
    return tokenProvider.getToken(member);
  }

  @Transactional
  public TokenDto reissueToken(String refreshToken) {
    tokenValidator.validateRefreshToken(refreshToken);
    Token token = tokenReader.findByRefreshToken(refreshToken);
    Member member = memberReader.findById(token.getMemberId());
    tokenDeleter.deleteRefreshToken(token);
    return tokenProvider.getToken(member);
  }

  public Authentication getAuthenticationByAccessToken(String accessToken) {
    Claims claims = tokenValidator.getClaimsFromAccessToken(accessToken);
    return tokenProvider.getAuthentication(claims);
  }

  public String getTokenFromAuthorizationHeader(HttpServletRequest request) {
    return tokenValidator.getTokenFromAuthorizationHeader(request);
  }

  public void validateAccessToken(String accessToken) {
    tokenValidator.validateAccessToken(accessToken);
  }
}
