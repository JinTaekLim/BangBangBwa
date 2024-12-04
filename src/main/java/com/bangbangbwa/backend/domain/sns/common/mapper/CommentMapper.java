package com.bangbangbwa.backend.domain.sns.common.mapper;

import com.bangbangbwa.backend.domain.member.common.dto.CommentDto;
import com.bangbangbwa.backend.domain.sns.common.dto.CreateCommentDto;
import com.bangbangbwa.backend.domain.sns.common.entity.Comment;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {

  CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

  @Mapping(target = "postId", source = "request.postId")
  @Mapping(target = "content", source = "request.content")
  Comment dtoToEntity(CreateCommentDto.Request request);


  @Mapping(target = "postId", source = "comment.postId")
  @Mapping(target = "content", source = "comment.content")
  CreateCommentDto.Response dtoToCreateCommentResponse(Comment comment);

  CommentDto.Response dtoToResponse(List<CommentDto> commentDto);
}
