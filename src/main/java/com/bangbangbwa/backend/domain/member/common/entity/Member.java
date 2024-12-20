package com.bangbangbwa.backend.domain.member.common.entity;

import com.bangbangbwa.backend.domain.member.common.enums.Role;
import com.bangbangbwa.backend.domain.oauth.common.dto.OAuthInfoDto;
import com.bangbangbwa.backend.domain.oauth.common.enums.SnsType;
import com.bangbangbwa.backend.domain.post.common.entity.Post;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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
  private List<Post> posts;
  private List<Follow> followers;
  private List<Follow> followings;
  private boolean usageAgree;
  private boolean personalAgree;
  private boolean withdrawalAgree;

  @Builder
  public Member(
      String nickname,
      String selfIntroduction,
      boolean usageAgree,
      boolean personalAgree,
      boolean withdrawalAgree
  ) {
    this.nickname = nickname;
    this.selfIntroduction = selfIntroduction;
    this.usageAgree = usageAgree;
    this.personalAgree = personalAgree;
    this.withdrawalAgree = withdrawalAgree;
    this.role = Role.MEMBER;
    LocalDateTime now = LocalDateTime.now();
    this.createdId = SELF;
    this.createdAt = now;
  }

  public void addOAuthInfo(OAuthInfoDto oAuthInfo) {
    this.snsId = oAuthInfo.getSnsId();
    this.email = oAuthInfo.getEmail();
    this.snsType = oAuthInfo.getSnsType();
  }

  public void updateProfile(String profile) {
    this.profile = profile;
  }

  public void updateRole(Role role) {
    this.role = role;
  }

  public void updateInfo(String nickname, String selfIntroduction) {
    boolean isChanged = false;
    if (!this.nickname.equals(nickname)) {
      this.nickname = nickname;
      isChanged = true;
    }
    if (Objects.isNull(this.selfIntroduction) || !this.selfIntroduction.equals(selfIntroduction)) {
      this.selfIntroduction = selfIntroduction;
      isChanged = true;
    }
    if (isChanged) {
      this.updatedId = SELF;
      this.updatedAt = LocalDateTime.now();
    }
  }
}