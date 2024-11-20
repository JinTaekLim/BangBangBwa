package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.sns.common.entity.Post;
import com.bangbangbwa.backend.domain.sns.common.enums.PostType;
import com.bangbangbwa.backend.domain.sns.exception.NotFoundPostException;
import com.bangbangbwa.backend.domain.sns.repository.PostRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostReader {

  private final PostRepository postRepository;

  public Post findById(Long postId) {
    return postRepository.findById(postId).orElseThrow(NotFoundPostException::new);
  }

  public List<Post> findAllByPostType(PostType postType) {
    return postRepository.findAllByPostType(postType.name());
  }

}
