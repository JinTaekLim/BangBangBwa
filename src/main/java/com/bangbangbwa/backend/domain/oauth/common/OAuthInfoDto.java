package com.bangbangbwa.backend.domain.oauth.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OAuthInfoDto {

  private String snsId;
  private String email;
}
