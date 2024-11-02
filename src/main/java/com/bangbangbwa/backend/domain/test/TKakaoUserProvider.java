//package com.bangbangbwa.backend.domain.test;
//
//import com.bangbangbwa.backend.domain.auth.common.dto.KakaoInfoDto;
//import com.bangbangbwa.backend.domain.auth.common.dto.KakaoTokenDto;
//import com.bangbangbwa.backend.domain.auth.common.dto.OAuthTokenDto;
//import com.bangbangbwa.backend.domain.auth.common.dto.OAuthUserInfoDto;
//import feign.FeignException;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//@Component
//public class TKakaoUserProvider {
//
//  private final TKakaoTokenClient TKakaoTokenClient;
//  private final TKakaoUserClient TKakaoUserClient;
//  private final String NAVER_CLIENT_ID;
//  private final String NAVER_CLIENT_SECRET;
//  private final String NAVER_REDIRECT_URI;
//  private final String NAVER_GRANT_TYPE;
//  TKakaoUserProvider(
//      TKakaoTokenClient TKakaoTokenClient,
//      TKakaoUserClient TKakaoUserClient,
//      @Value("${oauth2.client.registration.naver.client-id}") String clientId,
//      @Value("${oauth2.client.registration.naver.client-secret}") String clientSecret,
//      @Value("http://localhost:8080/test/kakao") String redirectUri,
//      @Value("authorization_code") String grantType
//  ) {
//    this.TKakaoTokenClient = TKakaoTokenClient;
//    this.TKakaoUserClient = TKakaoUserClient;
//    this.NAVER_CLIENT_ID = clientId;
//    this.NAVER_CLIENT_SECRET = clientSecret;
//    this.NAVER_REDIRECT_URI = redirectUri;
//    this.NAVER_GRANT_TYPE = grantType;
//  }
//
//
//
//
//  public KakaoTokenDto getAccessToken(String authCode)
//      throws FeignException {
//
//    KakaoTokenDto accessToken = TKakaoTokenClient.getAccessToken(
//        authCode,
//        NAVER_CLIENT_ID,
//        NAVER_CLIENT_SECRET,
//        NAVER_REDIRECT_URI,
//        NAVER_GRANT_TYPE
//    );
//
//    return accessToken;
//  }
//
//  public OAuthUserInfoDto getOAuthInfo(String accessToken) {
//    KakaoInfoDto info = TKakaoUserClient.getInfo(accessToken);
//
//    return OAuthUserInfoDto.builder()
//        .snsId(info.getId())
//        .snsType("kakao")
//        .build();
//  }
//
//}
