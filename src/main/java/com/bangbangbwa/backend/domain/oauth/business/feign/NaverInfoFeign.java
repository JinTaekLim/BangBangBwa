package com.bangbangbwa.backend.domain.oauth.business.feign;

import com.bangbangbwa.backend.domain.oauth.common.dto.NaverInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "NaverResource", url = "https://openapi.naver.com/v1/nid/me")
public interface NaverInfoFeign {

  @GetMapping
  NaverInfoDto requestInfo(
      @RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken,
      @RequestHeader(HttpHeaders.CONTENT_TYPE) String contentType
  );
}
