package com.bangbangbwa.backend.domain.member.business;

import com.bangbangbwa.backend.domain.member.common.dto.ToggleFollowDto;
import com.bangbangbwa.backend.domain.member.common.entity.Follow;
import com.bangbangbwa.backend.domain.member.common.mapper.FollowMapper;
import org.springframework.stereotype.Component;

@Component
public class FollowParser {

  public Follow requestToEntity(ToggleFollowDto.Request request) {
    return FollowMapper.INSTANCE.dtoToEntity(request);
  }
}
