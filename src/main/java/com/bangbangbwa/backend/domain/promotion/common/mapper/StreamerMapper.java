package com.bangbangbwa.backend.domain.promotion.common.mapper;

import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionStreamerDto.PromotionStreamer;
import com.bangbangbwa.backend.domain.promotion.common.dto.StreamerDto;
import java.util.Set;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
    StreamerInterestedListMapper.class,
    StreamerPlatformListMapper.class
})
public interface StreamerMapper {

  StreamerMapper INSTANCE = Mappers.getMapper(StreamerMapper.class);

  @Named("entityToResponse")
  @Mapping(target = "todayComment", source = "streamerDto.todayComment")
  @Mapping(target = "selfIntroduction", source = "streamerDto.selfIntroduction")
  @Mapping(target = "imageUrl", source = "streamerDto.imageUrl")
  @Mapping(target = "name", source = "streamerDto.name")
  @Mapping(target = "interestedList", source = "streamerDto.tags")
  @Mapping(target = "platformList", source = "streamerDto.platforms")
  PromotionStreamer entityToResponse(StreamerDto streamerDto);

  @IterableMapping(elementTargetType = PromotionStreamer.class, qualifiedByName = "entityToResponse")
  Set<PromotionStreamer> entitiesToResponses(Set<StreamerDto> streamerDto);
}
