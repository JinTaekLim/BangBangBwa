package com.bangbangbwa.backend.domain.token.common.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("token")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Token {
  private Long id;
  private Long memberId;
  private String refreshToken;
  private LocalDateTime expirationTime;

  @Builder
  public Token(Long memberId, String refreshToken, Long expirationTime) {
    this.memberId = memberId;
    this.refreshToken = refreshToken;
    this.expirationTime = LocalDateTime.now().plusSeconds(expirationTime);
  }
}
