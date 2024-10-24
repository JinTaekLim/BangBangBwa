package com.bangbangbwa.backend.domain.oauth.business.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "KakaoInfo")
public interface KakaoFeign {

}
