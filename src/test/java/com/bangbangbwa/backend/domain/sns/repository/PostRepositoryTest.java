package com.bangbangbwa.backend.domain.sns.repository;

import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.member.repository.MemberRepository;
import com.bangbangbwa.backend.domain.oauth.common.dto.OAuthInfoDto;
import com.bangbangbwa.backend.domain.oauth.common.enums.SnsType;
import com.bangbangbwa.backend.domain.sns.common.dto.GetLatestPostsDto;
import com.bangbangbwa.backend.domain.sns.common.entity.Post;
import com.bangbangbwa.backend.domain.sns.common.enums.PostType;
import com.bangbangbwa.backend.global.test.MyBatisTest;
import com.bangbangbwa.backend.global.util.randomValue.RandomValue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Import({PostRepository.class, MemberRepository.class})
class PostRepositoryTest extends MyBatisTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;


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

    @Test()
    void findPostsWithinLast24Hours() {
        // given
        Member writeMember = createMember();
        PostType postType = RandomValue.getRandomEnum(PostType.class);

        int readPostCount = RandomValue.getInt(0, 5);
        int newPostCount = RandomValue.getInt(0, 5);

        Set<String> readerPostList = IntStream.range(0, readPostCount)
                .mapToObj(i -> createPost(postType, writeMember).getId().toString())
                .collect(Collectors.toSet());

        List<Long> newPostList = IntStream.range(0, newPostCount)
                .mapToObj(i -> createPost(postType, writeMember))
                .map(Post::getId)
                .sorted(Comparator.naturalOrder())
                .toList();

        // when
        List<GetLatestPostsDto> postList = postRepository.findPostsWithinLast24Hours(postType, readerPostList);

        // then
        for(GetLatestPostsDto post : postList) {
            List<Long> sortedPostIds = Arrays.stream(post.getPostIdList().split(","))
                    .map(Long::valueOf)
                    .sorted()
                    .toList();

            assertThat(sortedPostIds).isEqualTo(newPostList);
        }
    }
}