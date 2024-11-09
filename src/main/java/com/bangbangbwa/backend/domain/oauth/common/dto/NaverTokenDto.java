package com.bangbangbwa.backend.domain.oauth.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NaverTokenDto(
    @JsonProperty("access_token")
    String accessToken
) {

}
