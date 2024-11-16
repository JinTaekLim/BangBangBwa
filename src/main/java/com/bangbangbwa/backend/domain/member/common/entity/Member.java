package com.bangbangbwa.backend.domain.member.common.entity;

import com.bangbangbwa.backend.domain.member.common.enums.Interest;
import com.bangbangbwa.backend.domain.member.common.enums.Role;
import com.bangbangbwa.backend.domain.oauth.common.dto.OAuthInfoDto;
import com.bangbangbwa.backend.domain.oauth.common.enums.SnsType;
import com.bangbangbwa.backend.domain.sns.common.entity.Post;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("member")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {

  private final String SELF = "SELF";

  private Long id;
  private SnsType snsType;
  private String snsId;
  private String email;
  private String nickname;
  private String profile;
  private Role role;
  private LocalDateTime deletedAt;
  private String createdId;
  private LocalDateTime createdAt;
  private String updatedId;
  private LocalDateTime updatedAt;
  private String selfIntroduction;
  private List<Interest> interests;
  private List<Post> posts;
  private List<Follower> followers;
  private List<Follower> followings;

  @Builder
  public Member(
      String nickname
  ) {
    this.nickname = nickname;
    this.role = Role.MEMBER;

    LocalDateTime now = LocalDateTime.now();

    this.createdAt = now;
    this.updatedAt = now;
    this.createdId = SELF;
    this.updatedId = SELF;
  }

  public void addOAuthInfo(OAuthInfoDto oAuthInfo) {
    this.snsId = oAuthInfo.getSnsId();
    this.email = oAuthInfo.getEmail();
    this.snsType = oAuthInfo.getSnsType();
  }

  public void updateProfile(String profile) {
    this.profile = profile;
  }
}
