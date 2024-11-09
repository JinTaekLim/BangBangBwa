package com.bangbangbwa.backend.domain.sns.common.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("postMedia")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostMedia {

  private Long id;
  private Long postId;
  private String url;
  private String createdId;
  private LocalDateTime createdAt;

  @Builder
  public PostMedia(Long postId, String url, String createdId) {
    this.postId = postId;
    this.url = url;
    this.createdId = createdId;
    this.createdAt = LocalDateTime.now();
  }

  public void updateUrl(Long postId, String url, String createdId) {
    this.postId = postId;
    this.url = url;
    this.createdId = createdId;
  }
}
