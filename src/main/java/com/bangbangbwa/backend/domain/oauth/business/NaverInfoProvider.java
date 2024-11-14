package com.bangbangbwa.backend.domain.oauth.business;

import static com.bangbangbwa.backend.domain.oauth.business.OAuthFeignManager.BEARER;
import static com.bangbangbwa.backend.domain.oauth.business.OAuthFeignManager.CONTENT_TYPE;
import static com.bangbangbwa.backend.domain.oauth.business.OAuthFeignManager.GRANT_TYPE;

import com.bangbangbwa.backend.domain.oauth.business.feign.NaverInfoFeign;
import com.bangbangbwa.backend.domain.oauth.business.feign.NaverTokenFeign;
import com.bangbangbwa.backend.domain.oauth.common.dto.NaverInfoDto;
import com.bangbangbwa.backend.domain.oauth.common.dto.NaverTokenDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NaverInfoProvider {

  private final NaverTokenFeign naverTokenFeign;
  private final NaverInfoFeign naverInfoFeign;
  private final String NAVER_CLIENT_ID;
  private final String NAVER_CLIENT_SECRET;
  private final String NAVER_GRANT_TYPE;
  private final String NAVER_STATE;

  NaverInfoProvider(
      NaverTokenFeign naverTokenFeign,
      NaverInfoFeign naverInfoFeign,
      @Value("${oauth.client.naver.client-id}") String naverClientId,
      @Value("${oauth.client.naver.client-secret}") String naverClientSecret,
      @Value("${oauth.client.naver.state}") String state
  ) {
    this.naverTokenFeign = naverTokenFeign;
    this.naverInfoFeign = naverInfoFeign;
    this.NAVER_CLIENT_ID = naverClientId;
    this.NAVER_CLIENT_SECRET = naverClientSecret;
    this.NAVER_GRANT_TYPE = GRANT_TYPE;
    this.NAVER_STATE = state;
  }

  public NaverInfoDto getInfo(String accessToken) {
    return naverInfoFeign.requestInfo(BEARER + accessToken, CONTENT_TYPE);
  }

  public NaverTokenDto getInfoByCode(String oauthToken) {
    return naverTokenFeign.getAccessToken(
        NAVER_GRANT_TYPE,
        NAVER_CLIENT_ID,
        NAVER_CLIENT_SECRET,
        oauthToken,
        NAVER_STATE
    );
  }
}
