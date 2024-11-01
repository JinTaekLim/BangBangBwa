package com.bangbangbwa.backend.domain.oauth.business.feign;

import com.bangbangbwa.backend.domain.oauth.common.dto.KakaoTokenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "KakaoOAuth", url = "https://kauth.kakao.com")
public interface KakaoTokenFeign {

  @GetMapping(value = "/oauth/token")
  KakaoTokenDto getAccessToken(
      @RequestParam("code") String code,
      @RequestParam("client_id") String clientId,
      @RequestParam("client_secret") String clientSecret,
      @RequestParam("redirect_uri") String redirectUri,
      @RequestParam("grant_type") String grantType
  );
}
