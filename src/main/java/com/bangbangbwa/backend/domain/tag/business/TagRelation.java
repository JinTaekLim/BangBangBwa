package com.bangbangbwa.backend.domain.tag.business;

import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.member.common.enums.Role;
import com.bangbangbwa.backend.domain.promotion.business.StreamerReader;
import com.bangbangbwa.backend.domain.promotion.common.entity.Streamer;
import com.bangbangbwa.backend.domain.tag.common.dto.TagDto;
import com.bangbangbwa.backend.domain.tag.repository.MemberTagRepository;
import com.bangbangbwa.backend.domain.tag.repository.StreamerTagRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TagRelation {

  private final MemberTagRepository memberTagRepository;
  private final StreamerTagRepository streamerTagRepository;
  private final StreamerReader streamerReader;

  public void relation(Member member, List<TagDto> tagList) {
    Role role = member.getRole();
    List<Long> tagIdList = tagList.stream().map(TagDto::getId).toList();
    if (role.equals(Role.STREAMER)) {
      Streamer streamer = streamerReader.findByMemberId(member.getId());
      streamerTagRepository.save(streamer.getId(), tagIdList);
    } else {
      memberTagRepository.save(member.getId(), tagIdList);
    }
  }

  public void breakRelation(Member member, List<TagDto> tagList) {
    Role role = member.getRole();
    List<Long> tagIdList = tagList.stream().map(TagDto::getId).toList();
    if (role.equals(Role.STREAMER)) {
      Streamer streamer = streamerReader.findByMemberId(member.getId());
      streamerTagRepository.delete(streamer.getId(), tagIdList);
    } else {
      memberTagRepository.delete(member.getId(), tagIdList);
    }
  }
}
