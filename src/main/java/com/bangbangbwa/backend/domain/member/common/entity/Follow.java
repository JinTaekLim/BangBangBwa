package com.bangbangbwa.backend.domain.member.common.entity;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("follow")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Follow {

  private final String SELF = "SELF";

  private Long id;
  private Long followerId;
  private Long followeeId;
  private LocalDateTime followedAt;
  private LocalDateTime createdAt;
  private String createdId;
  private LocalDateTime updatedAt;
  private String updatedId;

  @Builder
  public Follow(
      Long followerId,
      Long followeeId
  ) {
    this.followerId = followerId;
    this.followeeId = followeeId;

    LocalDateTime now = LocalDateTime.now();
    this.followedAt = now;
    this.createdAt = now;
    this.createdId = SELF;
  }

  public void updateFollowerId(Long followeeId) {
    this.followerId = followeeId;
  }
}
