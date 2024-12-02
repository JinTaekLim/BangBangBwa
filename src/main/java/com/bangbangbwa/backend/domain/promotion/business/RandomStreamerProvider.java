package com.bangbangbwa.backend.domain.promotion.business;

import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionStreamerDto;
import com.bangbangbwa.backend.domain.promotion.common.dto.StreamerDto;
import com.bangbangbwa.backend.domain.streamer.common.business.DailyMessageReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RandomStreamerProvider {

  private final DailyMessageReader dailyMessageReader;
  private final StreamerReader streamerReader;
  private final StreamerParser streamerParser;

  private static final int TOTAL_STREAMERS_COUNT = 4;
  private static final int INTERESTED_STREAMERS_COUNT = 3;
  private static final int RANDOM_STREAMERS_COUNT =
      TOTAL_STREAMERS_COUNT - INTERESTED_STREAMERS_COUNT;

  public Set<PromotionStreamerDto.PromotionStreamer> getStreamers() {
    List<StreamerDto> streamers = streamerReader.readAllStreamers();
    if (streamers.isEmpty()) {
      return new HashSet<>();
    }
    Set<StreamerDto> randomStreamers = getRandomStreamers(streamers);
    // TODO : 관심 분야 포함된 로직 3, 랜덤 1
    List<StreamerDto> interestedStreamers = new ArrayList<>(getInterestedStreamers(streamers));
    randomStreamers.addAll(interestedStreamers);
    return streamerParser.voToResponse(randomStreamers);
  }

  private Set<StreamerDto> getInterestedStreamers(List<StreamerDto> streamers) {
    return new HashSet<>();
  }

  private Set<StreamerDto> getRandomStreamers(List<StreamerDto> streamers) {
    List<StreamerDto> shuffledStreamers = new ArrayList<>(streamers);
    Collections.shuffle(shuffledStreamers);
    return shuffledStreamers.stream()
        .limit(RANDOM_STREAMERS_COUNT)
        .peek(streamer
            -> streamer.updateTodayComment(dailyMessageReader.getMessage(streamer.getId())))
        .collect(Collectors.toSet());
  }
}
