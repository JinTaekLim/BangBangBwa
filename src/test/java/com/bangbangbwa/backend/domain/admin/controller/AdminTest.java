package com.bangbangbwa.backend.domain.admin.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.bangbangbwa.backend.domain.admin.common.dto.GetReportedPostsDto;
import com.bangbangbwa.backend.domain.member.common.dto.PromoteStreamerDto.Request;
import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.member.common.enums.Role;
import com.bangbangbwa.backend.domain.member.repository.MemberRepository;
import com.bangbangbwa.backend.domain.oauth.common.dto.OAuthInfoDto;
import com.bangbangbwa.backend.domain.oauth.common.enums.SnsType;
import com.bangbangbwa.backend.domain.post.common.entity.Post;
import com.bangbangbwa.backend.domain.post.common.enums.PostType;
import com.bangbangbwa.backend.domain.sns.common.entity.ReportPost;
import com.bangbangbwa.backend.domain.sns.repository.PostRepository;
import com.bangbangbwa.backend.domain.sns.repository.ReportPostRepository;
import com.bangbangbwa.backend.domain.token.business.TokenProvider;
import com.bangbangbwa.backend.domain.token.common.dto.TokenDto;
import com.bangbangbwa.backend.global.response.ApiResponse;
import com.bangbangbwa.backend.global.test.IntegrationTest;
import com.bangbangbwa.backend.global.util.randomValue.RandomValue;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class AdminTest extends IntegrationTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private ReportPostRepository reportPostRepository;
  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private PostRepository postRepository;
  @Autowired
  private TokenProvider tokenProvider;


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

  private ReportPost getReportPost(Post post, Member writeMember) {
    return ReportPost.builder()
        .postId(post.getId())
        .reason(RandomValue.string(3, 10).setNullable(false).get())
        .createdId(String.valueOf(writeMember.getId()))
        .build();
  }

  private ReportPost createReportPost(Post post, Member writeMember) {
    ReportPost reportPost = getReportPost(post, writeMember);
    reportPostRepository.save(reportPost);
    return reportPost;
  }

  @Test
  void updatePendingStatus() {
  }

  @Test
  void getPendingMembers() {
  }

  @Test
  void getReportedPosts() {
    // given
    Member admin = createMember();
    admin.updateRole(Role.ADMIN);
    TokenDto tokenDto = tokenProvider.getToken(admin);

    PostType postType = RandomValue.getRandomEnum(PostType.class);
    int reportPostCount = RandomValue.getInt(3, 5);

    List<Member> members = IntStream.range(0, reportPostCount)
        .mapToObj(i -> createMember())
        .toList();
    List<Post> posts = members.stream()
        .map(writeMember -> createPost(postType, writeMember))
        .toList();
    List<ReportPost> reportPosts = IntStream.range(0, reportPostCount)
        .mapToObj(i -> createReportPost(posts.get(i), members.get(i)))
        .toList();

    String url = "http://localhost:" + port + "/api/v1/admin/getReportedPosts";

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(tokenDto.getAccessToken());
    HttpEntity<Request> requestEntity = new HttpEntity<>(headers);

    // when
    ResponseEntity<String> responseEntity = restTemplate.exchange(
        url,
        HttpMethod.GET,
        requestEntity,
        String.class
    );

    ApiResponse<GetReportedPostsDto.Response> apiResponse = gson.fromJson(
        responseEntity.getBody(),
        new TypeToken<ApiResponse<GetReportedPostsDto.Response>>() {
        }.getType()
    );

    // then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(apiResponse.getData().posts().size()).isEqualTo(reportPostCount);
    IntStream.range(0, reportPostCount).forEach(i -> {
      assertThat(apiResponse.getData().posts().get(i).postId()).isEqualTo(posts.get(i).getId());
      assertThat(apiResponse.getData().posts().get(i).writerId()).isEqualTo(members.get(i).getId());
      assertThat(apiResponse.getData().posts().get(i).nickname()).isEqualTo(
          members.get(i).getNickname());
      assertThat(apiResponse.getData().posts().get(i).profile()).isEqualTo(
          members.get(i).getProfile());
      assertThat(apiResponse.getData().posts().get(i).reason()).isEqualTo(
          reportPosts.get(i).getReason());
      assertThat(apiResponse.getData().posts().get(i).reportDate().withNano(0)).isEqualTo(
          reportPosts.get(i).getCreatedAt().withNano(0));
    });
  }
}