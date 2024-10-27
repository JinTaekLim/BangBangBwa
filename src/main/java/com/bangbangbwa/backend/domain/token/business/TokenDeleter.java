package com.bangbangbwa.backend.domain.token.business;

import com.bangbangbwa.backend.domain.token.common.entity.Token;
import com.bangbangbwa.backend.domain.token.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenDeleter {

  private final TokenRepository tokenRepository;

  public void deleteRefreshToken(Token token) {
    tokenRepository.deleteByRefreshToken(token);
  }


}
