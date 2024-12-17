package com.bangbangbwa.backend.domain.post.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.member.repository.MemberRepository;
import com.bangbangbwa.backend.domain.oauth.common.dto.OAuthInfoDto;
import com.bangbangbwa.backend.domain.oauth.common.enums.SnsType;
import com.bangbangbwa.backend.domain.post.common.entity.Post;
import com.bangbangbwa.backend.domain.post.common.enums.PostStatus;
import com.bangbangbwa.backend.domain.post.common.enums.PostType;
import com.bangbangbwa.backend.domain.sns.repository.PostRepository;
import com.bangbangbwa.backend.global.test.MyBatisTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.util.ReflectionTestUtils;

@Import({PostRepository.class, MemberRepository.class})
public class PostUpdateRepositoryTest extends MyBatisTest {

  @Autowired
  private PostRepository postRepository;
  @Autowired
  private MemberRepository memberRepository;

  private Member createMember() {
    Member newMember = Member.builder()
        .nickname("nickname")
        .usageAgree(true)
        .personalAgree(true)
        .withdrawalAgree(true)
        .build();
    OAuthInfoDto oAuthInfoDto = OAuthInfoDto.builder()
        .snsId("snsId")
        .email("email@example.com")
        .snsType(SnsType.GOOGLE)
        .build();
    newMember.addOAuthInfo(oAuthInfoDto);
    return newMember;
  }

  private Post createPost() {
    Post newPost = Post.builder()
        .postType(PostType.STREAMER)
        .title("title")
        .content("content")
        .memberId(6L)
        .build();
    ReflectionTestUtils.setField(newPost, "id", 1L);
    return newPost;
  }

  @Test
  void updatePost() {
    //given
    Member member = createMember();
    memberRepository.save(member);
    Post post = createPost();
    postRepository.save(post);
    post.deletePost();

    // when
    Post newPost = postRepository.updatePost(post);

    //then
    assertEquals(post.getId(), newPost.getId());
    assertEquals(newPost.getStatus(), PostStatus.DELETED);
    assertNotNull(newPost.getDeletedAt());
  }
}
