package com.bangbangbwa.backend.domain.oauth.business;

import com.bangbangbwa.backend.domain.oauth.common.dto.GoogleInfoDto;
import com.bangbangbwa.backend.domain.oauth.common.dto.GoogleTokenDto;
import com.bangbangbwa.backend.domain.oauth.common.dto.KakaoInfoDto;
import com.bangbangbwa.backend.domain.oauth.common.dto.KakaoTokenDto;
import com.bangbangbwa.backend.domain.oauth.common.dto.NaverInfoDto;
import com.bangbangbwa.backend.domain.oauth.common.dto.NaverTokenDto;
import com.bangbangbwa.backend.domain.oauth.common.dto.OAuthInfoDto;
import com.bangbangbwa.backend.domain.oauth.common.enums.SnsType;
import com.bangbangbwa.backend.global.util.Base64Util;
import com.google.gson.Gson;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthFeignManager {

  private final GoogleInfoProvider googleInfoProvider;
  private final KakaoInfoProvider kakaoInfoProvider;
  private final NaverInfoProvider naverInfoProvider;
  private final Gson gson;

  public static final String BEARER = "Bearer ";
  public static final String CONTENT_TYPE = "application/x-www-form-urlencoded;charset=UTF-8";
  public static final String GRANT_TYPE = "authorization_code";

  public OAuthInfoDto getGoogleInfo(String oauthToken) throws FeignException {
    GoogleInfoDto googleInfo = googleInfoProvider.getInfo(oauthToken);
    return OAuthInfoDto.builder()
        .snsId(googleInfo.sub())
        .email(googleInfo.email())
        .snsType(SnsType.GOOGLE)
        .build();
  }

  public OAuthInfoDto getKakaoInfo(String accessToken) throws FeignException {
    KakaoInfoDto kakaoInfoDto = kakaoInfoProvider.getInfo(accessToken);
    return OAuthInfoDto.builder()
        .email(kakaoInfoDto.kakaoAccount().email())
        .snsId(kakaoInfoDto.id())
        .snsType(SnsType.KAKAO)
        .oAuthToken(accessToken)
        .build();
  }

  public OAuthInfoDto getNaverInfo(String oauthToken) throws FeignException {
    NaverInfoDto naverInfo = naverInfoProvider.getInfo(oauthToken);
    return OAuthInfoDto.builder()
        .email(naverInfo.response().email())
        .snsId(naverInfo.response().id())
        .snsType(SnsType.NAVER)
        .oAuthToken(oauthToken)
        .build();
  }

  public OAuthInfoDto getGoogleInfoByCode(String authCode) throws FeignException {
    GoogleTokenDto googleToken = googleInfoProvider.getInfoByCode(authCode);
    GoogleInfoDto googleInfo = parseIdToken(googleToken.idToken());
    return OAuthInfoDto.builder()
        .email(googleInfo.email())
        .snsId(googleInfo.sub())
        .snsType(SnsType.GOOGLE)
        .oAuthToken(googleToken.accessToken())
        .build();
  }

  public OAuthInfoDto getKakaoInfoByCode(String authCode) throws FeignException {
    KakaoTokenDto kakaoTokenDto = kakaoInfoProvider.getInfoByCode(authCode);
    KakaoInfoDto kakaoInfoDto = kakaoInfoProvider.getInfo(kakaoTokenDto.accessToken());
    return OAuthInfoDto.builder()
        .email(kakaoInfoDto.kakaoAccount().email())
        .snsId(kakaoInfoDto.id())
        .snsType(SnsType.KAKAO)
        .oAuthToken(kakaoTokenDto.accessToken())
        .build();
  }

  public OAuthInfoDto getNaverInfoByCode(String authCode) throws FeignException {
    NaverTokenDto naverToken = naverInfoProvider.getInfoByCode(authCode);
    NaverInfoDto naverInfo = naverInfoProvider.getInfo(naverToken.accessToken());
    return OAuthInfoDto.builder()
        .email(naverInfo.response().email())
        .snsId(naverInfo.response().id())
        .snsType(SnsType.NAVER)
        .oAuthToken(naverToken.accessToken())
        .build();
  }

  private GoogleInfoDto parseIdToken(String idToken) {
    String[] jwt = idToken.split("\\.");
    String payload = jwt[1];
    String jsonPayload = Base64Util.decode(payload);
    return gson.fromJson(jsonPayload, GoogleInfoDto.class);
  }
}
