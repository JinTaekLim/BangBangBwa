package com.bangbangbwa.backend.domain.sns.common.mapper;

import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.sns.common.dto.CreatePostDto;
import com.bangbangbwa.backend.domain.sns.common.dto.GetPostDetailsDto;
import com.bangbangbwa.backend.domain.sns.common.dto.GetPostListDto;
import com.bangbangbwa.backend.domain.sns.common.dto.UploadPostMediaDto;
import com.bangbangbwa.backend.domain.sns.common.entity.Comment;
import com.bangbangbwa.backend.domain.sns.common.entity.Post;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {

  PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

  @Mapping(target = "postType", source = "request.postType")
  @Mapping(target = "title", source = "request.title")
  @Mapping(target = "content", source = "request.content")
  @Mapping(target = "publicMembers", source = "request.publicMembers")
  @Mapping(target = "privateMembers", source = "request.privateMembers")
  Post dtoToEntity(CreatePostDto.Request request);


  @Mapping(target = "postType", source = "post.postType")
  @Mapping(target = "title", source = "post.title")
  @Mapping(target = "content", source = "post.content")
  CreatePostDto.Response dtoToCreatePostResponse(Post post);

  @Mapping(target = "url", source = "url")
  UploadPostMediaDto.Response dtoToUploadPostMediaResponse(String url);

  @Mapping(source = "post.id", target = "postId")
  @Mapping(source = "post.memberId", target = "writerId")
  @Mapping(source = "member.profile", target = "profileUrl")
  @Mapping(source = "member.nickname", target = "nickname")
  @Mapping(source = "post.title", target = "title")
  @Mapping(source = "post.content", target = "content")
  @Mapping(source = "comment.content", target = "comment")
//  @Mapping(source = "", target = "isFollowed")
  GetPostDetailsDto.Response dtoToGetPostDetailsResponse(Post post, Member member, Comment comment);


  @Mapping(target = "postId", source = "id")
  @Mapping(target = "title", source = "title")
  GetPostListDto.Response dtoToGetPostListResponse(Post post);
  List<GetPostListDto.Response> dtoToGetPostListResponse(List<Post> postList);
}
