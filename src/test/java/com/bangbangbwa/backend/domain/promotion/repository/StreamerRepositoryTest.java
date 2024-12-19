package com.bangbangbwa.backend.domain.promotion.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.member.repository.MemberRepository;
import com.bangbangbwa.backend.domain.oauth.common.dto.OAuthInfoDto;
import com.bangbangbwa.backend.domain.oauth.common.enums.SnsType;
import com.bangbangbwa.backend.domain.promotion.common.entity.Streamer;
import com.bangbangbwa.backend.global.test.MyBatisTest;
import com.bangbangbwa.backend.global.util.randomValue.RandomValue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

@Import({MemberRepository.class, StreamerRepository.class})
class StreamerRepositoryTest extends MyBatisTest {


  @Autowired
  private StreamerRepository streamerRepository;
  @Autowired
  private MemberRepository memberRepository;

  private Streamer getStreamer(Member member) {
    return Streamer.builder().memberId(member.getId()).build();
  }

  private Streamer createStreamer(Member member) {
    Streamer streamer = getStreamer(member);
    streamerRepository.save(streamer);
    return streamer;
  }

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
  @Test
  void findByMemberId() {
    // given
    Member member = createMember();
    Streamer streamer = getStreamer(member);

    // when
    streamerRepository.save(streamer);

    // then
    streamerRepository.findByMemberId(member.getId());
  }
}