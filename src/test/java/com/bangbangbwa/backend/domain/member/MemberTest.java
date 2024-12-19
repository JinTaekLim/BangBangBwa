package com.bangbangbwa.backend.domain.member;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.bangbangbwa.backend.domain.member.common.dto.FollowDto;
import com.bangbangbwa.backend.domain.member.common.dto.FollowDto.FollowResponse;
import com.bangbangbwa.backend.domain.member.common.dto.FollowerDto;
import com.bangbangbwa.backend.domain.member.common.dto.FollowerDto.FollowerResponse;
import com.bangbangbwa.backend.domain.member.common.dto.PostDto;
import com.bangbangbwa.backend.domain.member.common.dto.PromoteStreamerDto;
import com.bangbangbwa.backend.domain.member.common.dto.ToggleFollowDto;
import com.bangbangbwa.backend.domain.member.common.dto.ToggleFollowDto.Request;
import com.bangbangbwa.backend.domain.member.common.dto.TogglePostPinDto;
import com.bangbangbwa.backend.domain.member.common.entity.Follow;
import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.member.common.enums.Role;
import com.bangbangbwa.backend.domain.member.exception.NotFoundFollowException;
import com.bangbangbwa.backend.domain.member.exception.UnAuthenticationMemberException;
import com.bangbangbwa.backend.domain.member.repository.FollowRepository;
import com.bangbangbwa.backend.domain.member.repository.MemberRepository;
import com.bangbangbwa.backend.domain.oauth.common.dto.OAuthInfoDto;
import com.bangbangbwa.backend.domain.oauth.common.enums.SnsType;
import com.bangbangbwa.backend.domain.post.common.entity.Post;
import com.bangbangbwa.backend.domain.post.common.enums.PostType;
import com.bangbangbwa.backend.domain.sns.exception.DuplicatePendingPromotionException;
import com.bangbangbwa.backend.domain.sns.exception.MaxPinnedPostsExceededException;
import com.bangbangbwa.backend.domain.sns.repository.PostRepository;
import com.bangbangbwa.backend.domain.streamer.common.entity.PendingStreamer;
import com.bangbangbwa.backend.domain.streamer.repository.PendingStreamerRepository;
import com.bangbangbwa.backend.domain.token.business.TokenProvider;
import com.bangbangbwa.backend.domain.token.common.dto.TokenDto;
import com.bangbangbwa.backend.domain.token.common.exception.AuthenticationRequiredException;
import com.bangbangbwa.backend.global.response.ApiResponse;
import com.bangbangbwa.backend.global.test.IntegrationTest;
import com.bangbangbwa.backend.global.util.randomValue.RandomValue;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class MemberTest extends IntegrationTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private PendingStreamerRepository pendingStreamerRepository;

  @Autowired
  private TokenProvider tokenProvider;

  @Autowired
  private FollowRepository followRepository;

  @Autowired
  private PostRepository postRepository;

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

  private PendingStreamer getPendingStreamer(Member member) {
    return PendingStreamer.builder()
        .memberId(member.getId())
        .platformUrl(RandomValue.string(255).setNullable(false).get())
        .build();
  }

  private PendingStreamer createPendingStreamer(Member member) {
    PendingStreamer pendingStreamer = getPendingStreamer(member);
    pendingStreamerRepository.save(pendingStreamer);
    return pendingStreamer;
  }

  private Follow getFollow(Long followerId, Long followeeId) {
    return Follow.builder()
        .followerId(followerId)
        .followeeId(followeeId)
        .build();
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


  @Test
  void promoteStreamer() {
    // given
    Member member = createMember();
    TokenDto tokenDto = tokenProvider.getToken(member);

    String profileUrl = RandomValue.string(255).setNullable(false).get();
    PromoteStreamerDto.Request request = new PromoteStreamerDto.Request(profileUrl);

    String url = "http://localhost:" + port + "/api/v1/members/promoteStreamer";

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(tokenDto.getAccessToken());
    HttpEntity<PromoteStreamerDto.Request> requestEntity = new HttpEntity<>(request, headers);

    // when
    ResponseEntity<String> responseEntity = restTemplate.postForEntity(
        url,
        requestEntity,
        String.class
    );

    ApiResponse<PromoteStreamerDto.Response> apiResponse = gson.fromJson(
        responseEntity.getBody(),
        new TypeToken<ApiResponse<PromoteStreamerDto.Response>>() {
        }.getType()
    );
    // then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(apiResponse.getData()).isNotNull();
    assertThat(apiResponse.getData().platformUrl()).isEqualTo(profileUrl);
  }

  @Test
  void promoteStreamer_토큰_미입력() {
    // given
    String profileUrl = RandomValue.string(255).setNullable(false).get();
    PromoteStreamerDto.Request request = new PromoteStreamerDto.Request(profileUrl);

    String url = "http://localhost:" + port + "/api/v1/members/promoteStreamer";

    AuthenticationRequiredException exception = new AuthenticationRequiredException();

    // when
    ResponseEntity<String> responseEntity = restTemplate.postForEntity(
        url,
        request,
        String.class
    );

    ApiResponse<PromoteStreamerDto.Response> apiResponse = gson.fromJson(
        responseEntity.getBody(),
        new TypeToken<ApiResponse<PromoteStreamerDto.Response>>() {
        }.getType()
    );
    // then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    assertThat(apiResponse.getData()).isNull();
    assertThat(apiResponse.getCode()).isEqualTo(exception.getCode());
    assertThat(apiResponse.getMessage()).isEqualTo(exception.getMessage());
  }

  @Test
  void promoteStreamer_중복_요청() {
    // given
    Member member = createMember();
    createPendingStreamer(member);
    TokenDto tokenDto = tokenProvider.getToken(member);

    String profileUrl = RandomValue.string(255).setNullable(false).get();
    PromoteStreamerDto.Request request = new PromoteStreamerDto.Request(profileUrl);

    String url = "http://localhost:" + port + "/api/v1/members/promoteStreamer";

    DuplicatePendingPromotionException exception = new DuplicatePendingPromotionException();

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(tokenDto.getAccessToken());
    HttpEntity<PromoteStreamerDto.Request> requestEntity = new HttpEntity<>(request, headers);

    // when
    ResponseEntity<String> responseEntity = restTemplate.postForEntity(
        url,
        requestEntity,
        String.class
    );

    ApiResponse<PromoteStreamerDto.Response> apiResponse = gson.fromJson(
        responseEntity.getBody(),
        new TypeToken<ApiResponse<PromoteStreamerDto.Response>>() {
        }.getType()
    );
    // then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(apiResponse.getData()).isNull();
    assertThat(apiResponse.getCode()).isEqualTo(exception.getCode());
    assertThat(apiResponse.getMessage()).isEqualTo(exception.getMessage());
  }

  @Test()
  void getFollowers() {
    // given
    Member member = createMember();

    int followCount = RandomValue.getInt(0, 5);
    List<Member> followMember = new ArrayList<>();
    List<Follow> followerList = IntStream.range(0, followCount)
        .mapToObj(i -> {
          followMember.add(createMember());
          Follow follow = getFollow(followMember.get(i).getId(), member.getId());
          followRepository.save(follow);
          return follow;
        })
        .toList();

    String url = "http://localhost:" + port + "/api/v1/members/followers/" + member.getId();

    // when
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(
        url,
        String.class
    );

    ApiResponse<FollowerDto.Response> apiResponse = gson.fromJson(
        responseEntity.getBody(),
        new TypeToken<ApiResponse<FollowerDto.Response>>() {
        }.getType()
    );

    // then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    for (int i = 0; i < followCount; i++) {
      FollowerResponse followerResponse = apiResponse.getData().followers().get(i);
      Follow follow = followerList.get(i);

      assertThat(followerResponse.memberId()).isEqualTo(follow.getFollowerId());
      assertThat(followerResponse.profile()).isEqualTo(followMember.get(i).getProfile());
      assertThat(followerResponse.nickname()).isEqualTo(followMember.get(i).getNickname());
    }

  }


  @Test()
  void getFollows() {
    // given
    Member member = createMember();
    int followeeCount = RandomValue.getInt(0, 5);
    List<Member> followeeMember = new ArrayList<>();
    List<Follow> followeeList = IntStream.range(0, followeeCount)
        .mapToObj(i -> {
          followeeMember.add(createMember());
          Follow follow = getFollow(member.getId(), followeeMember.get(i).getId());
          followRepository.save(follow);
          return follow;
        })
        .toList();

    String url = "http://localhost:" + port + "/api/v1/members/follows/" + member.getId();

    // when
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(
        url,
        String.class
    );

    ApiResponse<FollowDto.Response> apiResponse = gson.fromJson(
        responseEntity.getBody(),
        new TypeToken<ApiResponse<FollowDto.Response>>() {
        }.getType()
    );

    // then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    for (int i = 0; i < followeeCount; i++) {
      FollowResponse followeeResponse = apiResponse.getData().follows().get(i);
      Follow followee = followeeList.get(i);

      assertThat(followeeResponse.memberId()).isEqualTo(followee.getFolloweeId());
      assertThat(followeeResponse.profile()).isEqualTo(followeeMember.get(i).getProfile());
      assertThat(followeeResponse.nickname()).isEqualTo(followeeMember.get(i).getNickname());
    }

  }

  @Test()
  void getPosts() {
    // given
    Member member = createMember();
    PostType postType = RandomValue.getRandomEnum(PostType.class);
    int postCount = 3;
    List<Post> posts = IntStream.range(0, postCount)
        .mapToObj(i -> createPost(postType, member))
        .toList();

    String url = "http://localhost:" + port + "/api/v1/members/posts/" + member.getId();

    // when
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(
        url,
        String.class
    );

    ApiResponse<PostDto.Response> apiResponse = gson.fromJson(
        responseEntity.getBody(),
        new TypeToken<ApiResponse<PostDto.Response>>() {
        }.getType()
    );

    // then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    IntStream.range(0, posts.size()).forEach(i -> {
      assertThat(apiResponse.getData().postInfos().get(i).postId()).isEqualTo(posts.get(i).getId());
//            assertThat(apiResponse.getData().postInfos().get(i).createdDate()).isEqualTo(posts.get(i).getCreatedAt());
      assertThat(apiResponse.getData().postInfos().get(i).title()).isEqualTo(
          posts.get(i).getTitle());
      assertThat(apiResponse.getData().postInfos().get(i).isPinned()).isEqualTo(
          posts.get(i).isPinned());
    });
  }

  @Test()
  void togglePostPin() {
    // given
    Member member = getMember();
    member.updateRole(Role.STREAMER);
    memberRepository.save(member);
    TokenDto tokenDto = tokenProvider.getToken(member);

    PostType postType = RandomValue.getRandomEnum(PostType.class);
    Post post = createPost(postType, member);

    boolean pinned = !post.isPinned();
    TogglePostPinDto.Request request = new TogglePostPinDto.Request(post.getId(), pinned);

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(tokenDto.getAccessToken());
    HttpEntity<TogglePostPinDto.Request> requestEntity = new HttpEntity<>(request, headers);

    String url = "http://localhost:" + port + "/api/v1/members/togglePostPin";

    // then
    ResponseEntity<String> responseEntity = restTemplate.postForEntity(
        url,
        requestEntity,
        String.class
    );

    ApiResponse<?> apiResponse = gson.fromJson(
        responseEntity.getBody(),
        new TypeToken<ApiResponse<?>>() {
        }.getType()
    );

    Post getPost = postRepository.findById(post.getId()).orElseThrow(AssertionError::new);

    // when
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(apiResponse.getData()).isNull();
    assertThat(getPost.getId()).isEqualTo(post.getId());
    assertThat(getPost.getMemberId()).isEqualTo(member.getId());
    assertThat(getPost.isPinned()).isEqualTo(pinned);
  }

  @Test()
  void togglePostPin_게시물_초과() {
    // given
    Member member = getMember();
    member.updateRole(Role.STREAMER);
    memberRepository.save(member);
    TokenDto tokenDto = tokenProvider.getToken(member);
    PostType postType = RandomValue.getRandomEnum(PostType.class);

    List<Post> posts = IntStream.range(0, 3)
        .mapToObj(i -> {
          Post post = createPost(postType, member);
          postRepository.updatePostPin(post.getId(), true);
          return post;
        }).toList();

    boolean pinned = true;
    TogglePostPinDto.Request request = new TogglePostPinDto.Request(posts.get(0).getId(), pinned);

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(tokenDto.getAccessToken());
    HttpEntity<TogglePostPinDto.Request> requestEntity = new HttpEntity<>(request, headers);

    MaxPinnedPostsExceededException exception = new MaxPinnedPostsExceededException();
    String url = "http://localhost:" + port + "/api/v1/members/togglePostPin";

    // then
    ResponseEntity<String> responseEntity = restTemplate.postForEntity(
        url,
        requestEntity,
        String.class
    );

    ApiResponse<?> apiResponse = gson.fromJson(
        responseEntity.getBody(),
        new TypeToken<ApiResponse<?>>() {
        }.getType()
    );

    // when
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(apiResponse.getData()).isNull();
    assertThat(apiResponse.getCode()).isEqualTo(exception.getCode());
    assertThat(apiResponse.getMessage()).isEqualTo(exception.getMessage());
  }


  @Test()
  void togglePostPin_토큰_미입력() {
    // given
    TogglePostPinDto.Request request = new TogglePostPinDto.Request(null, true);

    AuthenticationRequiredException exception = new AuthenticationRequiredException();
    String url = "http://localhost:" + port + "/api/v1/members/togglePostPin";

    // then
    ResponseEntity<String> responseEntity = restTemplate.postForEntity(
        url,
        request,
        String.class
    );

    ApiResponse<?> apiResponse = gson.fromJson(
        responseEntity.getBody(),
        new TypeToken<ApiResponse<?>>() {
        }.getType()
    );

    // when
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    assertThat(apiResponse.getCode()).isEqualTo(exception.getCode());
    assertThat(apiResponse.getMessage()).isEqualTo(exception.getMessage());
  }

  @Test()
  void toggleFollow() {
    // given
    Member member = createMember();
    TokenDto tokenDto = tokenProvider.getToken(member);
    Member followeeMember = createMember();

    ToggleFollowDto.Request request = new Request(followeeMember.getId(), true);

    String url = "http://localhost:" + port + "/api/v1/members/toggleFollow";

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(tokenDto.getAccessToken());
    HttpEntity<ToggleFollowDto.Request> requestEntity = new HttpEntity<>(request, headers);

    // when
    ResponseEntity<String> responseEntity = restTemplate.postForEntity(
        url,
        requestEntity,
        String.class
    );

    ApiResponse<?> apiResponse = gson.fromJson(
        responseEntity.getBody(),
        new TypeToken<ApiResponse<?>>() {
        }.getType()
    );

    // then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test()
  void toggleFollow_중복_저장() {
    // given
    Member member = createMember();
    TokenDto tokenDto = tokenProvider.getToken(member);
    Member followeeMember = createMember();

    Follow follow = Follow.builder()
        .followeeId(followeeMember.getId())
        .followerId(member.getId())
        .build();
    followRepository.save(follow);

    ToggleFollowDto.Request request = new Request(followeeMember.getId(), true);

    String url = "http://localhost:" + port + "/api/v1/members/toggleFollow";

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(tokenDto.getAccessToken());
    HttpEntity<ToggleFollowDto.Request> requestEntity = new HttpEntity<>(request, headers);

    // when
    ResponseEntity<String> responseEntity = restTemplate.postForEntity(
        url,
        requestEntity,
        String.class
    );

    ApiResponse<?> apiResponse = gson.fromJson(
        responseEntity.getBody(),
        new TypeToken<ApiResponse<?>>() {
        }.getType()
    );

    // then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
  }


  @Test()
  void toggleFollow_삭제() {
    // given
    Member member = createMember();
    TokenDto tokenDto = tokenProvider.getToken(member);
    Member followeeMember = createMember();

    Follow follow = Follow.builder()
        .followeeId(followeeMember.getId())
        .followerId(member.getId())
        .build();
    followRepository.save(follow);

    ToggleFollowDto.Request request = new Request(followeeMember.getId(), false);

    String url = "http://localhost:" + port + "/api/v1/members/toggleFollow";

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(tokenDto.getAccessToken());
    HttpEntity<ToggleFollowDto.Request> requestEntity = new HttpEntity<>(request, headers);

    // when
    ResponseEntity<String> responseEntity = restTemplate.postForEntity(
        url,
        requestEntity,
        String.class
    );

    ApiResponse<?> apiResponse = gson.fromJson(
        responseEntity.getBody(),
        new TypeToken<ApiResponse<?>>() {
        }.getType()
    );

    // then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test()
  void toggleFollow_존재하지않는_팔로우_삭제() {
    // given
    Member member = createMember();
    TokenDto tokenDto = tokenProvider.getToken(member);
    Member followeeMember = createMember();

    ToggleFollowDto.Request request = new Request(followeeMember.getId(), false);

    NotFoundFollowException exception = new NotFoundFollowException();

    String url = "http://localhost:" + port + "/api/v1/members/toggleFollow";

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(tokenDto.getAccessToken());
    HttpEntity<ToggleFollowDto.Request> requestEntity = new HttpEntity<>(request, headers);

    // when
    ResponseEntity<String> responseEntity = restTemplate.postForEntity(
        url,
        requestEntity,
        String.class
    );

    ApiResponse<?> apiResponse = gson.fromJson(
        responseEntity.getBody(),
        new TypeToken<ApiResponse<?>>() {
        }.getType()
    );

    // then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(apiResponse.getCode()).isEqualTo(exception.getCode());
    assertThat(apiResponse.getMessage()).isEqualTo(exception.getMessage());
  }
}

