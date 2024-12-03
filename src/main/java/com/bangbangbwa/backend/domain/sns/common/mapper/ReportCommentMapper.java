package com.bangbangbwa.backend.domain.sns.common.mapper;

import com.bangbangbwa.backend.domain.sns.common.dto.ReportCommentDto;
import com.bangbangbwa.backend.domain.sns.common.entity.ReportComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReportCommentMapper {

  ReportCommentMapper INSTANCE = Mappers.getMapper(ReportCommentMapper.class);

  @Mapping(target = "commentId", source = "request.commentId")
  ReportComment dtoToEntity(ReportCommentDto.Request request);

}
