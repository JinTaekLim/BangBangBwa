package com.bangbangbwa.backend.domain.oauth.business.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "NaverInfo")
public interface NaverFeign {

}
