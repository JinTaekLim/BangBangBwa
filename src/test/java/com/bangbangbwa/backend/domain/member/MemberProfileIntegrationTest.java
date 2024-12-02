package com.bangbangbwa.backend.domain.member;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bangbangbwa.backend.domain.member.common.dto.ProfileDto;
import com.bangbangbwa.backend.domain.member.common.entity.Follow;
import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.member.repository.FollowRepository;
import com.bangbangbwa.backend.domain.member.repository.MemberRepository;
import com.bangbangbwa.backend.domain.oauth.common.dto.OAuthInfoDto;
import com.bangbangbwa.backend.domain.oauth.common.enums.SnsType;
import com.bangbangbwa.backend.domain.promotion.common.entity.Streamer;
import com.bangbangbwa.backend.domain.promotion.repository.StreamerRepository;
import com.bangbangbwa.backend.domain.streamer.repository.StreamerTagRepository;
import com.bangbangbwa.backend.domain.tag.common.entity.Tag;
import com.bangbangbwa.backend.domain.tag.repository.TagRepository;
import com.bangbangbwa.backend.domain.token.business.TokenProvider;
import com.bangbangbwa.backend.domain.token.common.TokenDto;
import com.bangbangbwa.backend.global.test.IntegrationTest;
import com.bangbangbwa.backend.global.util.randomValue.RandomString;
import com.bangbangbwa.backend.global.util.randomValue.RandomValue;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

class MemberProfileIntegrationTest extends IntegrationTest {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private StreamerRepository streamerRepository;

  @Autowired
  private FollowRepository followRepository;

  @Autowired
  private TagRepository tagRepository;

  @Autowired
  private StreamerTagRepository streamerTagRepository;

  @Autowired
  private TokenProvider tokenProvider;

  private Member testMember() {
    Member member = Member.builder()
        .nickname(RandomString.getGenerator().minLength(1).maxLength(100).nullable(false).generate())
        .selfIntroduction(RandomString.getGenerator().minLength(0).maxLength(100).nullable(true).generate())
        .build();

    OAuthInfoDto oAuthInfo = OAuthInfoDto.builder()
        .snsId(RandomString.getGenerator().minLength(1).maxLength(30).nullable(false).generate())
        .email(RandomString.getGenerator().minLength(1).maxLength(100).nullable(false).generate())
        .snsType(SnsType.GOOGLE)
        .build();
    member.addOAuthInfo(oAuthInfo);
    member.updateProfile(RandomString.getGenerator().minLength(0).maxLength(255).nullable(true).generate());

    return member;
  }

  private Streamer testStreamer(Long memberId) {
    Streamer streamer = Streamer.builder()
        .memberId(memberId)
        .build();

    return streamer;
  }

  private Follow testFollow(Long memberId, Long followeeMemberId) {
    Follow follow = Follow.builder()
        .followerId(memberId)
        .followeeId(followeeMemberId)
        .build();

    return follow;
  }

  private Tag testTag(Long memberId) {
    Tag tag = Tag.builder()
        .name(RandomString.getGenerator()
            .minLength(3)
            .maxLength(10)
            .nullable(false)
            .generate())
        .createdId("member:" + memberId)
        .build();

    return tag;
  }

  @Test
  void getProfile_내_정보() throws Exception {
    Member member = testMember();
    memberRepository.save(member);
    TokenDto token = tokenProvider.getToken(member);
    String AUTHORIZATION = "Bearer " + token.getAccessToken();

    // 관심태그 1~10개
    long tagCount = RandomValue.getRandomLong(1, 10);
    List<String> tags = new ArrayList<>();
    for (int i = 0; i < tagCount; i++) {
      Tag tag = testTag(member.getId());
      tagRepository.save(tag);
      memberRepository.save(member, tag);
      tags.add(tag.getName());
    }

    ProfileDto.Response expected = new ProfileDto.Response(
        member.getProfile(),
        member.getNickname(),
        false,
        member.getSelfIntroduction(),
        tags
    );

    String URL = "/api/v1/members/profile/" + member.getId();
    mvc.perform(get(URL)
            .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.code").value(HttpStatus.OK.name()))
        .andExpect(jsonPath("$.message").value(HttpStatus.OK.getReasonPhrase()))
        .andExpect(jsonPath("$.data").isNotEmpty())
        .andExpect(jsonPath("$.data.imageUrl").value(expected.imageUrl()))
        .andExpect(jsonPath("$.data.nickname").value(expected.nickname()))
        .andExpect(jsonPath("$.data.isFollowing").value(expected.isFollowing()))
        .andExpect(jsonPath("$.data.selfIntroduction").value(expected.selfIntroduction()))
        .andExpect(jsonPath("$.data.tags").isArray())
        .andExpect(jsonPath("$.data.tags").isNotEmpty())
        .andExpect(jsonPath("$.data.tags").value(expected.tags()))
    ;
  }

  @Test
  void getProfile_내_정보_스트리머() throws Exception {
    Member member = testMember();
    memberRepository.save(member);
    TokenDto token = tokenProvider.getToken(member);
    String AUTHORIZATION = "Bearer " + token.getAccessToken();

    Streamer streamer = testStreamer(member.getId());
    streamerRepository.save(streamer);

    // 방송분야 1~10개
    long tagCount = RandomValue.getRandomLong(1, 10);
    List<String> tags = new ArrayList<>();
    for (int i = 0; i < tagCount; i++) {
      Tag tag = testTag(member.getId());
      tagRepository.save(tag);
      streamerTagRepository.save(streamer.getId(), tag);
      tags.add(tag.getName());
    }

    ProfileDto.Response expected = new ProfileDto.Response(
        member.getProfile(),
        member.getNickname(),
        false,
        member.getSelfIntroduction(),
        tags
    );

    String URL = "/api/v1/members/profile/" + member.getId();
    mvc.perform(get(URL)
            .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.code").value(HttpStatus.OK.name()))
        .andExpect(jsonPath("$.message").value(HttpStatus.OK.getReasonPhrase()))
        .andExpect(jsonPath("$.data").isNotEmpty())
        .andExpect(jsonPath("$.data.imageUrl").value(expected.imageUrl()))
        .andExpect(jsonPath("$.data.nickname").value(expected.nickname()))
        .andExpect(jsonPath("$.data.isFollowing").value(expected.isFollowing()))
        .andExpect(jsonPath("$.data.selfIntroduction").value(expected.selfIntroduction()))
        .andExpect(jsonPath("$.data.tags").isArray())
        .andExpect(jsonPath("$.data.tags").isNotEmpty())
        .andExpect(jsonPath("$.data.tags").value(expected.tags()))
    ;
  }

  @Test
  void getProfile_내_정보_관심태그_없음() throws Exception {
    Member member = testMember();
    memberRepository.save(member);
    TokenDto token = tokenProvider.getToken(member);
    String AUTHORIZATION = "Bearer " + token.getAccessToken();

    ProfileDto.Response expected = new ProfileDto.Response(
        member.getProfile(),
        member.getNickname(),
        false,
        member.getSelfIntroduction(),
        null
    );

    String URL = "/api/v1/members/profile/" + member.getId();
    mvc.perform(get(URL)
            .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.code").value(HttpStatus.OK.name()))
        .andExpect(jsonPath("$.message").value(HttpStatus.OK.getReasonPhrase()))
        .andExpect(jsonPath("$.data").isNotEmpty())
        .andExpect(jsonPath("$.data.imageUrl").value(expected.imageUrl()))
        .andExpect(jsonPath("$.data.nickname").value(expected.nickname()))
        .andExpect(jsonPath("$.data.isFollowing").value(expected.isFollowing()))
        .andExpect(jsonPath("$.data.selfIntroduction").value(expected.selfIntroduction()))
        .andExpect(jsonPath("$.data.tags").value(Matchers.nullValue()))
    ;
  }

  @Test
  void getProfile_내_정보_스트리머_관심태그_없음() throws Exception {
    Member member = testMember();
    memberRepository.save(member);
    TokenDto token = tokenProvider.getToken(member);
    String AUTHORIZATION = "Bearer " + token.getAccessToken();

    Streamer streamer = testStreamer(member.getId());
    streamerRepository.save(streamer);

    ProfileDto.Response expected = new ProfileDto.Response(
        member.getProfile(),
        member.getNickname(),
        false,
        member.getSelfIntroduction(),
        null
    );

    String URL = "/api/v1/members/profile/" + member.getId();
    mvc.perform(get(URL)
            .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.code").value(HttpStatus.OK.name()))
        .andExpect(jsonPath("$.message").value(HttpStatus.OK.getReasonPhrase()))
        .andExpect(jsonPath("$.data").isNotEmpty())
        .andExpect(jsonPath("$.data.imageUrl").value(expected.imageUrl()))
        .andExpect(jsonPath("$.data.nickname").value(expected.nickname()))
        .andExpect(jsonPath("$.data.isFollowing").value(expected.isFollowing()))
        .andExpect(jsonPath("$.data.selfIntroduction").value(expected.selfIntroduction()))
        .andExpect(jsonPath("$.data.tags").value(Matchers.nullValue()))
    ;
  }

  @Test
  void getProfile_로그인_다른_스트리머_정보() throws Exception {
    Member member1 = testMember();
    memberRepository.save(member1);
    TokenDto token = tokenProvider.getToken(member1);
    String AUTHORIZATION = "Bearer " + token.getAccessToken();

    Member member2 = testMember();
    memberRepository.save(member2);

    Streamer streamer = testStreamer(member2.getId());
    streamerRepository.save(streamer);

    // 방송분야 1~10개
    long tagCount = RandomValue.getRandomLong(1, 10);
    List<String> tags = new ArrayList<>();
    for (int i = 0; i < tagCount; i++) {
      Tag tag = testTag(member2.getId());
      tagRepository.save(tag);
      streamerTagRepository.save(streamer.getId(), tag);
      tags.add(tag.getName());
    }

    ProfileDto.Response expected = new ProfileDto.Response(
        member2.getProfile(),
        member2.getNickname(),
        false,
        member2.getSelfIntroduction(),
        tags
    );

    String URL = "/api/v1/members/profile/" + member2.getId();
    mvc.perform(get(URL)
            .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.code").value(HttpStatus.OK.name()))
        .andExpect(jsonPath("$.message").value(HttpStatus.OK.getReasonPhrase()))
        .andExpect(jsonPath("$.data").isNotEmpty())
        .andExpect(jsonPath("$.data.imageUrl").value(expected.imageUrl()))
        .andExpect(jsonPath("$.data.nickname").value(expected.nickname()))
        .andExpect(jsonPath("$.data.isFollowing").value(expected.isFollowing()))
        .andExpect(jsonPath("$.data.selfIntroduction").value(expected.selfIntroduction()))
        .andExpect(jsonPath("$.data.tags").isArray())
        .andExpect(jsonPath("$.data.tags").isNotEmpty())
        .andExpect(jsonPath("$.data.tags").value(expected.tags()))
    ;
  }

  @Test
  void getProfile_로그인_다른_사람_정보_팔로잉_true() throws Exception {
    Member member1 = testMember();
    memberRepository.save(member1);
    TokenDto token = tokenProvider.getToken(member1);
    String AUTHORIZATION = "Bearer " + token.getAccessToken();

    Member member2 = testMember();
    memberRepository.save(member2);

    // member1 -> member2를 Follow한다.
    Follow follow = testFollow(member1.getId(), member2.getId());
    followRepository.save(follow);

    // 관심태그 1~10개
    long tagCount = RandomValue.getRandomLong(1, 10);
    List<String> tags = new ArrayList<>();
    for (int i = 0; i < tagCount; i++) {
      Tag tag = testTag(member2.getId());
      tagRepository.save(tag);
      memberRepository.save(member2, tag);
      tags.add(tag.getName());
    }

    ProfileDto.Response expected = new ProfileDto.Response(
        member2.getProfile(),
        member2.getNickname(),
        true,
        member2.getSelfIntroduction(),
        tags
    );

    String URL = "/api/v1/members/profile/" + member2.getId();
    mvc.perform(get(URL)
            .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.code").value(HttpStatus.OK.name()))
        .andExpect(jsonPath("$.message").value(HttpStatus.OK.getReasonPhrase()))
        .andExpect(jsonPath("$.data").isNotEmpty())
        .andExpect(jsonPath("$.data.imageUrl").value(expected.imageUrl()))
        .andExpect(jsonPath("$.data.nickname").value(expected.nickname()))
        .andExpect(jsonPath("$.data.isFollowing").value(expected.isFollowing()))
        .andExpect(jsonPath("$.data.selfIntroduction").value(expected.selfIntroduction()))
        .andExpect(jsonPath("$.data.tags").isArray())
        .andExpect(jsonPath("$.data.tags").isNotEmpty())
        .andExpect(jsonPath("$.data.tags").value(expected.tags()))
    ;
  }

  @Test
  void getProfile_로그인_다른_사람_정보_팔로잉_false() throws Exception {
    Member member1 = testMember();
    memberRepository.save(member1);
    TokenDto token = tokenProvider.getToken(member1);
    String AUTHORIZATION = "Bearer " + token.getAccessToken();

    Member member2 = testMember();
    memberRepository.save(member2);

    // 관심태그 1~10개
    long tagCount = RandomValue.getRandomLong(1, 10);
    List<String> tags = new ArrayList<>();
    for (int i = 0; i < tagCount; i++) {
      Tag tag = testTag(member2.getId());
      tagRepository.save(tag);
      memberRepository.save(member2, tag);
      tags.add(tag.getName());
    }

    ProfileDto.Response expected = new ProfileDto.Response(
        member2.getProfile(),
        member2.getNickname(),
        false,
        member2.getSelfIntroduction(),
        tags
    );

    String URL = "/api/v1/members/profile/" + member2.getId();
    mvc.perform(get(URL)
            .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.code").value(HttpStatus.OK.name()))
        .andExpect(jsonPath("$.message").value(HttpStatus.OK.getReasonPhrase()))
        .andExpect(jsonPath("$.data").isNotEmpty())
        .andExpect(jsonPath("$.data.imageUrl").value(expected.imageUrl()))
        .andExpect(jsonPath("$.data.nickname").value(expected.nickname()))
        .andExpect(jsonPath("$.data.isFollowing").value(expected.isFollowing()))
        .andExpect(jsonPath("$.data.selfIntroduction").value(expected.selfIntroduction()))
        .andExpect(jsonPath("$.data.tags").isArray())
        .andExpect(jsonPath("$.data.tags").isNotEmpty())
        .andExpect(jsonPath("$.data.tags").value(expected.tags()))
    ;
  }

  @Test
  void getProfile_비로그인_다른_사람_정보() throws Exception {
    Member member = testMember();
    memberRepository.save(member);

    // 관심태그 1~10개
    long tagCount = RandomValue.getRandomLong(1, 10);
    List<String> tags = new ArrayList<>();
    for (int i = 0; i < tagCount; i++) {
      Tag tag = testTag(member.getId());
      tagRepository.save(tag);
      memberRepository.save(member, tag);
      tags.add(tag.getName());
    }

    ProfileDto.Response expected = new ProfileDto.Response(
        member.getProfile(),
        member.getNickname(),
        false,
        member.getSelfIntroduction(),
        tags
    );

    String URL = "/api/v1/members/profile/" + member.getId();
    mvc.perform(get(URL))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.code").value(HttpStatus.OK.name()))
        .andExpect(jsonPath("$.message").value(HttpStatus.OK.getReasonPhrase()))
        .andExpect(jsonPath("$.data").isNotEmpty())
        .andExpect(jsonPath("$.data.imageUrl").value(expected.imageUrl()))
        .andExpect(jsonPath("$.data.nickname").value(expected.nickname()))
        .andExpect(jsonPath("$.data.isFollowing").value(expected.isFollowing()))
        .andExpect(jsonPath("$.data.selfIntroduction").value(expected.selfIntroduction()))
        .andExpect(jsonPath("$.data.tags").isArray())
        .andExpect(jsonPath("$.data.tags").isNotEmpty())
        .andExpect(jsonPath("$.data.tags").value(expected.tags()))
    ;
  }

  @Test
  void getProfile_비로그인_다른_스트리머_정보() throws Exception {
    Member member = testMember();
    memberRepository.save(member);

    Streamer streamer = testStreamer(member.getId());
    streamerRepository.save(streamer);

    // 방송분야 1~10개
    long tagCount = RandomValue.getRandomLong(1, 10);
    List<String> tags = new ArrayList<>();
    for (int i = 0; i < tagCount; i++) {
      Tag tag = testTag(member.getId());
      tagRepository.save(tag);
      streamerTagRepository.save(streamer.getId(), tag);
      tags.add(tag.getName());
    }

    ProfileDto.Response expected = new ProfileDto.Response(
        member.getProfile(),
        member.getNickname(),
        false,
        member.getSelfIntroduction(),
        tags
    );

    String URL = "/api/v1/members/profile/" + member.getId();
    mvc.perform(get(URL))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.code").value(HttpStatus.OK.name()))
        .andExpect(jsonPath("$.message").value(HttpStatus.OK.getReasonPhrase()))
        .andExpect(jsonPath("$.data").isNotEmpty())
        .andExpect(jsonPath("$.data.imageUrl").value(expected.imageUrl()))
        .andExpect(jsonPath("$.data.nickname").value(expected.nickname()))
        .andExpect(jsonPath("$.data.isFollowing").value(expected.isFollowing()))
        .andExpect(jsonPath("$.data.selfIntroduction").value(expected.selfIntroduction()))
        .andExpect(jsonPath("$.data.tags").isArray())
        .andExpect(jsonPath("$.data.tags").isNotEmpty())
        .andExpect(jsonPath("$.data.tags").value(expected.tags()))
    ;
  }
}