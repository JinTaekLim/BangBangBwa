package com.bangbangbwa.backend.domain.streamer.common.entity;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("postViewStreamer")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostViewStreamer {

  private Long id;
  private Long postId;
  private Long streamerId;
  private LocalDateTime createdAt;

  @Builder
  public PostViewStreamer(Long postId, Long streamerId) {
    this.postId = postId;
    this.streamerId = streamerId;
    this.createdAt = LocalDateTime.now();
  }
}
