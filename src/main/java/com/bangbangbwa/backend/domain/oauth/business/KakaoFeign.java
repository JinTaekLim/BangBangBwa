package com.bangbangbwa.backend.domain.oauth.business;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "KakaoInfo")
public interface KakaoFeign {

}
