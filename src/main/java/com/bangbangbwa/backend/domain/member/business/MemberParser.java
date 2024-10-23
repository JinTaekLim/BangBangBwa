package com.bangbangbwa.backend.domain.member.business;

import com.bangbangbwa.backend.domain.member.common.Member;
import com.bangbangbwa.backend.domain.member.common.MemberMapper;
import com.bangbangbwa.backend.domain.member.common.MemberSignupDto;
import org.springframework.stereotype.Component;

@Component
public class MemberParser {

  public Member requestToEntity(MemberSignupDto.Request request) {
    return MemberMapper.INSTANCE.dtoToEntity(request);
  }
}
