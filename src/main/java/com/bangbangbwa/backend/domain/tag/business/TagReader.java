package com.bangbangbwa.backend.domain.tag.business;

import com.bangbangbwa.backend.domain.tag.common.entity.Tag;
import com.bangbangbwa.backend.domain.tag.repository.TagRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TagReader {

  private final TagRepository tagRepository;

  public Optional<Tag> findByName(String name) {
    return tagRepository.findByName(name);
  }

}
