package com.bangbangbwa.backend.domain.tag.business;

import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.member.common.enums.Role;
import com.bangbangbwa.backend.domain.promotion.business.StreamerReader;
import com.bangbangbwa.backend.domain.promotion.common.entity.Streamer;
import com.bangbangbwa.backend.domain.tag.common.dto.TagDto;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TagUpdater {

  private final TagReader tagReader;
  private final TagRelation tagRelation;
  private final StreamerReader streamerReader;

  // 태그 갱신 기존의 태그 조회하여,
  // 내부에 포함되어 있는지 여부를 확인.
  // 포함되지 않은 것들은 새롭게 연결.
  // 새롭게 얻은 태그에 포함되지 않는 기존 태그들은 제거.
  public void updateTagRelations(Member member, List<TagDto> tagList) {
    Role role = member.getRole();
    List<TagDto> existingTags;

    if (role.equals(Role.STREAMER)) {
      Streamer streamer = streamerReader.findByMemberId(member.getId());
      existingTags = tagReader.findByStreamerId(streamer.getId());
    } else {
      existingTags = tagReader.findByMemberId(member.getId());
    }

    List<TagDto> newTags = new ArrayList<>();
    List<TagDto> oldTags = new ArrayList<>();
    for (TagDto tag : existingTags) {
      if (!tagList.contains(tag)) {
        oldTags.add(tag);
      }
    }
    if (!oldTags.isEmpty()) {
      tagRelation.breakRelation(member, oldTags);
    }
    for (TagDto tag : tagList) {
      if (!existingTags.contains(tag)) {
        newTags.add(tag);
      }
    }
    if (!newTags.isEmpty()) {
      tagRelation.relation(member, newTags);
    }
  }
}
