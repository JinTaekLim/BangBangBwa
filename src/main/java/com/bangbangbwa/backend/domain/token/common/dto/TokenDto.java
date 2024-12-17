package com.bangbangbwa.backend.domain.token.common.dto;

import com.bangbangbwa.backend.domain.member.common.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {

  private String accessToken;
  private String refreshToken;
  private Long memberId;
  private String role;
  private String profileImage;

  public void setMemberInfo(Member member) {
    this.memberId = member.getId();
    this.role = member.getRole().name();
    this.profileImage = member.getProfile();
  }
}
