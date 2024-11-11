package com.bangbangbwa.backend.domain.oauth.business.feign;

import com.bangbangbwa.backend.domain.oauth.common.dto.GoogleTokenDto;
import com.bangbangbwa.backend.domain.oauth.common.dto.NaverTokenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "NaverOAuth", url = "https://nid.naver.com/oauth2.0/token")
public interface NaverTokenFeign {

  @PostMapping
  NaverTokenDto getAccessToken(
      @RequestParam("grant_type") String grantType,
      @RequestParam("client_id") String clientId,
      @RequestParam("client_secret") String clientSecret,
      @RequestParam("code") String code,
      @RequestParam("state") String state
  );
}
