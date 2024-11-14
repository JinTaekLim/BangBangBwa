package com.bangbangbwa.backend.domain.sns.business;


import com.bangbangbwa.backend.domain.sns.common.dto.CreatePostDto;
import com.bangbangbwa.backend.domain.sns.common.entity.Post;
import com.bangbangbwa.backend.domain.sns.common.mapper.PostMapper;
import org.springframework.stereotype.Component;

@Component
public class PostParser {

  public Post requestToEntity(CreatePostDto.Request request) {
    return PostMapper.INSTANCE.dtoToEntity(request);
  }
}
