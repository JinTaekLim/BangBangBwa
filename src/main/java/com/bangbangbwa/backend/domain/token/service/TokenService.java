package com.bangbangbwa.backend.domain.token.service;

import com.bangbangbwa.backend.domain.member.common.Member;
import com.bangbangbwa.backend.domain.token.common.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class TokenService {

  public TokenDto getToken(Member member) {
    String accessToken = "";
    String refreshToken = "";
    return TokenDto.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .build();
  }
}
