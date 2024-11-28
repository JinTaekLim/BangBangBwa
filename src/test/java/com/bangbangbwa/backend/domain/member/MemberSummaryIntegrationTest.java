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
import com.bangbangbwa.backend.domain.token.business.TokenProvider;
import com.bangbangbwa.backend.global.test.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

class MemberSummaryIntegrationTest extends IntegrationTest {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private StreamerRepository streamerRepository;

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
        // TODO: 스트리머 내부 데이터 채워주기
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

    
    // 팔로워 3명
    Member member1 = testMember();
    memberRepository.save(member1);
    followRepository.save(testFollow(member1.getId(), member.getId()));
    Member member2 = testMember();
    memberRepository.save(member2);
    followRepository.save(testFollow(member2.getId(), member.getId()));
    Member member3 = testMember();
    memberRepository.save(member3);
    followRepository.save(testFollow(member3.getId(), member.getId()));
    
    // 팔로잉 1명
    followRepository.save(testFollow(member.getId(), member1.getId()));

    // 게시글 5개
    postRepository.save(testPost(member.getId()));
    postRepository.save(testPost(member.getId()));
    postRepository.save(testPost(member.getId()));
    postRepository.save(testPost(member.getId()));
    postRepository.save(testPost(member.getId()));

    streamerRepository.save(testStreamer(member.getId()));
    
    SummaryDto.Response expected = new SummaryDto.Response(
        3L,
        1L,
        5L,
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
    ;
  }

  @Test
  void getSummary_스트리머_다른사람_정보() throws Exception {
  }

  @Test
  void getSummary_스트리머_신청중_내_정보() throws Exception {
  }

  @Test
  void getSummary_스트리머_신청중_다른사람_정보() throws Exception {
  }

  @Test
  void getSummary_일반인_내_정보() throws Exception {
  }

  @Test
  void getSummary_일반인_다른사람_정보() throws Exception {
  }
}