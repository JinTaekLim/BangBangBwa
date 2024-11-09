package com.bangbangbwa.backend.domain.sns.controller;

import com.bangbangbwa.backend.domain.sns.common.dto.CreateCommentDto;
import com.bangbangbwa.backend.domain.sns.common.dto.UploadPostMediaDto;
import com.bangbangbwa.backend.domain.sns.common.entity.Comment;
import com.bangbangbwa.backend.domain.sns.common.entity.PostMedia;
import com.bangbangbwa.backend.domain.sns.common.mapper.CommentMapper;
import com.bangbangbwa.backend.domain.sns.common.mapper.PostMapper;
import com.bangbangbwa.backend.domain.sns.common.mapper.PostMediaMapper;
import com.bangbangbwa.backend.domain.sns.service.SnsService;
import com.bangbangbwa.backend.domain.sns.common.dto.CreatePostDto;
import com.bangbangbwa.backend.domain.sns.common.dto.GetFollowedLatestPostsDto;
import com.bangbangbwa.backend.domain.sns.common.dto.GetFollowedLatestPostsDto.RecentPost;
import com.bangbangbwa.backend.domain.sns.common.dto.GetFollowedLatestPostsDto.Response;
import com.bangbangbwa.backend.domain.sns.common.dto.GetPostDetailsDto;
import com.bangbangbwa.backend.domain.sns.common.dto.GetPostListDto;
import com.bangbangbwa.backend.domain.sns.common.entity.Post;
import com.bangbangbwa.backend.global.response.ApiResponse;
import com.bangbangbwa.backend.global.util.randomValue.RandomValue;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/sns")
@RequiredArgsConstructor
public class SnsController implements SnsApi{

  private final SnsService snsService;


  @GetMapping("/getFollowedLatestPosts")
  public ApiResponse<GetFollowedLatestPostsDto.Response> getFollowedLatestPosts() {

    List<RecentPost> postList = new ArrayList<>();
    int randomInt = RandomValue.getInt(5);

    for(int i=0; i<randomInt; i++) {
      postList.add(
          new RecentPost(
              RandomValue.string(20).setNullable(false).get(),
              RandomValue.string(500).setNullable(false).get(),
              RandomValue.string(50).setNullable(false).get(),
              RandomValue.getRandomBoolean()
          )
      );
    }

    GetFollowedLatestPostsDto.Response response = new Response(
        RandomValue.getRandomLong(0,9999),
        "https://photoUrl//1",
        RandomValue.string(20).setNullable(false).get(),
        RandomValue.string(20).setNullable(false).get(),
        postList
    );
    return ApiResponse.ok(response);
  }

  @GetMapping("/getPostList")
  public ApiResponse<List<GetPostListDto.Response>> getPostList() {

    List<GetPostListDto.Response> response = new ArrayList<>();

    response.add(new GetPostListDto.Response(1L, "오늘의 컨텐츠는 무엇일까요"));
    response.add(new GetPostListDto.Response(2L, "요즘 이게 그렇게 유명하다면서요?"));
    response.add(new GetPostListDto.Response(3L, "진짜 이게 말이 되는 걸까요?"));
    response.add(new GetPostListDto.Response(4L, "제목을 무엇으로 해야 좋을까요"));
    response.add(new GetPostListDto.Response(5L, "오늘의 방송은 5시 부터 합니다!"));

    return ApiResponse.ok(response);
  }

  @GetMapping("/getPostDetails/{postId}")
  public ApiResponse<GetPostDetailsDto.Response> getPostDetails(@PathVariable Long postId) {

    GetPostDetailsDto.Response response = new GetPostDetailsDto.Response(
        RandomValue.getRandomLong(0,9999),
        "https://photoUrl//" + postId,
        RandomValue.string(20).setNullable(false).get(),
        RandomValue.string(20).setNullable(false).get(),
        RandomValue.string(500).setNullable(false).get(),
        RandomValue.string(50).setNullable(false).get(),
        RandomValue.getRandomBoolean()
    );

    return ApiResponse.ok(response);
  }

  @PostMapping("/createPost")
  @PreAuthorize("hasAuthority('MEMBER')")
  public ApiResponse<CreatePostDto.Response> createPost(
      @RequestBody @Valid CreatePostDto.Request request
  ) {
    Post post = request.postId() == null ?
        snsService.createPost(request): snsService.updatePost(request);

    CreatePostDto.Response response = PostMapper.INSTANCE.dtoToCreatePostResponse(post);
    return ApiResponse.ok(response);
  }

  @PostMapping(value = "/uploadPostMedia", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  @PreAuthorize("hasAuthority('MEMBER')")
  public ApiResponse<UploadPostMediaDto.Response> uploadPostMedia(
      @RequestPart @Valid UploadPostMediaDto.Request request,
      @RequestPart(value = "file", required = false) MultipartFile file
      ) {
    Post post = snsService.getOrCreatePost(request.postId());
    PostMedia postMedia = snsService.uploadPostMedia(post, file);
    UploadPostMediaDto.Response response = PostMediaMapper
        .INSTANCE
        .dtoToCreatePostResponse(postMedia);
    return ApiResponse.ok(response);
  }

  @PostMapping("/createComment")
  @PreAuthorize("hasAuthority('MEMBER')")
  public ApiResponse<CreateCommentDto.Response> createComment(
      @RequestBody @Valid CreateCommentDto.Request request
  ) {
    Comment comment = snsService.createComment(request);
    CreateCommentDto.Response response = CommentMapper.INSTANCE.dtoToCreateCommentResponse(comment);
    return ApiResponse.ok(response);
  }
}
