package com.bangbangbwa.backend.domain.tag.service;

import com.bangbangbwa.backend.domain.tag.business.TagReader;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {

  private final TagReader tagReader;

  public List<String> getTagList(String tagWord) {
    return tagReader.findAllByName(tagWord);
  }
}
