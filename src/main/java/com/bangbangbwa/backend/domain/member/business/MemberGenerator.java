package com.bangbangbwa.backend.domain.member.business;

import com.bangbangbwa.backend.domain.member.common.dto.MemberSignupDto;
import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.oauth.common.dto.OAuthInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class MemberGenerator {

  private final MemberUpdater memberUpdater;
  private final MemberParser memberParser;

  public Member generate(OAuthInfoDto oAuthInfoDto, MemberSignupDto.Request request,
      MultipartFile multipartFile) {
    Member member = memberParser.requestToEntity(request);
    memberUpdater.updateProfile(member, multipartFile);
    memberUpdater.updateOAuthInfo(member, oAuthInfoDto);
    return member;
  }
}
