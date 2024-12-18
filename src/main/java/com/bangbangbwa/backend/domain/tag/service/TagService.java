package com.bangbangbwa.backend.domain.tag.service;

import com.bangbangbwa.backend.domain.member.business.MemberProvider;
import com.bangbangbwa.backend.domain.tag.business.TagManager;
import com.bangbangbwa.backend.domain.tag.business.TagReader;
import com.bangbangbwa.backend.domain.tag.common.dto.TagDto;
import com.bangbangbwa.backend.domain.tag.common.entity.Tag;
import com.bangbangbwa.backend.domain.tag.common.mapper.TagMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {

  private final TagReader tagReader;
  private final MemberProvider memberProvider;
  private final TagManager tagManager;

  public List<String> getTagList(String tagWord) {
    return tagReader.findAllByName(tagWord);
  }

  public List<TagDto> getTagList(List<String> tagList) {
    Long memberId = memberProvider.getCurrentMemberId();
    List<Tag> tags = tagManager.getTags(tagList, memberId.toString());
    return TagMapper.INSTANCE.toDto(tags);
  }
}
