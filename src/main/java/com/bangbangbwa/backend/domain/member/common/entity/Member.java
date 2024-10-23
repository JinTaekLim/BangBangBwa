package com.bangbangbwa.backend.domain.member.common.entity;

import com.bangbangbwa.backend.domain.member.common.enums.Role;
import com.bangbangbwa.backend.domain.oauth.common.dto.OAuthInfoDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {

  @Builder
  public Member(
      String nickname
  ) {
    this.nickname = nickname;
    this.role = Role.MEMBER;
  }

  private Long id;

  private String snsId;

  private String email;

  private String nickname;

  private String profile;

//  private List<Interest> interests;

  private Role role;

  public void addOAuthInfo(OAuthInfoDto oAuthInfo) {
    this.snsId = oAuthInfo.getSnsId();
    this.email = oAuthInfo.getEmail();
  }

  public void updateProfile(String profile) {
    this.profile = profile;
  }
}
