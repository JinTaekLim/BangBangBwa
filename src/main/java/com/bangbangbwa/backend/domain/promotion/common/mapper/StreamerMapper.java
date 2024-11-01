package com.bangbangbwa.backend.domain.promotion.common.mapper;

import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionStreamerDto.PromotionStreamerResponseStreamer;
import com.bangbangbwa.backend.domain.promotion.common.vo.StreamerVo;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {StreamerInterestedListMapper.class, StreamerPlatformListMapper.class})
public interface StreamerMapper {

  StreamerMapper INSTANCE = Mappers.getMapper(StreamerMapper.class);

  @Mapping(ignore = true, target = "id")
  @Mapping(target = "todayComment", source = "streamerVo.todayComment")
  @Mapping(target = "selfIntroduction", source = "streamerVo.selfIntroduction")
  @Mapping(target = "imageUrl", source = "streamerVo.imageUrl")
  @Mapping(target = "name", source = "streamerVo.name")
  @Mapping(target = "interestedList", source = "streamerVo.interestedList")
  @Mapping(target = "platformList", source = "streamerVo.platformList")
  List<PromotionStreamerResponseStreamer> voToDto(List<StreamerVo> streamerVo);
}
