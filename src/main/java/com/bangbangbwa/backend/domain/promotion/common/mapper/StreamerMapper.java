package com.bangbangbwa.backend.domain.promotion.common.mapper;

import com.bangbangbwa.backend.domain.promotion.common.dto.PlatformDto;
import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionStreamerDto.PromotionStreamer;
import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionStreamerDto.PromotionStreamerInterested;
import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionStreamerDto.PromotionStreamerPlatform;
import com.bangbangbwa.backend.domain.promotion.common.dto.StreamerDto;
import com.bangbangbwa.backend.domain.tag.common.dto.TagDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
    StreamerInterestedListMapper.class,
    StreamerPlatformListMapper.class
})
public interface StreamerMapper {

  StreamerMapper INSTANCE = Mappers.getMapper(StreamerMapper.class);

  default Set<PromotionStreamer> entitiesToResponses(
      Set<StreamerDto> streamerDtoSet,
      List<TagDto> interestedTagList
  ) {
    return streamerDtoSet.stream()
        .map(streamerDto -> entityToResponse(streamerDto, interestedTagList))
        .collect(Collectors.toSet());
  }

  default PromotionStreamer entityToResponse(
      StreamerDto streamerDto,
      List<TagDto> interestedTagList
  ) {
    return new PromotionStreamer(
        streamerDto.getName(),
        streamerDto.getTodayComment(),
        streamerDto.getSelfIntroduction(),
        streamerDto.getImageUrl(),
        getInterestedTagDtoList(streamerDto.getTags(), interestedTagList),
        getPlatformDtoList(streamerDto.getPlatforms())
    );
  }

  default List<PromotionStreamerInterested> getInterestedTagDtoList(
      List<TagDto> interestedTagList,
      List<TagDto> memberInterestedTagList
  ) {
    List<PromotionStreamerInterested> returnList = new ArrayList<>();
    for (TagDto tagDto : interestedTagList) {
      boolean isInterested = memberInterestedTagList.contains(tagDto);
      PromotionStreamerInterested response = new PromotionStreamerInterested(
          isInterested,
          tagDto.getName()
      );
      returnList.add(response);
    }
    return returnList;
  }

  default List<PromotionStreamerPlatform> getPlatformDtoList(
      List<PlatformDto> platformDtoList
  ) {
    List<PromotionStreamerPlatform> returnList = new ArrayList<>();
    for (PlatformDto platformDto : platformDtoList) {
      PromotionStreamerPlatform response = new PromotionStreamerPlatform(
          platformDto.getName(),
          platformDto.getImageUrl(),
          platformDto.getProfileUrl()
      );
      returnList.add(response);
    }
    return returnList;
  }
}
