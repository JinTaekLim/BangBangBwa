package com.bangbangbwa.backend.domain.oauth.service;

import com.bangbangbwa.backend.domain.oauth.common.OAuthInfoDto;
import org.springframework.stereotype.Service;

@Service
public class OAuthService {

  public OAuthInfoDto getInfoByToken(String snsType, String oauthToken) {
    return OAuthInfoDto.builder().build();
  }
}
