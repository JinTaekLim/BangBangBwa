package com.bangbangbwa.backend.domain.promotion.business;

import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionStreamerDto;
import com.bangbangbwa.backend.domain.promotion.common.dto.StreamerDto;
import com.bangbangbwa.backend.domain.streamer.common.business.DailyMessageReader;
import com.bangbangbwa.backend.domain.tag.business.TagReader;
import com.bangbangbwa.backend.domain.tag.common.dto.TagDto;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
  private final TagReader tagReader;

  public Set<PromotionStreamerDto.PromotionStreamer> getStreamers(Long memberId) {
    // 1. 전체 스트리머 조회
    List<StreamerDto> allStreamers = streamerReader.readAllStreamers();
    List<TagDto> interestedTagList = tagReader.findByMemberId(memberId);
    Set<StreamerDto> interestedStreamers;
    Set<StreamerDto> randomStreamers;

    if (Objects.nonNull(interestedTagList) && !interestedTagList.isEmpty()) {
      // 2-1. 회원 태그 조회
      Map<Long, String> interestedTagMap = interestedTagList.stream()
          .collect(Collectors.toMap(TagDto::getId, TagDto::getName));

      // 3. 관심 스트리머 조회
      interestedStreamers = getInterestedStreamers(allStreamers, interestedTagMap);
      Map<Long, String> notContainMap = interestedStreamers.stream()
          .collect(Collectors.toMap(StreamerDto::getId, StreamerDto::getName));

      // 4. 랜덤 스트리머 조회
      randomStreamers = getRandomStreamers(allStreamers,
          TOTAL_STREAMERS_COUNT - interestedStreamers.size(), notContainMap);
    } else {
      // 2-2. 랜덤 스트리머만 조회
      interestedStreamers = new HashSet<>();
      randomStreamers = getRandomStreamers(allStreamers, TOTAL_STREAMERS_COUNT, new HashMap<>());
    }

    // 5. 두 Set 병합
    Set<StreamerDto> streamers = Stream.concat(
        interestedStreamers.stream(),
        randomStreamers.stream()
    ).collect(Collectors.toSet());

    for (StreamerDto streamer : streamers) {
      String comment = dailyMessageReader.getMessage(streamer.getId());
      streamer.updateTodayComment(comment);
    }

    // 6. 응답 DTO로 변환 후 반환
    return streamerParser.voToResponse(streamers, interestedTagList);
  }

  private Set<StreamerDto> getInterestedStreamers(
      List<StreamerDto> streamers,
      Map<Long, String> interestedTagMap
  ) {
    Set<StreamerDto> interestedStreamers = new HashSet<>();
    for (StreamerDto streamer : streamers) {
      for (TagDto tag : streamer.getTags()) {
        if (interestedTagMap.containsKey(tag.getId())) {
          interestedStreamers.add(streamer);
          break;
        }
      }
      if (interestedStreamers.size() >= INTERESTED_STREAMERS_COUNT) {
        break;
      }
    }
    return interestedStreamers;
  }

  private Set<StreamerDto> getRandomStreamers(
      List<StreamerDto> streamers,
      int count,
      Map<Long, String> notContainList
  ) {
    if (notContainList == null) {
      notContainList = new HashMap<>();
    }
    Set<StreamerDto> shuffledStreamers = new HashSet<>();
    Collections.shuffle(streamers);
    for (StreamerDto streamer : streamers) {
      if (!notContainList.containsKey(streamer.getId())) {
        shuffledStreamers.add(streamer);
      }
      if (shuffledStreamers.size() >= count) {
        break;
      }
    }
    return shuffledStreamers;
  }
}
