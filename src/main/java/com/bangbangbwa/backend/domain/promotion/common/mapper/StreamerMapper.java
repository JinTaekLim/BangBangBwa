package com.bangbangbwa.backend.domain.promotion.common.mapper;

import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionStreamerDto.PromotionStreamer;
import com.bangbangbwa.backend.domain.promotion.common.entity.Streamer;
import java.util.Set;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {StreamerInterestedListMapper.class, StreamerPlatformListMapper.class})
public interface StreamerMapper {

  StreamerMapper INSTANCE = Mappers.getMapper(StreamerMapper.class);

  @Named("entityToResponse")
  @Mapping(target = "todayComment", source = "entity.todayComment")
  @Mapping(target = "selfIntroduction", source = "entity.selfIntroduction")
  @Mapping(target = "imageUrl", source = "entity.imageUrl")
  @Mapping(target = "name", source = "entity.name")
  @Mapping(target = "interestedList", source = "entity.tags")
  @Mapping(target = "platformList", source = "entity.platforms")
  PromotionStreamer entityToResponse(Streamer entity);

  @IterableMapping(elementTargetType = PromotionStreamer.class, qualifiedByName = "entityToResponse")
  Set<PromotionStreamer> entitiesToResponses(Set<Streamer> streamer);
}
