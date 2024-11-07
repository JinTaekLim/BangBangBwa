package com.bangbangbwa.backend.domain.promotion.business;

import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionStreamerDto.PromotionStreamerResponseStreamer;
import com.bangbangbwa.backend.domain.promotion.common.entity.Streamer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RandomStreamerProvider {

  private final StreamerReader streamerReader;
  private final StreamerParser streamerParser;

  public Set<PromotionStreamerResponseStreamer> getStreamers() {
    List<Streamer> streamers = streamerReader.readAllStreamers();
    Set<Streamer> randomStreamers = getRandomStreamers(streamers, 5);
    List<Streamer> interestedStreamers =
        new ArrayList<>(getInterestedStreamers(streamers, 0));// TODO: 회원 관심분야 작업 선행 필요!
    randomStreamers.addAll(interestedStreamers);
    return streamerParser.voToResponse(randomStreamers);
  }

  private Set<Streamer> getInterestedStreamers(List<Streamer> streamers, int count) {
    return new HashSet<>();
  }

  private Set<Streamer> getRandomStreamers(List<Streamer> streamers, int count) {
    Set<Streamer> randomStreamers = new HashSet<>();
    if (streamers.isEmpty()) {
      return randomStreamers;
    }
    Random random = new Random();
    for (int cnt = 0; cnt < 100 && randomStreamers.size() < count; cnt++) {
      int randomIdx = random.nextInt(streamers.size()); // 0 ~ size-1 범위로 수정
      Streamer streamer = streamers.get(randomIdx);
      randomStreamers.add(streamer);
    }
    return randomStreamers;
  }
}
