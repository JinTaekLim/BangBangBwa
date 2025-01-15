package com.bangbangbwa.backend.domain.tag.common.mapper;

import com.bangbangbwa.backend.domain.tag.common.dto.TagDto;
import com.bangbangbwa.backend.domain.tag.common.entity.Tag;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TagMapper {

  TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

  @IterableMapping(elementTargetType = TagDto.class, qualifiedByName = "toDto")
  List<TagDto> toDto(List<Tag> tags);

  @Named("toDto")
  TagDto toDto(Tag tag);
}
