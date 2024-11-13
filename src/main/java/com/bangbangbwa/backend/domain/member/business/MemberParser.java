package com.bangbangbwa.backend.domain.member.business;

import com.bangbangbwa.backend.domain.member.common.dto.MemberSignupDto;
import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.member.common.mapper.MemberMapper;
import com.bangbangbwa.backend.global.error.exception.BusinessException;
import org.springframework.stereotype.Component;

@Component
public class MemberParser {

  public Member requestToEntity(MemberSignupDto.Request request) {
    return MemberMapper.INSTANCE.dtoToEntity(request);
  }

  public Long parseLong(String memberId) {
    try {
      return Long.valueOf(memberId);
    } catch (NumberFormatException e) {
      throw new BusinessException("잘못된 memberId 입니다.");
    }
  }
}
