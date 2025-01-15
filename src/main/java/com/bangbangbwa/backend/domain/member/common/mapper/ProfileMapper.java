package com.bangbangbwa.backend.domain.member.common.mapper;

import com.bangbangbwa.backend.domain.member.common.dto.ProfileDto;
import com.bangbangbwa.backend.domain.tag.common.entity.Tag;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfileMapper {

  ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

  @Mapping(target = "tags", expression = "java(selectTags(dto))")
  ProfileDto.Response dtoToResponse(ProfileDto dto);

  @Named("selectTags")
  default List<String> selectTags(ProfileDto dto) {
    if (dto.getIsStreamer()) {
      return getTags(dto.getStreamerTags());
    }
    return getTags(dto.getInterestTags());
  }

  default List<String> getTags(List<Tag> tags) {
    if (tags == null || tags.isEmpty()) {
      return null;
    }
    return tags.stream()
        .map(Tag::getName)
        .toList();
  }
}
