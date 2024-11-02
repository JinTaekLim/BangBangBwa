//package com.bangbangbwa.backend.domain.test;
//
//import com.bangbangbwa.backend.domain.auth.common.dto.KakaoTokenDto;
//import com.bangbangbwa.backend.domain.auth.common.dto.OAuthTokenDto;
//import com.bangbangbwa.backend.domain.auth.common.dto.OAuthUserInfoDto;
//import com.bangbangbwa.backend.domain.auth.common.exception.NotSupportedSnsTypeException;
//import com.bangbangbwa.backend.domain.members.common.exception.OAuth2FeignException;
//import com.bangbangbwa.backend.domain.members.common.exception.UriSyntaxException;
//import feign.FeignException;
//import java.net.URISyntaxException;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class TOAuthUserInfoProvider {
//
//  private final TKakaoUserProvider TKakaoUserProvider;
//
//
//  public OAuthUserInfoDto provideOAuthUserInfoByAuthCode(String snsType, String authCode) {
//    try {
//      KakaoTokenDto oAuthTokenDto = requestToken(snsType, authCode);
//      String oauthToken = oAuthTokenDto.getAccessToken();
//      OAuthUserInfoDto oAuthUserInfoDto = requestUserInfo(snsType, oauthToken);
//      return oAuthUserInfoDto.addOauthToken(oauthToken);
//    } catch (URISyntaxException e) {
//      throw new UriSyntaxException();
//    } catch (FeignException e) {
//      logErrorMessage(e);
//      throw new OAuth2FeignException();
//    }
//  }
//
//  public KakaoTokenDto getAccessToken(String snsType, String authCode) {
//    try {
//      return requestToken(snsType, authCode);
//    } catch (URISyntaxException e) {
//      throw new UriSyntaxException();
//    }
//  }
//
////  public OAuthUserInfoDto provideOAuthUserInfoByOAuthToken(String snsType, String oauthToken) {
////    try {
////      return requestUserInfo(snsType, oauthToken);
////    } catch (URISyntaxException e) {
////      throw new UriSyntaxException();
////    } catch (FeignException e) {
////      logErrorMessage(e);
////      throw new OAuth2FeignException();
////    }
////  }
////
//  private void logErrorMessage(FeignException e) {
//    log.error("\n url : {}, \n method : {}, \n content : {}", e.request().url(),
//        e.request().httpMethod().name(), e.contentUTF8());
//  }
//
//  private KakaoTokenDto requestToken(String snsType, String authCode)
//      throws URISyntaxException, FeignException {
//    return switch (snsType) {
//      case "google" -> null;
//      case "kakao" -> TKakaoUserProvider.getAccessToken(authCode);
//      case "naver" -> null;
//      default -> throw new NotSupportedSnsTypeException();
//    };
//
//  }
//
//  private OAuthUserInfoDto requestUserInfo(String snsType, String accessToken)
//      throws URISyntaxException, FeignException {
//    return switch (snsType) {
//      case "google" -> null;
//      case "kakao" -> TKakaoUserProvider.getOAuthInfo(accessToken);
//      case "naver" -> null;
//      default -> throw new NotSupportedSnsTypeException();
//    };
//  }
//}
