package com.bangbangbwa.backend.domain.streamer.common.business;

import com.bangbangbwa.backend.domain.streamer.common.entity.PostViewStreamer;
import com.bangbangbwa.backend.domain.streamer.repository.PostViewStreamerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostViewStreamerCreator {
  private final PostViewStreamerRepository postViewStreamerRepository;

  public void save(PostViewStreamer postViewStreamer) {
    postViewStreamerRepository.save(postViewStreamer);
  }
}
