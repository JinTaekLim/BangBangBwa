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

  @Mapping(target = "interests", source = "interests", qualifiedByName = "interestsToString")
  ProfileDto.Response dtoToResponse(ProfileDto dto);

  @Named("interestsToString")
  static List<String> interestsToString(List<Tag> interests) {
    if (interests == null || interests.isEmpty()) {
      return null;
    }
    return interests.stream()
        .map(Tag::getName)
        .toList();
  }
}
