package com.bangbangbwa.backend.domain.streamer.common.business;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.bangbangbwa.backend.domain.admin.common.dto.GetPendingMembers.GetPendingMemberResponse;
import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.member.repository.MemberRepository;
import com.bangbangbwa.backend.domain.oauth.common.dto.OAuthInfoDto;
import com.bangbangbwa.backend.domain.oauth.common.enums.SnsType;
import com.bangbangbwa.backend.domain.streamer.common.entity.PendingStreamer;
import com.bangbangbwa.backend.domain.streamer.repository.PendingStreamerRepository;
import com.bangbangbwa.backend.global.test.MyBatisTest;
import com.bangbangbwa.backend.global.util.randomValue.RandomValue;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

@Import({PendingStreamerRepository.class, MemberRepository.class})
class PendingStreamerReaderTest extends MyBatisTest {

  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private PendingStreamerRepository pendingStreamerRepository;

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

  private PendingStreamer getPendingStreamer(Long memberId) {
    return PendingStreamer.builder()
        .memberId(memberId)
        .platformUrl(RandomValue.string(5,15).setNullable(false).get())
        .build();
  }

  private PendingStreamer createPendingStreamer(Long memberId) {
    PendingStreamer pendingStreamer = getPendingStreamer(memberId);
    pendingStreamerRepository.save(pendingStreamer);
    return pendingStreamer;
  }

  @Test
  void findByPendingMembers() {
    // given
    int pendingCount = RandomValue.getInt(0,5);

    List<Member> memberList = new ArrayList<>();
    List<PendingStreamer> pendingStreamers = IntStream.range(0, pendingCount)
        .mapToObj(i -> {
          memberList.add(createMember());
          return createPendingStreamer(memberList.get(i).getId());
        })
        .toList();

    // then
    List<GetPendingMemberResponse> response = pendingStreamerRepository.findByPendingMembers();

    // when
    assertThat(response.size()).isEqualTo(pendingCount);

    for(int i=0; i<pendingCount; i++) {
      assertThat(response.get(i).memberId()).isEqualTo(memberList.get(i).getId());
      assertThat(response.get(i).profile()).isEqualTo(memberList.get(i).getProfile());

      assertThat(response.get(i).registrationDate().withNano(0)).isEqualTo(pendingStreamers.get(i).getCreatedAt().withNano(0));
      assertThat(response.get(i).platformUrl()).isEqualTo(pendingStreamers.get(i).getPlatformUrl());
    }
  }
}