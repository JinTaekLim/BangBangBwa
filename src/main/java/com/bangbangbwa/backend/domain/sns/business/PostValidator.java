package com.bangbangbwa.backend.domain.sns.business;

import com.bangbangbwa.backend.domain.sns.common.entity.Post;
import com.bangbangbwa.backend.domain.sns.common.enums.VisibilityType;
import com.bangbangbwa.backend.domain.sns.exception.InvalidMemberVisibilityException;
import com.bangbangbwa.backend.domain.sns.exception.MaxPinnedPostsExceededException;
import com.bangbangbwa.backend.domain.sns.exception.NotFoundPostOrPermissionException;
import com.bangbangbwa.backend.domain.sns.repository.PostRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostValidator {

  private final PostRepository postRepository;

  public VisibilityType validateMembers(List<Long> publicMembers, List<Long> privateMembers) {
    if (!publicMembers.isEmpty() && !privateMembers.isEmpty()) {
      throw new InvalidMemberVisibilityException();
    }
    if (publicMembers.isEmpty() && privateMembers.isEmpty()) return null;
    return !publicMembers.isEmpty() ? VisibilityType.PUBLIC : VisibilityType.PRIVATE;
  }

  public void validatePostWriter(Long postId, Long memberId) {
    postRepository.findPostByIdAndMemberId(postId, memberId)
        .orElseThrow(NotFoundPostOrPermissionException::new);
  }

  public void validatePostPinLimit(Long memberId) {
    List<Post> posts = postRepository.findPinnedPostsByMemberId(memberId);
    if (posts.size() >= 3) throw new MaxPinnedPostsExceededException();
  }

}
