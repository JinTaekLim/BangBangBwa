package com.bangbangbwa.backend.domain.sns.business;


import com.bangbangbwa.backend.domain.sns.common.dto.CreateCommentDto;
import com.bangbangbwa.backend.domain.sns.common.entity.Comment;
import com.bangbangbwa.backend.domain.sns.common.mapper.CommentMapper;
import org.springframework.stereotype.Component;

@Component
public class CommentParser {

  public Comment requestToEntity(CreateCommentDto.Request request) {
    return CommentMapper.INSTANCE.dtoToEntity(request);
  }
}
