package com.bangbangbwa.backend.domain.post.controller;

import com.bangbangbwa.backend.domain.post.common.dto.CreatePostDto;
import com.bangbangbwa.backend.domain.post.common.dto.CreatePostDto.Response;
import com.bangbangbwa.backend.domain.post.common.dto.GetLatestPostsDto;
import com.bangbangbwa.backend.domain.post.common.dto.GetPostDetailsDto;
import com.bangbangbwa.backend.domain.post.common.dto.GetPostListDto;
import com.bangbangbwa.backend.domain.post.common.dto.UploadPostMediaDto;
import com.bangbangbwa.backend.domain.post.common.entity.Post;
import com.bangbangbwa.backend.domain.post.common.enums.PostType;
import com.bangbangbwa.backend.domain.post.common.mapper.PostMapper;
import com.bangbangbwa.backend.domain.post.service.PostService;
import com.bangbangbwa.backend.global.response.ApiResponse;
import jakarta.validation.Valid;
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
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;

  @PostMapping("/createPost")
  @PreAuthorize("hasAuthority('MEMBER')")
  public ApiResponse<Response> createPost(
      @RequestBody @Valid CreatePostDto.Request request
  ) {
    Post post = postService.createPost(request);
    CreatePostDto.Response response = PostMapper.INSTANCE.dtoToCreatePostResponse(post);
    return ApiResponse.ok(response);
  }

  @PostMapping(value = "/uploadPostMedia", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  @PreAuthorize("hasAuthority('MEMBER')")
  public ApiResponse<UploadPostMediaDto.Response> uploadPostMedia(
      @RequestPart(value = "file", required = false) MultipartFile file
  ) {
    String mediaUrl = postService.uploadPostMedia(file);
    UploadPostMediaDto.Response response
        = PostMapper.INSTANCE.dtoToUploadPostMediaResponse(mediaUrl);
    return ApiResponse.ok(response);
  }

  @GetMapping("/getPostList")
  @PreAuthorize("permitAll()")
  public ApiResponse<List<GetPostListDto.Response>> getPostList() {
    List<Post> postList = postService.getPostList();
    List<GetPostListDto.Response> response = PostMapper.INSTANCE.dtoToGetPostListResponse(postList);
    return ApiResponse.ok(response);
  }

  @GetMapping("/getPostDetails/{postId}")
  @PreAuthorize("permitAll()")
  public ApiResponse<GetPostDetailsDto.Response> getPostDetails(
      @PathVariable("postId") Long postId) {
    GetPostDetailsDto.Response response = postService.getPostDetails(postId);
    return ApiResponse.ok(response);
  }

  @GetMapping("/getLatestPosts")
  @PreAuthorize("permitAll()")
  public ApiResponse<List<GetLatestPostsDto.Response>> getLatestPosts() {
    List<GetLatestPostsDto.Response> response = postService.getLatestPosts(PostType.STREAMER);
    return ApiResponse.ok(response);
  }
}
