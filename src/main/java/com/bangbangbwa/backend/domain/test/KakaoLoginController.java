//package com.bangbangbwa.backend.domain.test;
//
//
//import com.bangbangbwa.backend.domain.auth.common.dto.KakaoInfoDto;
//import com.bangbangbwa.backend.domain.auth.common.dto.KakaoTokenDto;
//import com.bangbangbwa.backend.domain.auth.common.dto.OAuthTokenDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/test")
//public class KakaoLoginController {
//
//  private final TKakaoUserProvider TKakaoUserProvider;
//  private final TKakaoUserClient TKakaoUserClient;
//
//  @Value("${oauth2.client.registration.naver.client-id}") String clientId;
//  @Value("${oauth2.client.registration.naver.client-secret}") String clientSecret;
//  @Value("http://localhost:8080/test/kakao") String redirectUri;
//  @Value("authorization_code") String grantType;
//
////  @GetMapping("/kakao")
////  public void kakaoLoginCallback(@RequestParam String code) {
////
////    System.out.println("요청");
////    KakaoTokenDto tokenResponse = TKakaoUserProvider.getAccessToken(code);
////
////    System.out.println("야호 : " + tokenResponse);
////
////    System.out.println("액세스 " +tokenResponse.getAccessToken());
////
////  }
//
////  @GetMapping("/kakao")
////  public String kakaoLoginCallback(@RequestParam String code) {
////
////    System.out.println(code);
////
////    KakaoInfoDto kakaoInfoDto = kakaoUserClient.getInfo(code);
////
////    System.out.println("id : "+ kakaoInfoDto.getId());
////
////    return kakaoInfoDto.getId();
////  }
//
//
//  @GetMapping("/kakao")
//  public ResponseEntity<KakaoInfoDto> kakaoLoginCallback(@RequestParam String code) {
//
//    System.out.println("요청");
//    KakaoTokenDto tokenResponse = TKakaoUserProvider.getAccessToken(code);
//    String accessToken = tokenResponse.getAccessToken();
//
//    System.out.println("액세스 " +accessToken);
//    KakaoInfoDto userInfo = TKakaoUserClient.getInfo(accessToken);
//
//    System.out.println("정보 " + userInfo.getId());
//    return ResponseEntity.ok(null);
//  }
//}
//
