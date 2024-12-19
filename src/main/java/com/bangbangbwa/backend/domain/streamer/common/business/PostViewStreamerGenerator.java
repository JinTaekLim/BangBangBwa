package com.bangbangbwa.backend.domain.streamer.common.business;

import com.bangbangbwa.backend.domain.promotion.common.entity.Streamer;
import com.bangbangbwa.backend.domain.streamer.common.entity.PostViewStreamer;
import org.springframework.stereotype.Component;

@Component
public class PostViewStreamerGenerator {

  public PostViewStreamer generate(Long postId, Streamer streamer) {
    return PostViewStreamer.builder()
        .postId(postId)
        .streamerId(streamer.getId())
        .build();
  }
}
