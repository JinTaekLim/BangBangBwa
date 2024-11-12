package com.bangbangbwa.backend.domain.member.service;

import static org.junit.jupiter.api.Assertions.assertAll;
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
        () -> assertNotNull(profile)
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
        () -> assertNotNull(summary)
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
        () -> assertNotNull(post)
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
        () -> assertNotNull(comments)
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
        () -> assertNotNull(followers)
    );
  }

}