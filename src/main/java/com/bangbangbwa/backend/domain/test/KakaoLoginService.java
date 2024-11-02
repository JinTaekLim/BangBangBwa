//package com.bangbangbwa.backend.domain.auth.test;
//
//import org.springframework.stereotype.Service;
//
//@Service
//public class KakaoLoginService {
//
//  private final KakaoAuthClient kakaoAuthClient;
//  private final KakaoApiClient kakaoApiClient;
//
//  @Value("${kakao.client-id}")
//  private String clientId;
//
//  @Value("${kakao.redirect-uri}")
//  private String redirectUri;
//
//  public KakaoLoginService(KakaoAuthClient kakaoAuthClient, KakaoApiClient kakaoApiClient) {
//    this.kakaoAuthClient = kakaoAuthClient;
//    this.kakaoApiClient = kakaoApiClient;
//  }
//
//  // 인가 코드를 통해 액세스 토큰을 받아오는 메서드
//  public KakaoTokenResponse getAccessToken(String code) {
//    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//    params.add("grant_type", "authorization_code");
//    params.add("client_id", clientId);
//    params.add("redirect_uri", redirectUri);
//    params.add("code", code);
//
//    return kakaoAuthClient.getToken(params);
//  }
//
//  // 액세스 토큰을 통해 회원 정보를 확인하는 메서드
//  public KakaoUserInfoResponse getUserInfo(String accessToken) {
//    return kakaoApiClient.getUserInfo("Bearer " + accessToken);
//  }
//
//  // 회원 유무를 확인하는 전체 로직
//  public KakaoUserInfoResponse checkKakaoUser(String code) {
//    KakaoTokenResponse tokenResponse = getAccessToken(code);
//    return getUserInfo(tokenResponse.getAccess_token());
//  }
//}
