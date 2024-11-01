package com.bangbangbwa.backend.domain.promotion.business;

import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionStreamerDto.PromotionStreamerResponseStreamer;
import com.bangbangbwa.backend.domain.promotion.common.vo.StreamerVo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RandomStreamerProvider {

  private final StreamerReader streamerReader;
  private final StreamerParser streamerParser;

  public List<PromotionStreamerResponseStreamer> getStreamers() {
    List<StreamerVo> streamers = streamerReader.readAllStreamers();
    List<StreamerVo> randomStreamers = new ArrayList<>(getRandomStreamers(streamers, 4));
    List<StreamerVo> interestedStreamers =
        new ArrayList<>(getInterestedStreamers(streamers, 0));// TODO: 회원 관심분야 작업 선행 필요!
    randomStreamers.addAll(interestedStreamers);
    return streamerParser.voToResponse(randomStreamers);
  }

  private List<StreamerVo> getInterestedStreamers(List<StreamerVo> streamers, int count) {
    return new ArrayList<>();
  }

  private List<StreamerVo> getRandomStreamers(List<StreamerVo> streamers, int count) {
    Random random = new Random();
    List<StreamerVo> randomStreamers = new ArrayList<>();
    while (count-- > 0) {
      if (!streamers.isEmpty()) {
        int randomIdx = random.nextInt(0, streamers.size());
        StreamerVo streamer = streamers.get(randomIdx);
        if (!randomStreamers.contains(streamer)) {
          randomStreamers.add(streamer);
        }
      }
    }
    return randomStreamers;
  }
}
