package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.sns.common.entity.Post;
import com.bangbangbwa.backend.domain.sns.common.enums.PostType;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostProvider {

  private final PostReader postReader;

  public List<Post> getRandomPost(PostType postType) {
    List<Post> postList = postReader.findAllByPostType(postType);
    Collections.shuffle(postList);
    return postList.subList(0, Math.min(postList.size(), 5));
  }
}
