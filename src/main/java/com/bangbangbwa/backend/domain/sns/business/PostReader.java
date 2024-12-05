package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.sns.common.dto.GetLatestPostsDto;
import com.bangbangbwa.backend.domain.sns.common.dto.GetPostDetailsDto;
import com.bangbangbwa.backend.domain.sns.common.entity.Post;
import com.bangbangbwa.backend.domain.sns.common.enums.PostType;
import com.bangbangbwa.backend.domain.sns.exception.NotFoundPostException;
import com.bangbangbwa.backend.domain.sns.repository.PostRepository;
import java.util.List;
import java.util.Set;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostReader {

  private final PostRepository postRepository;

  public Post findById(Long postId) {
    return postRepository.findById(postId).orElseThrow(NotFoundPostException::new);
  }

  public GetPostDetailsDto.Response getPostDetails(Long postId, Long memberId) {
    return postRepository.getPostDetails(postId, memberId).orElseThrow(NotFoundPostException::new);
  }

  public List<Post> findAllByPostType(PostType postType) {
    return postRepository.findAllByPostType(postType);
  }

  public List<Post> findPostsByPostTypeWithLimit(PostType postType, int size) {
    return postRepository.findPostsByPostTypeWithLimit(postType, size);
  }

  public List<GetLatestPostsDto> findPostsWithinLast24Hours(PostType postType, Set<String> readPostIds) {
    return postRepository.findPostsWithinLast24Hours(postType, readPostIds);
  }

  public List<Post> findRandomPostsExcludingReadIds(PostType postType, int limit, Set<String> readPostIds) {
    return postRepository.findByPostTypeAndRandomPostsExcludingReadIds(postType, limit, readPostIds);
  }

  public List<Post> findPostsByStreamerTagsExcludingReadIds(int limit, List<Long> tagIds, Set<String> readPostIds) {
    return postRepository.findPostsByStreamerTagsExcludingReadIds(limit, tagIds, readPostIds);
  }

  public List<Post> findPostsByStreamerAndMemberIdsExcludingReadIds(
          int limit,
          List<Long> memberIds,
          Set<String> readPostIds
  ) {
    return postRepository.findPostsByStreamerAndMemberIdsExcludingReadIds(limit, memberIds, readPostIds);
  }
}
