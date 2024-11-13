package com.bangbangbwa.backend.domain.member.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.bangbangbwa.backend.domain.member.business.MemberReader;
import com.bangbangbwa.backend.domain.member.business.MemberValidator;
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
  @Mock
  private MemberValidator memberValidator;

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
  void getProfile_내_정보() {
    // given
    Member member = testMember();
    when(memberReader.findById(memberId)).thenReturn(member);
    when(memberValidator.isMyMemberId(memberId)).thenReturn(true);

    // when
    ProfileDto profile = memberService.getProfile(memberId);

    // then
    assertAll(
        () -> assertEquals(profile.getImageUrl(), member.getProfile()),
        () -> assertEquals(profile.getNickName(), member.getNickname()),
        () -> assertEquals(profile.getSelfIntroduction(), member.getSelfIntroduction()),
        () -> assertFalse(profile.isFollowing())
    );
  }

  @Test
  void getProfile_다른_사람_정보_팔로잉_true() {
    // given
    Member member = testMember();
    when(memberReader.findById(memberId)).thenReturn(member);
    when(memberValidator.isMyMemberId(memberId)).thenReturn(false);
    when(memberValidator.isFollowing(memberId)).thenReturn(true);

    // when
    ProfileDto profile = memberService.getProfile(memberId);

    // then
    assertAll(
        () -> assertEquals(profile.getImageUrl(), member.getProfile()),
        () -> assertEquals(profile.getNickName(), member.getNickname()),
        () -> assertEquals(profile.getSelfIntroduction(), member.getSelfIntroduction()),
        () -> assertTrue(profile.isFollowing())
    );
  }

  @Test
  void getProfile_다른_사람_정보_팔로잉_false() {
    // given
    Member member = testMember();
    when(memberReader.findById(memberId)).thenReturn(member);
    when(memberValidator.isMyMemberId(memberId)).thenReturn(false);
    when(memberValidator.isFollowing(memberId)).thenReturn(false);

    // when
    ProfileDto profile = memberService.getProfile(memberId);

    // then
    assertAll(
        () -> assertEquals(profile.getImageUrl(), member.getProfile()),
        () -> assertEquals(profile.getNickName(), member.getNickname()),
        () -> assertEquals(profile.getSelfIntroduction(), member.getSelfIntroduction()),
        () -> assertFalse(profile.isFollowing())
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
  }

  @Test
  void getPosts() {
    // given
    Member member = testMember();
    when(memberReader.findById(memberId)).thenReturn(member);

    // when
    List<PostDto> post = memberService.getPosts(memberId);

    // then
  }

  @Test
  void getComments() {
    // given
    Member member = testMember();
    when(memberReader.findById(memberId)).thenReturn(member);

    // when
    CommentDto comments = memberService.getComments(memberId);

    // then
  }

  @Test
  void getFollowers() {
    // given
    Member member = testMember();
    when(memberReader.findById(memberId)).thenReturn(member);

    // when
    List<FollowerDto> followers = memberService.getFollowers(memberId);

    // then
  }

}