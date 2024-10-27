package com.bangbangbwa.backend.domain.oauth.business.feign;

import com.bangbangbwa.backend.domain.oauth.common.dto.GoogleInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "GoogleInfo", url = "https://www.googleapis.com/oauth2/v3/userinfo")
public interface GoogleInfoFeign {

  @GetMapping
  GoogleInfoDto requestInfo(
      @RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken,
      @RequestHeader(HttpHeaders.CONTENT_TYPE) String contentType
  );
}
