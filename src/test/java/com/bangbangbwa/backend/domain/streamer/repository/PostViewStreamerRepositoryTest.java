package com.bangbangbwa.backend.domain.streamer.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.member.repository.MemberRepository;
import com.bangbangbwa.backend.domain.oauth.common.dto.OAuthInfoDto;
import com.bangbangbwa.backend.domain.oauth.common.enums.SnsType;
import com.bangbangbwa.backend.domain.post.common.entity.Post;
import com.bangbangbwa.backend.domain.post.common.enums.PostType;
import com.bangbangbwa.backend.domain.promotion.common.entity.Streamer;
import com.bangbangbwa.backend.domain.promotion.repository.StreamerRepository;
import com.bangbangbwa.backend.domain.sns.repository.PostRepository;
import com.bangbangbwa.backend.domain.streamer.common.entity.PostViewStreamer;
import com.bangbangbwa.backend.global.test.MyBatisTest;
import com.bangbangbwa.backend.global.util.randomValue.RandomValue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

@Import({
    MemberRepository.class,
    StreamerRepository.class,
    PostViewStreamerRepository.class,
    PostRepository.class
})
class PostViewStreamerRepositoryTest extends MyBatisTest {

  @Autowired
  private StreamerRepository streamerRepository;
  @Autowired
  private PostViewStreamerRepository postViewStreamerRepository;
  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private PostRepository postRepository;

  private Streamer getStreamer(Member member) {
    return Streamer.builder().memberId(member.getId()).build();
  }

  private Streamer createStreamer(Member member) {
    Streamer streamer = getStreamer(member);
    streamerRepository.save(streamer);
    return streamer;
  }
  private Member getMember() {
    Member member = Member.builder()
        .nickname(RandomValue.string(255).setNullable(false).get())
        .build();
    OAuthInfoDto oAuthInfo = OAuthInfoDto.builder()
        .snsId(RandomValue.string(255).setNullable(false).get())
        .email(RandomValue.string(255).setNullable(false).getEmail())
        .snsType(RandomValue.getRandomEnum(SnsType.class))
        .build();
    member.addOAuthInfo(oAuthInfo);
    member.updateProfile(RandomValue.string(255).get());

    return member;
  }

  private Member createMember() {
    Member member = getMember();
    memberRepository.save(member);
    return member;
  }

  private Post getPost(PostType postType, Member member) {
    return Post.builder()
        .postType(postType)
        .memberId(member.getId())
        .title(RandomValue.string(100).setNullable(false).get())
        .content(RandomValue.string(2000).setNullable(false).get())
        .build();
  }

  private Post createPost(PostType postType, Member writeMember) {
    Post post = getPost(postType, writeMember);
    postRepository.save(post);
    return post;
  }

  private PostViewStreamer getPostViewStreamer(Post post, Streamer streamer) {
    return PostViewStreamer.builder().postId(post.getId()).streamerId(streamer.getId()).build();
  }


  @Test
  void save() {
    // given
    Member writerMember = createMember();
    PostType postType = RandomValue.getRandomEnum(PostType.class);
    Post post = createPost(postType, writerMember);

    Member streamerMember = createMember();
    Streamer streamer = createStreamer(streamerMember);

    PostViewStreamer postViewStreamer = getPostViewStreamer(post, streamer);

    // when && then
    postViewStreamerRepository.save(postViewStreamer);
  }

  @Test
  void save_중복_저장() {
    // given
    Member writerMember = createMember();
    PostType postType = RandomValue.getRandomEnum(PostType.class);
    Post post = createPost(postType, writerMember);

    Member streamerMember = createMember();
    Streamer streamer = createStreamer(streamerMember);

    PostViewStreamer postViewStreamer = getPostViewStreamer(post, streamer);

    // when && then
    postViewStreamerRepository.save(postViewStreamer);
    postViewStreamerRepository.save(postViewStreamer);
  }
}