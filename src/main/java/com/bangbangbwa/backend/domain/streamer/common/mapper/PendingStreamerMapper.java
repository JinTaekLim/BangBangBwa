package com.bangbangbwa.backend.domain.streamer.common.mapper;

import com.bangbangbwa.backend.domain.admin.common.dto.ApproveStreamerDto;
import com.bangbangbwa.backend.domain.member.common.dto.PromoteStreamerDto;
import com.bangbangbwa.backend.domain.streamer.common.entity.PendingStreamer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PendingStreamerMapper {

  PendingStreamerMapper INSTANCE = Mappers.getMapper(PendingStreamerMapper.class);

  @Mapping(target = "platformUrl", source = "request.platformUrl")
  PendingStreamer dtoToEntity(PromoteStreamerDto.Request request);

  @Mapping(target = "id", source = "pendingStreamerId")
  @Mapping(target = "status", source = "status")
  PendingStreamer dtoToEntity(ApproveStreamerDto.Request request);

  @Mapping(target = "platformUrl", source = "platformUrl")
  PromoteStreamerDto.Response dtoToPromoteStreamerResponse(PendingStreamer pendingStreamer);

  @Mapping(target = "pendingStreamerId", source = "id")
  ApproveStreamerDto.Response dtoToApproveStreamerResponse(PendingStreamer pendingStreamer);

}
