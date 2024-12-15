package com.bangbangbwa.backend.domain.member;

import com.bangbangbwa.backend.domain.member.common.dto.FollowDto;
import com.bangbangbwa.backend.domain.member.common.dto.FollowDto.FollowerResponse;
import com.bangbangbwa.backend.domain.member.common.dto.PromoteStreamerDto;
import com.bangbangbwa.backend.domain.member.common.entity.Follow;
import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.member.exception.UnAuthenticationMemberException;
import com.bangbangbwa.backend.domain.member.repository.FollowRepository;
import com.bangbangbwa.backend.domain.member.repository.MemberRepository;
import com.bangbangbwa.backend.domain.oauth.common.dto.OAuthInfoDto;
import com.bangbangbwa.backend.domain.oauth.common.enums.SnsType;
import com.bangbangbwa.backend.domain.sns.exception.DuplicatePendingPromotionException;
import com.bangbangbwa.backend.domain.streamer.common.entity.PendingStreamer;
import com.bangbangbwa.backend.domain.streamer.repository.PendingStreamerRepository;
import com.bangbangbwa.backend.domain.token.business.TokenProvider;
import com.bangbangbwa.backend.domain.token.common.TokenDto;
import com.bangbangbwa.backend.global.response.ApiResponse;
import com.bangbangbwa.backend.global.test.IntegrationTest;
import com.bangbangbwa.backend.global.util.randomValue.RandomValue;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MemberTest extends IntegrationTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private PendingStreamerRepository pendingStreamerRepository;

  @Autowired
  private TokenProvider tokenProvider;

  @Autowired
  private FollowRepository followRepository;

  private Member getMember() {
    Member member = Member.builder()
        .nickname(RandomValue.string(255).setNullable(false).get())
        .build();
    OAuthInfoDto oAuthInfo = OAuthInfoDto.builder()
        .snsId(RandomValue.string(255).setNullable(false).get())
        .email(RandomValue.string(255).setNullable(false).getEmail())
        .snsType(RandomValue.getRandomEnum(SnsType.class))
        .build();
    member.addOAuthInfo(oAuthInfo);
    member.updateProfile(RandomValue.string(255).get());

    return member;
  }

  private Member createMember() {
    Member member = getMember();
    memberRepository.save(member);
    return member;
  }

  private PendingStreamer getPendingStreamer(Member member) {
    return PendingStreamer.builder()
        .memberId(member.getId())
        .platformUrl(RandomValue.string(255).setNullable(false).get())
        .build();
  }

  private PendingStreamer createPendingStreamer(Member member) {
    PendingStreamer pendingStreamer = getPendingStreamer(member);
    pendingStreamerRepository.save(pendingStreamer);
    return pendingStreamer;
  }

  private Follow getFollow(Long followerId, Long followeeId) {
    return Follow.builder()
        .followerId(followerId)
        .followeeId(followeeId)
        .build();
  }


  @Test
  void promoteStreamer() {
    // given
    Member member = createMember();
    TokenDto tokenDto = tokenProvider.getToken(member);

    String profileUrl = RandomValue.string(255).setNullable(false).get();
    PromoteStreamerDto.Request request = new PromoteStreamerDto.Request(profileUrl);

    String url = "http://localhost:" + port + "/api/v1/members/promoteStreamer";

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(tokenDto.getAccessToken());
    HttpEntity<PromoteStreamerDto.Request> requestEntity = new HttpEntity<>(request, headers);

    // when
    ResponseEntity<String> responseEntity = restTemplate.postForEntity(
        url,
        requestEntity,
        String.class
    );

    ApiResponse<PromoteStreamerDto.Response> apiResponse = gson.fromJson(
        responseEntity.getBody(),
        new TypeToken<ApiResponse<PromoteStreamerDto.Response>>() {
        }.getType()
    );
    // then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(apiResponse.getData()).isNotNull();
    assertThat(apiResponse.getData().platformUrl()).isEqualTo(profileUrl);
  }

  @Test
  void promoteStreamer_토큰_미입력() {
    // given
    String profileUrl = RandomValue.string(255).setNullable(false).get();
    PromoteStreamerDto.Request request = new PromoteStreamerDto.Request(profileUrl);

    String url = "http://localhost:" + port + "/api/v1/members/promoteStreamer";

    UnAuthenticationMemberException exception = new UnAuthenticationMemberException();

    // when
    ResponseEntity<String> responseEntity = restTemplate.postForEntity(
        url,
        request,
        String.class
    );

    ApiResponse<PromoteStreamerDto.Response> apiResponse = gson.fromJson(
        responseEntity.getBody(),
        new TypeToken<ApiResponse<PromoteStreamerDto.Response>>() {
        }.getType()
    );
    // then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    assertThat(apiResponse.getData()).isNull();
    assertThat(apiResponse.getCode()).isEqualTo(exception.getCode());
    assertThat(apiResponse.getMessage()).isEqualTo(exception.getMessage());
  }

  @Test
  void promoteStreamer_중복_요청() {
    // given
    Member member = createMember();
    createPendingStreamer(member);
    TokenDto tokenDto = tokenProvider.getToken(member);

    String profileUrl = RandomValue.string(255).setNullable(false).get();
    PromoteStreamerDto.Request request = new PromoteStreamerDto.Request(profileUrl);

    String url = "http://localhost:" + port + "/api/v1/members/promoteStreamer";

    DuplicatePendingPromotionException exception = new DuplicatePendingPromotionException();

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(tokenDto.getAccessToken());
    HttpEntity<PromoteStreamerDto.Request> requestEntity = new HttpEntity<>(request, headers);

    // when
    ResponseEntity<String> responseEntity = restTemplate.postForEntity(
        url,
        requestEntity,
        String.class
    );

    ApiResponse<PromoteStreamerDto.Response> apiResponse = gson.fromJson(
        responseEntity.getBody(),
        new TypeToken<ApiResponse<PromoteStreamerDto.Response>>() {
        }.getType()
    );
    // then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(apiResponse.getData()).isNull();
    assertThat(apiResponse.getCode()).isEqualTo(exception.getCode());
    assertThat(apiResponse.getMessage()).isEqualTo(exception.getMessage());
  }

  @Test()
  void getFollowers() {
    // given
    Member member = createMember();

    int followCount = RandomValue.getInt(0, 5);
    List<Member> followMember = new ArrayList<>();
    List<Follow> followList = IntStream.range(0, followCount)
        .mapToObj(i -> {
            followMember.add(createMember());
          Follow follow = getFollow(followMember.get(i).getId(), member.getId());
          followRepository.save(follow);
          return follow;
        })
        .toList();

    String url = "http://localhost:" + port + "/api/v1/members/followers/" + member.getId();

    // when
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(
        url,
        String.class
    );

    ApiResponse<FollowDto.Response> apiResponse = gson.fromJson(
        responseEntity.getBody(),
        new TypeToken<ApiResponse<FollowDto.Response>>() {
        }.getType()
    );

    // then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    for(int i=0; i<followCount; i++) {
        FollowerResponse followerResponse = apiResponse.getData().followers().get(i);
        Follow follow = followList.get(i);

        assertThat(followerResponse.memberId()).isEqualTo(follow.getFollowerId());
        assertThat(followerResponse.profile()).isEqualTo(followMember.get(i).getProfile());
        assertThat(followerResponse.nickname()).isEqualTo(followMember.get(i).getNickname());
    }

  }
}