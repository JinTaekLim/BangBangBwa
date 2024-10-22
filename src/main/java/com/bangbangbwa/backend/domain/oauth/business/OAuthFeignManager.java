package com.bangbangbwa.backend.domain.oauth.business;

import com.bangbangbwa.backend.domain.oauth.common.GoogleInfoDto;
import com.bangbangbwa.backend.domain.oauth.common.OAuthInfoDto;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OAuthFeignManager {

  private static final String BEARER = "Bearer ";
  private static final String CONTENT_TYPE = "application/x-www-form-urlencoded;charset=UTF-8";

  private final GoogleInfoFeign googleInfoFeign;
  private final String GOOGLE_CLIENT_ID;
  private final String GOOGLE_CLIENT_SECRET;
  private final String GOOGLE_REDIRECT_URI;
  private final String GOOGLE_GRANT_TYPE;

  private final KakaoInfoFeign kakaoInfoFeign;
  private final String KAKAO_CLIENT_ID;
  private final String KAKAO_CLIENT_SECRET;
  private final String KAKAO_REDIRECT_URI;
  private final String KAKAO_GRANT_TYPE;

  private final NaverInfoFeign naverInfoFeign;
  private final String NAVER_CLIENT_ID;
  private final String NAVER_CLIENT_SECRET;
  private final String NAVER_REDIRECT_URI;
  private final String NAVER_GRANT_TYPE;

  OAuthFeignManager(
      @Value("authorization_code") String grantType,

      GoogleInfoFeign googleInfoFeign,
      @Value("${oauth.client.google.client-id}") String googleClientId,
      @Value("${oauth.client.google.client-secret}") String googleClientSecret,
      @Value("${oauth.client.google.redirect-url}") String googleRedirectUri,

      KakaoInfoFeign kakaoInfoFeign,
      @Value("${oauth.client.kakao.client-id}") String kakaoClientId,
      @Value("${oauth.client.kakao.client-secret}") String kakaoClientSecret,
      @Value("${oauth.client.kakao.redirect-url}") String kakaoRedirectUri,

      NaverInfoFeign naverInfoFeign,
      @Value("${oauth.client.naver.client-id}") String naverClientId,
      @Value("${oauth.client.naver.client-secret}") String naverClientSecret,
      @Value("${oauth.client.naver.redirect-url}") String naverRedirectUri

  ) {
    this.googleInfoFeign = googleInfoFeign;
    this.GOOGLE_CLIENT_ID = googleClientId;
    this.GOOGLE_CLIENT_SECRET = googleClientSecret;
    this.GOOGLE_REDIRECT_URI = googleRedirectUri;
    this.GOOGLE_GRANT_TYPE = grantType;

    this.kakaoInfoFeign = kakaoInfoFeign;
    this.KAKAO_CLIENT_ID = kakaoClientId;
    this.KAKAO_CLIENT_SECRET = kakaoClientSecret;
    this.KAKAO_REDIRECT_URI = kakaoRedirectUri;
    this.KAKAO_GRANT_TYPE = grantType;

    this.naverInfoFeign = naverInfoFeign;
    this.NAVER_CLIENT_ID = naverClientId;
    this.NAVER_CLIENT_SECRET = naverClientSecret;
    this.NAVER_REDIRECT_URI = naverRedirectUri;
    this.NAVER_GRANT_TYPE = grantType;
  }

  public OAuthInfoDto getGoogleInfo(String oauthToken) throws FeignException {
    GoogleInfoDto googleInfo = googleInfoFeign.requestInfo(BEARER + oauthToken, CONTENT_TYPE);
    return OAuthInfoDto.builder()
        .snsId(googleInfo.sub())
        .email(googleInfo.email())
        .build();
  }

  public OAuthInfoDto getKakaoInfo(String oauthToken) throws FeignException {
    return OAuthInfoDto.builder().build();
  }

  public OAuthInfoDto getNaverInfo(String oauthToken) throws FeignException {
    return OAuthInfoDto.builder().build();
  }
}
