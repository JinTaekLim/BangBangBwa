package com.bangbangbwa.backend.domain.oauth.service;

import com.bangbangbwa.backend.domain.oauth.business.OAuthInfoProvider;
import com.bangbangbwa.backend.domain.oauth.common.OAuthInfoDto;
import com.bangbangbwa.backend.domain.oauth.common.SnsType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthService {

  private final OAuthInfoProvider oAuthInfoProvider;

  public OAuthInfoDto getInfoByToken(SnsType snsType, String oauthToken) {
    return oAuthInfoProvider.getInfo(snsType, oauthToken);
  }
}
