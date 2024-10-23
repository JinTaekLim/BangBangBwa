package com.bangbangbwa.backend.domain.member.business;

import com.bangbangbwa.backend.domain.member.common.dto.MemberSignupDto;
import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.member.common.mapper.MemberMapper;
import org.springframework.stereotype.Component;

@Component
public class MemberParser {

  public Member requestToEntity(MemberSignupDto.Request request) {
    return MemberMapper.INSTANCE.dtoToEntity(request);
  }
}
