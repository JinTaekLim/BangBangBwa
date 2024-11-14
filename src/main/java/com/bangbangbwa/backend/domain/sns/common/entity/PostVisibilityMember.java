package com.bangbangbwa.backend.domain.sns.common.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("postVisibilityMember")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostVisibilityMember {

  private Long id;
  private Long memberId;
  private Long postId;
  private LocalDateTime createdAt;
  private String createdId;

  @Builder
  public PostVisibilityMember(Long memberId, Long postId, String createdId) {
    this.memberId = memberId;
    this.postId = postId;
    this.createdAt = LocalDateTime.now();
    this.createdId = createdId;
  }
}
