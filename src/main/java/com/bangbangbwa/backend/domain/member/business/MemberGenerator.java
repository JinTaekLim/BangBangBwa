package com.bangbangbwa.backend.domain.member.business;

import com.bangbangbwa.backend.domain.member.common.dto.MemberSignupDto;
import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.oauth.common.dto.OAuthInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberGenerator {

  private final MemberParser memberParser;

  public Member generate(OAuthInfoDto oAuthInfoDto, MemberSignupDto.Request request) {
    Member member = memberParser.requestToEntity(request);
    member.addOAuthInfo(oAuthInfoDto);
    return member;
  }
}
