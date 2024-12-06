package com.bangbangbwa.backend.domain.member;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bangbangbwa.backend.domain.member.common.dto.SummaryDto;
import com.bangbangbwa.backend.domain.member.common.entity.Follow;
import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.member.repository.FollowRepository;
import com.bangbangbwa.backend.domain.member.repository.MemberRepository;
import com.bangbangbwa.backend.domain.oauth.common.dto.OAuthInfoDto;
import com.bangbangbwa.backend.domain.oauth.common.enums.SnsType;
import com.bangbangbwa.backend.domain.promotion.common.entity.Streamer;
import com.bangbangbwa.backend.domain.promotion.repository.StreamerRepository;
import com.bangbangbwa.backend.domain.sns.common.entity.Post;
import com.bangbangbwa.backend.domain.sns.common.enums.PostType;
import com.bangbangbwa.backend.domain.sns.repository.PostRepository;
import com.bangbangbwa.backend.domain.streamer.common.entity.PendingStreamer;
import com.bangbangbwa.backend.domain.streamer.repository.PendingStreamerRepository;
import com.bangbangbwa.backend.domain.token.business.TokenProvider;
import com.bangbangbwa.backend.domain.token.common.TokenDto;
import com.bangbangbwa.backend.global.test.IntegrationTest;
import com.bangbangbwa.backend.global.util.randomValue.RandomValue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.stream.LongStream;

class MemberSummaryIntegrationTest extends IntegrationTest {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private StreamerRepository streamerRepository;

  @Autowired
  private PendingStreamerRepository pendingStreamerRepository;

  @Autowired
  private FollowRepository followRepository;
  
  @Autowired
  private PostRepository postRepository;

  @Autowired
  private TokenProvider tokenProvider;

  private Member testMember() {
    Member member = Member.builder()
        .nickname("bbbNickname")
        .selfIntroduction("bbbSelfIntroduction")
        .build();

    OAuthInfoDto oAuthInfo = OAuthInfoDto.builder()
        .snsId("bbbSnsId")
        .email("bbb@gmail.com")
        .snsType(SnsType.GOOGLE)
        .build();
    member.addOAuthInfo(oAuthInfo);
    member.updateProfile("https://images.khan.co.kr/article/2024/03/05/news-p.v1.20240305.9dc707937ff0483e9f91ee16c87312dd_P1.jpg");

    return member;
  }

  private Streamer testStreamer(Long memberId) {
    Streamer streamer = Streamer.builder()
        .memberId(memberId)
        .build();

    return streamer;
  }

  private PendingStreamer testPendingStreamer(Long memberId) {
    PendingStreamer pendingStreamer = PendingStreamer.builder()
        .memberId(memberId)
        .platformUrl("https://www.naver.com/")
        .build();

    return pendingStreamer;
  }

  private Follow testFollow(Long memberId, Long followeeMemberId) {
    Follow follow = Follow.builder()
        .followerId(memberId)
        .followeeId(followeeMemberId)
        .build();

    return follow;
  }

  private Post testPost(Long memberId) {
    Post post = Post.builder()
        .postType(PostType.MEMBER)
        .memberId(memberId)
        .title("bbbTitle")
        .content("bbbContent")
        .publicMembers(null)
        .privateMembers(null)
        .build();

    return post;
  }

  @Test
  void getSummary_스트리머_내_정보() throws Exception {
    Member member = testMember();
    memberRepository.save(member);
    TokenDto token = tokenProvider.getToken(member);
    String AUTHORIZATION = "Bearer " + token.getAccessToken();

    // 팔로워 1~20명
    long followerCount = RandomValue.getLong(1, 20);
    LongStream.range(0, followerCount)
            .forEach(i -> {
              Member m = testMember();
              memberRepository.save(m);
              followRepository.save(testFollow(m.getId(), member.getId()));
            });

    // 팔로잉 1~20명
    long followingCount = RandomValue.getLong(1, 20);
    LongStream.range(0, followingCount)
            .forEach(i -> {
              Member m = testMember();
              memberRepository.save(m);
              followRepository.save(testFollow(member.getId(), m.getId()));
            });

    // 게시글 1~50개
    long postCount = RandomValue.getLong(1, 50);
    LongStream.range(0, postCount)
            .forEach(i -> postRepository.save(testPost(member.getId())));

    // 스트리머 등록
    streamerRepository.save(testStreamer(member.getId()));

    SummaryDto.Response expected = new SummaryDto.Response(
        followerCount,
        followingCount,
        postCount,
        true,
        false,
        null
    );

    String URL = "/api/v1/members/summary/" + member.getId();
    mvc.perform(get(URL)
            .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.code").value(HttpStatus.OK.name()))
        .andExpect(jsonPath("$.message").value(HttpStatus.OK.getReasonPhrase()))
        .andExpect(jsonPath("$.data").isNotEmpty())
        .andExpect(jsonPath("$.data.followerCount").value(expected.followerCount()))
        .andExpect(jsonPath("$.data.followingCount").value(expected.followingCount()))
        .andExpect(jsonPath("$.data.postCount").value(expected.postCount()))
        .andExpect(jsonPath("$.data.isStreamer").value(expected.isStreamer()))
        .andExpect(jsonPath("$.data.isSubmittedToStreamer").value(expected.isSubmittedToStreamer()))
    ;
  }

  @Test
  void getSummary_스트리머_다른사람_정보() throws Exception {
    Member member = testMember();
    memberRepository.save(member);

    // 팔로워 1~20명
    long followerCount = RandomValue.getLong(1, 20);
    LongStream.range(0, followerCount)
            .forEach(i -> {
              Member m = testMember();
              memberRepository.save(m);
              followRepository.save(testFollow(m.getId(), member.getId()));
            });
    
    // 팔로잉 1~20명
    long followingCount = RandomValue.getLong(1, 20);
    LongStream.range(0, followingCount)
            .forEach(i -> {
              Member m = testMember();
              memberRepository.save(m);
              followRepository.save(testFollow(member.getId(), m.getId()));
            });
    
    // 게시글 1~50개
    long postCount = RandomValue.getLong(1, 50);
    LongStream.range(0, postCount)
            .forEach(i -> postRepository.save(testPost(member.getId())));
    
    // 스트리머 등록
    streamerRepository.save(testStreamer(member.getId()));

    SummaryDto.Response expected = new SummaryDto.Response(
        followerCount,
        0L, // 다른 사람을 조회할 때에 팔로잉 수는 보여주면 안된다.
        postCount,
        true,
        false,
        null
    );

    String URL = "/api/v1/members/summary/" + member.getId();
    mvc.perform(get(URL))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.code").value(HttpStatus.OK.name()))
        .andExpect(jsonPath("$.message").value(HttpStatus.OK.getReasonPhrase()))
        .andExpect(jsonPath("$.data").isNotEmpty())
        .andExpect(jsonPath("$.data.followerCount").value(expected.followerCount()))
        .andExpect(jsonPath("$.data.followingCount").value(expected.followingCount()))
        .andExpect(jsonPath("$.data.postCount").value(expected.postCount()))
        .andExpect(jsonPath("$.data.isStreamer").value(expected.isStreamer()))
        .andExpect(jsonPath("$.data.isSubmittedToStreamer").value(expected.isSubmittedToStreamer()))
    ;
  }

  @Test
  void getSummary_스트리머_신청중_내_정보() throws Exception {
    Member member = testMember();
    memberRepository.save(member);
    TokenDto token = tokenProvider.getToken(member);
    String AUTHORIZATION = "Bearer " + token.getAccessToken();

    // 팔로워 1~20명
    long followerCount = RandomValue.getLong(1, 20);
    LongStream.range(0, followerCount)
            .forEach(i -> {
              Member m = testMember();
              memberRepository.save(m);
              followRepository.save(testFollow(m.getId(), member.getId()));
            });

    // 팔로잉 1~20명
    long followingCount = RandomValue.getLong(1, 20);
    LongStream.range(0, followingCount)
            .forEach(i -> {
              Member m = testMember();
              memberRepository.save(m);
              followRepository.save(testFollow(member.getId(), m.getId()));
            });

    // 게시글 1~50개
    long postCount = RandomValue.getLong(1, 50);
    LongStream.range(0, postCount)
            .forEach(i -> postRepository.save(testPost(member.getId())));

    // 스트리머 신청 등록
    pendingStreamerRepository.save(testPendingStreamer(member.getId()));

    SummaryDto.Response expected = new SummaryDto.Response(
        followerCount,
        followingCount,
        postCount,
        false,
        true,
        null
    );

    String URL = "/api/v1/members/summary/" + member.getId();
    mvc.perform(get(URL)
            .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.code").value(HttpStatus.OK.name()))
        .andExpect(jsonPath("$.message").value(HttpStatus.OK.getReasonPhrase()))
        .andExpect(jsonPath("$.data").isNotEmpty())
        .andExpect(jsonPath("$.data.followerCount").value(expected.followerCount()))
        .andExpect(jsonPath("$.data.followingCount").value(expected.followingCount()))
        .andExpect(jsonPath("$.data.postCount").value(expected.postCount()))
        .andExpect(jsonPath("$.data.isStreamer").value(expected.isStreamer()))
        .andExpect(jsonPath("$.data.isSubmittedToStreamer").value(expected.isSubmittedToStreamer()))
    ;
  }

  @Test
  void getSummary_스트리머_신청중_다른사람_정보() throws Exception {
    Member member = testMember();
    memberRepository.save(member);

    // 팔로워 1~20명
    long followerCount = RandomValue.getLong(1, 20);
    LongStream.range(0, followerCount).forEach(i -> {
      Member m = testMember();
      memberRepository.save(m);
      followRepository.save(testFollow(m.getId(), member.getId()));
    });

    // 팔로잉 1~20명
    long followingCount = RandomValue.getLong(1, 20);
    LongStream.range(0, followingCount).forEach(i -> {
      Member m = testMember();
      memberRepository.save(m);
      followRepository.save(testFollow(member.getId(), m.getId()));
    });

    // 게시글 1~50개
    long postCount = RandomValue.getLong(1, 50);
    LongStream.range(0, postCount).forEach(i -> postRepository.save(testPost(member.getId())));

    // 스트리머 신청 등록
    pendingStreamerRepository.save(testPendingStreamer(member.getId()));

    SummaryDto.Response expected = new SummaryDto.Response(
        followerCount,
        0L, // 다른 사람을 조회할 때에 팔로잉 수는 보여주면 안된다.
        postCount,
        false,
        false, // 다른 사람을 조회할 때에 스트리머 신청 여부를 보여주면 안된다.
        null
    );

    String URL = "/api/v1/members/summary/" + member.getId();
    mvc.perform(get(URL))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.code").value(HttpStatus.OK.name()))
        .andExpect(jsonPath("$.message").value(HttpStatus.OK.getReasonPhrase()))
        .andExpect(jsonPath("$.data").isNotEmpty())
        .andExpect(jsonPath("$.data.followerCount").value(expected.followerCount()))
        .andExpect(jsonPath("$.data.followingCount").value(expected.followingCount()))
        .andExpect(jsonPath("$.data.postCount").value(expected.postCount()))
        .andExpect(jsonPath("$.data.isStreamer").value(expected.isStreamer()))
        .andExpect(jsonPath("$.data.isSubmittedToStreamer").value(expected.isSubmittedToStreamer()))
    ;
  }

  @Test
  void getSummary_일반인_내_정보() throws Exception {
    Member member = testMember();
    memberRepository.save(member);
    TokenDto token = tokenProvider.getToken(member);
    String AUTHORIZATION = "Bearer " + token.getAccessToken();

    // 팔로워 1~20명
    long followerCount = RandomValue.getLong(1, 20);
    LongStream.range(0, followerCount).forEach(i -> {
      Member m = testMember();
      memberRepository.save(m);
      followRepository.save(testFollow(m.getId(), member.getId()));
    });

    // 팔로잉 1~20명
    long followingCount = RandomValue.getLong(1, 20);
    LongStream.range(0, followingCount).forEach(i -> {
      Member m = testMember();
      memberRepository.save(m);
      followRepository.save(testFollow(member.getId(), m.getId()));
    });

    // 게시글 1~50개
    long postCount = RandomValue.getLong(1, 50);
    LongStream.range(0, postCount).forEach(i -> postRepository.save(testPost(member.getId())));

    SummaryDto.Response expected = new SummaryDto.Response(
        followerCount,
        followingCount,
        postCount,
        false,
        false,
        null
    );

    String URL = "/api/v1/members/summary/" + member.getId();
    mvc.perform(get(URL)
            .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.code").value(HttpStatus.OK.name()))
        .andExpect(jsonPath("$.message").value(HttpStatus.OK.getReasonPhrase()))
        .andExpect(jsonPath("$.data").isNotEmpty())
        .andExpect(jsonPath("$.data.followerCount").value(expected.followerCount()))
        .andExpect(jsonPath("$.data.followingCount").value(expected.followingCount()))
        .andExpect(jsonPath("$.data.postCount").value(expected.postCount()))
        .andExpect(jsonPath("$.data.isStreamer").value(expected.isStreamer()))
        .andExpect(jsonPath("$.data.isSubmittedToStreamer").value(expected.isSubmittedToStreamer()))
    ;
  }

  @Test
  void getSummary_일반인_다른사람_정보() throws Exception {
    Member member = testMember();
    memberRepository.save(member);

    // 팔로워 1~20명
    long followerCount = RandomValue.getLong(1, 20);
    LongStream.range(0, followerCount).forEach(i -> {
      Member m = testMember();
      memberRepository.save(m);
      followRepository.save(testFollow(m.getId(), member.getId()));
    });

    // 팔로잉 1~20명
    long followingCount = RandomValue.getLong(1, 20);
    LongStream.range(0, followingCount).forEach(i -> {
      Member m = testMember();
      memberRepository.save(m);
      followRepository.save(testFollow(member.getId(), m.getId()));
    });

    // 게시글 1~50개
    long postCount = RandomValue.getLong(1, 50);
    LongStream.range(0, postCount).forEach(i -> postRepository.save(testPost(member.getId())));

    SummaryDto.Response expected = new SummaryDto.Response(
        followerCount,
        0L, // 다른 사람을 조회할 때에 팔로잉 수는 보여주면 안된다.
        postCount,
        false,
        false,
        null
    );

    String URL = "/api/v1/members/summary/" + member.getId();
    mvc.perform(get(URL))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.code").value(HttpStatus.OK.name()))
        .andExpect(jsonPath("$.message").value(HttpStatus.OK.getReasonPhrase()))
        .andExpect(jsonPath("$.data").isNotEmpty())
        .andExpect(jsonPath("$.data.followerCount").value(expected.followerCount()))
        .andExpect(jsonPath("$.data.followingCount").value(expected.followingCount()))
        .andExpect(jsonPath("$.data.postCount").value(expected.postCount()))
        .andExpect(jsonPath("$.data.isStreamer").value(expected.isStreamer()))
        .andExpect(jsonPath("$.data.isSubmittedToStreamer").value(expected.isSubmittedToStreamer()))
    ;
  }
}