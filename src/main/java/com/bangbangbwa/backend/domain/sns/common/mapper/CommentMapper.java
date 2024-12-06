package com.bangbangbwa.backend.domain.sns.common.mapper;

import com.bangbangbwa.backend.domain.member.common.dto.CommentDto;
import com.bangbangbwa.backend.domain.member.common.dto.CommentDto.CommentResponse;
import com.bangbangbwa.backend.domain.member.common.dto.CommentDto.CommentResponseCommentInfo;
import com.bangbangbwa.backend.domain.member.common.dto.CommentDto.CommentResponsePostInfo;
import com.bangbangbwa.backend.domain.sns.common.dto.CreateCommentDto;
import com.bangbangbwa.backend.domain.sns.common.entity.Comment;
import java.util.ArrayList;
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

  default CommentDto.Response dtoToResponse(List<CommentDto> commentDtos) {
    if (commentDtos == null || commentDtos.isEmpty()) {
      return new CommentDto.Response(null);
    }

    List<CommentResponse> comments = new ArrayList<>();

    commentDtos.forEach(comment -> {
      CommentResponsePostInfo postInfo = null; // TODO : CommentDto에서 가져오기
      CommentResponseCommentInfo commentInfo = null; // TODO : CommentDto에서 가져오기
      CommentResponse commentResponse = new CommentResponse(postInfo, commentInfo);
      comments.add(commentResponse);
    });

    return new CommentDto.Response(comments);
  }
}
