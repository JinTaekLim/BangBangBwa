package com.bangbangbwa.backend.domain.member.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.bangbangbwa.backend.domain.member.common.dto.FollowDto.FollowResponse;
import com.bangbangbwa.backend.domain.member.common.dto.FollowerDto.FollowerResponse;
import com.bangbangbwa.backend.domain.member.common.entity.Follow;
import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.oauth.common.dto.OAuthInfoDto;
import com.bangbangbwa.backend.domain.oauth.common.enums.SnsType;
import com.bangbangbwa.backend.global.test.MyBatisTest;
import com.bangbangbwa.backend.global.util.randomValue.RandomValue;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;


@Import({
    MemberRepository.class,
    FollowRepository.class,
})
class FollowRepositoryTest extends MyBatisTest {

  @Autowired
  private MemberRepository memberRepository;
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

  private Follow getFollow(Long followerId, Long followeeId) {
    return Follow.builder()
        .followerId(followerId)
        .followeeId(followeeId)
        .build();
  }

  @Test
  void findFollowersByMemberId() {
    // given
    Member member = createMember();

    int followCount = 3;
    IntStream.range(0, followCount)
        .forEach(i -> {
          Member followMember = createMember();
          Follow follow = getFollow(followMember.getId(), member.getId());
          followRepository.save(follow);
        });

    // when
    List<FollowerResponse> responses = followRepository.findFollowersByMemberId(member.getId());

    // then
    assertThat(responses.size()).isEqualTo(followCount);
  }

  @Test
  void findFollowsByMemberId() {
    // given
    Member member = createMember();

    int followCount = 3;
    IntStream.range(0, followCount)
        .forEach(i -> {
          Member followeeMember = createMember();
          Follow follow = getFollow(member.getId(), followeeMember.getId());
          followRepository.save(follow);
        });

    // when
    List<FollowResponse> responses = followRepository.findFollowsByMemberId(member.getId());

    // then
    assertThat(responses.size()).isEqualTo(followCount);
  }
}