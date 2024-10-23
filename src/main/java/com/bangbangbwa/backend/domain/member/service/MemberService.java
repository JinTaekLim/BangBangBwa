package com.bangbangbwa.backend.domain.member.service;

import com.bangbangbwa.backend.domain.member.common.Member;
import com.bangbangbwa.backend.domain.oauth.common.OAuthInfoDto;
import com.bangbangbwa.backend.domain.token.common.TokenDto;
import com.bangbangbwa.backend.domain.token.service.TokenService;
import com.bangbangbwa.backend.global.util.S3Manager;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final S3Manager s3Manager;
  private final TokenService tokenService;

  public TokenDto signup(OAuthInfoDto oAuthInfo, Member member, MultipartFile profileFile) {
    if (Objects.nonNull(profileFile)) {
      String profile = s3Manager.upload(profileFile);
      member.updateProfile(profile);
    }
    member.addOAuthInfo(oAuthInfo);
    return tokenService.getToken(member);
  }
}
