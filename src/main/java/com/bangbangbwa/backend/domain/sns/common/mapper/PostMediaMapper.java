package com.bangbangbwa.backend.domain.sns.common.mapper;

import com.bangbangbwa.backend.domain.sns.common.dto.UploadPostMediaDto;
import com.bangbangbwa.backend.domain.sns.common.entity.PostMedia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMediaMapper {

  PostMediaMapper INSTANCE = Mappers.getMapper(PostMediaMapper.class);

  @Mapping(target = "postId", source = "postMedia.postId")
  @Mapping(target = "url", source = "postMedia.url")
  UploadPostMediaDto.Response dtoToCreatePostResponse(PostMedia postMedia);

}
