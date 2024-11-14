package com.bangbangbwa.backend.domain.member;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bangbangbwa.backend.domain.member.common.dto.ProfileDto;
import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.member.repository.MemberRepository;
import com.bangbangbwa.backend.domain.oauth.common.dto.OAuthInfoDto;
import com.bangbangbwa.backend.domain.oauth.common.enums.SnsType;
import com.bangbangbwa.backend.domain.token.business.TokenProvider;
import com.bangbangbwa.backend.domain.token.common.TokenDto;
import com.bangbangbwa.backend.domain.token.common.entity.Token;
import com.bangbangbwa.backend.global.test.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

class MemberIntegrationTest extends IntegrationTest {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private TokenProvider tokenProvider;

  private Member testMember() {
    Member member = Member.builder().build();

    OAuthInfoDto oAuthInfo = OAuthInfoDto.builder()
        .snsId("bbbSnsId")
        .email("bbb@gmail.com")
        .snsType(SnsType.GOOGLE)
        .build();
    member.addOAuthInfo(oAuthInfo);
    member.updateProfile("https://images.khan.co.kr/article/2024/03/05/news-p.v1.20240305.9dc707937ff0483e9f91ee16c87312dd_P1.jpg");

    return member;
  }

  @Test
  void getProfile_내_정보() throws Exception {
    Member member = testMember();
    memberRepository.save(member);
    TokenDto token = tokenProvider.getToken(member);

    ProfileDto.Response expected = new ProfileDto.Response(
        member.getProfile(),
        member.getNickname(),
        false,
        member.getSelfIntroduction(),
        null
    );

    String URL = "/api/v1/members/profile/" + member.getId();
    mvc.perform(get(URL)
            .header(HttpHeaders.AUTHORIZATION, token.getAccessToken()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.code").value(HttpStatus.OK.name()))
        .andExpect(jsonPath("$.message").value(HttpStatus.OK.getReasonPhrase()))
        .andExpect(jsonPath("$.data").isNotEmpty())
        .andExpect(jsonPath("$.data.imageUrl").value(expected.imageUrl()))
        .andExpect(jsonPath("$.data.nickName").value(expected.nickName()))
        .andExpect(jsonPath("$.data.isFollowing").value(expected.isFollowing()))
        .andExpect(jsonPath("$.data.selfIntroduction").value(expected.selfIntroduction()))
    ;
  }

  @Test
  void getProfile_다른_사람_정보_팔로잉_true() {
  }

  @Test
  void getProfile_다른_사람_정보_팔로잉_false() {
  }
}