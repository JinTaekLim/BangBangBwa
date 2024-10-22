package com.bangbangbwa.backend.domain.member.common;

import com.bangbangbwa.backend.domain.oauth.common.OAuthInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

  @Builder
  public Member(
      String nickname
  ) {
    this.nickname = nickname;
    this.role = Role.MEMBER;
  }

  private Long id;

  private String oauthId;

  private String email;

  private String nickname;

//  private List<Interest> interests;

  private Role role;

  public void addOAuthInfo(OAuthInfoDto oAuthInfo) {
    this.oauthId = oAuthInfo.getOauthId();
    this.email = oAuthInfo.getEmail();
  }
}
