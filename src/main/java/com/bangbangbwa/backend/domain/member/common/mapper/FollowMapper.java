package com.bangbangbwa.backend.domain.member.common.mapper;

import com.bangbangbwa.backend.domain.member.common.dto.FollowDto;
import com.bangbangbwa.backend.domain.member.common.dto.FollowDto.FollowerResponse;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FollowMapper {

  FollowMapper INSTANCE = Mappers.getMapper(FollowMapper.class);

  default FollowDto.Response dtoToResponse(List<FollowDto> followDtos) {
    if (followDtos == null || followDtos.isEmpty()) {
      return new FollowDto.Response(null);
    }

    List<FollowerResponse> followers = new ArrayList<>();
    followDtos.forEach(follow ->
        followers.add(
          new FollowerResponse(
              null, // TODO: FollowDto에서 가져오기
              null, // TODO: FollowDto에서 가져오기
              null // TODO: FollowDto에서 가져오기
          )
    ));

    return new FollowDto.Response(followers);
  }
}
