package com.bangbangbwa.backend.domain.tag.business;

import com.bangbangbwa.backend.domain.tag.common.entity.Tag;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TagManager {
  private final TagCreator tagCreator;
  private final TagReader tagReader;

  public List<Tag> getTags(List<String> tagList, String createdId) {
    return tagList.stream()
        .map(tagName -> mapTagStringToTag(tagName, createdId))
        .map(this::findOrCreateTag)
        .collect(Collectors.toList());
  }

  private Tag mapTagStringToTag(String tagName, String createdId) {
    return Tag.builder()
        .name(tagName)
        .createdId(createdId)
        .build();
  }

  private Tag findOrCreateTag(Tag tag) {
    return tagReader.findByName(tag.getName())
        .orElseGet(() -> {
          tagCreator.save(tag);
          return tag;
        });
  }


}
