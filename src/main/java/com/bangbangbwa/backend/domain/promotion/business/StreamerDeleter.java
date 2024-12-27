package com.bangbangbwa.backend.domain.promotion.business;

import com.bangbangbwa.backend.domain.platform.repository.StreamerPlatformRepository;
import com.bangbangbwa.backend.domain.promotion.common.entity.Streamer;
import com.bangbangbwa.backend.domain.promotion.repository.StreamerRepository;
import com.bangbangbwa.backend.domain.streamer.repository.PostViewStreamerRepository;
import com.bangbangbwa.backend.domain.tag.repository.StreamerTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StreamerDeleter {

  private final StreamerRepository streamerRepository;
  private final StreamerReader streamerReader;
  private final StreamerTagRepository streamerTagRepository;
  private final PostViewStreamerRepository postViewStreamerRepository;
  private final StreamerPlatformRepository streamerPlatformRepository;

  public void deleteByWithdraw(Long memberId) {
    Streamer streamer = streamerReader.findByMemberId(memberId);
    Long streamerId = streamer.getId();
    streamerTagRepository.deleteByStreamerId(streamerId);
    streamerPlatformRepository.deleteByStreamerId(streamerId);
    postViewStreamerRepository.deleteByStreamerId(streamerId);
  }
}
