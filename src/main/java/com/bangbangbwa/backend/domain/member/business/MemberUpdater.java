package com.bangbangbwa.backend.domain.member.business;

import com.bangbangbwa.backend.domain.member.common.Member;
import com.bangbangbwa.backend.domain.oauth.common.OAuthInfoDto;
import com.bangbangbwa.backend.global.util.S3Manager;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class MemberUpdater {

  private final S3Manager s3Manager;

  public void updateProfile(Member member, MultipartFile profileFile) {
    if (Objects.nonNull(profileFile)) {
      String profile = s3Manager.upload(profileFile);
      member.updateProfile(profile);
    }
  }

  public void updateOAuthInfo(Member member, OAuthInfoDto oauthInfo) {
    if (Objects.nonNull(oauthInfo)) {
      member.addOAuthInfo(oauthInfo);
    }
  }
}
