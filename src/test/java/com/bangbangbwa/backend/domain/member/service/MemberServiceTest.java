package com.bangbangbwa.backend.domain.member.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.bangbangbwa.backend.domain.member.business.MemberReader;
import com.bangbangbwa.backend.domain.member.common.dto.CommentDto;
import com.bangbangbwa.backend.domain.member.common.dto.FollowerDto;
import com.bangbangbwa.backend.domain.member.common.dto.PostDto;
import com.bangbangbwa.backend.domain.member.common.dto.ProfileDto;
import com.bangbangbwa.backend.domain.member.common.dto.SummaryDto;
import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.oauth.common.dto.OAuthInfoDto;
import com.bangbangbwa.backend.domain.oauth.common.enums.SnsType;
import com.bangbangbwa.backend.global.test.ServiceTest;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class MemberServiceTest extends ServiceTest {

  @InjectMocks
  private MemberService memberService;
  @Mock
  private MemberReader memberReader;

  private static Long memberId = 1L;

  private Member testMember() {
    Member member = Member.builder().build();

    OAuthInfoDto oAuthInfo = OAuthInfoDto.builder()
        .snsId("bbbSnsId")
        .email("bbb@gmail.com")
        .snsType(SnsType.GOOGLE)
        .build();
    member.addOAuthInfo(oAuthInfo);

    return member;
  }

  @Test
  void getProfile() {
    // given
    Member member = testMember();
    when(memberReader.findById(memberId)).thenReturn(member);

    // when
    ProfileDto profile = memberService.getProfile(memberId);

    // then
    assertAll(
        () -> assertNotNull(profile),
        () -> assertEquals(memberId, profile.getMemberId()),
        () -> assertEquals(member.getNickname(), profile.getNickName()),
        () -> assertEquals(member.getSelfIntroduction(), profile.getSelfIntroduction()),
        () -> assertEquals(member.getInterests().size(), profile.getInterests().size()),
        () -> {
          for (int i = 0; i < member.getInterests().size(); i++) {
            assertEquals(member.getInterests().get(i), profile.getInterests().get(i));
          }
        }
    );
  }

  @Test
  void getSummary() {
    // given
    Member member = testMember();
    when(memberReader.findById(memberId)).thenReturn(member);

    // when
    SummaryDto summary = memberService.getSummary(memberId);

    // then
    assertAll(
        () -> assertNotNull(summary),
        () -> assertEquals(memberId, summary.getMemberId()),
        () -> assertEquals(member.getFollowers().size(), summary.getFollowerCount()),
        () -> assertEquals(member.getFollowings().size(), summary.getFollowingCount()),
        () -> assertEquals(member.getPosts().size(), summary.getPostCount())
    );
  }

  @Test
  void getPosts() {
    // given
    Member member = testMember();
    when(memberReader.findById(memberId)).thenReturn(member);

    // when
    List<PostDto> post = memberService.getPosts(memberId);

    // then
    assertAll(
        () -> assertNotNull(post),
        () -> assertEquals(memberId, post.get(0).getMemberId()),
        () -> assertEquals(member.getPosts().size(), post.size()),
        () -> {
          for (int i = 0; i < member.getPosts().size(); i++) {
            assertEquals(member.getPosts().get(i).getTitle(), post.get(i).getTitle());
          }
        }
    );
  }

  @Test
  void getComments() {
    // given
    Member member = testMember();
    when(memberReader.findById(memberId)).thenReturn(member);

    // when
    CommentDto comments = memberService.getComments(memberId);

    // then
    assertAll(
        () -> assertNotNull(comments),
        () -> assertNotNull(comments.getMemberId()),
        () -> assertNotNull(comments.getPostId()),
        () -> assertNotNull(comments.getCreatorName()),
        () -> assertEquals(memberId, comments.getCommentInfo().getMemberId()),
        () -> assertNotNull(comments.getCommentInfo().getContent())
    );
  }

  @Test
  void getFollowers() {
    // given
    Member member = testMember();
    when(memberReader.findById(memberId)).thenReturn(member);

    // when
    List<FollowerDto> followers = memberService.getFollowers(memberId);

    // then
    assertAll(
        () -> assertNotNull(followers),
        () -> assertEquals(member.getFollowers().size(), followers.size()),
        () -> {
          for (int i = 0; i < member.getFollowers().size(); i++) {
            assertEquals(member.getFollowers().get(i).getFolloweeMemberId(), followers.get(i).getMemberId());
            assertEquals(member.getFollowers().get(i).getMemberId(), followers.get(i).getFollowerMemberId());
            assertEquals(member.getFollowers().get(i).getName(), followers.get(i).getFollowerName());
            assertEquals(member.getFollowers().get(i).getImageUrl(), followers.get(i).getFollowerImageUrl());
          }
        }
    );
  }

}