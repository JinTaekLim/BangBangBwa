package com.bangbangbwa.backend.domain.sns.service;

import com.bangbangbwa.backend.domain.member.business.MemberProvider;
import com.bangbangbwa.backend.domain.member.business.MemberValidator;
import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.sns.business.CommentCreator;
import com.bangbangbwa.backend.domain.sns.business.CommentGenerator;
import com.bangbangbwa.backend.domain.sns.business.PostCreator;
import com.bangbangbwa.backend.domain.sns.business.PostGenerator;
import com.bangbangbwa.backend.domain.sns.business.PostMediaCreator;
import com.bangbangbwa.backend.domain.sns.business.PostMediaGenerator;
import com.bangbangbwa.backend.domain.sns.business.PostReader;
import com.bangbangbwa.backend.domain.sns.common.dto.CreateCommentDto;
import com.bangbangbwa.backend.domain.sns.common.dto.CreatePostDto;
import com.bangbangbwa.backend.domain.sns.common.dto.UploadPostMediaDto;
import com.bangbangbwa.backend.domain.sns.common.entity.Comment;
import com.bangbangbwa.backend.domain.sns.common.entity.Post;
import com.bangbangbwa.backend.domain.sns.common.entity.PostMedia;
import com.bangbangbwa.backend.domain.sns.common.enums.PostType;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class SnsService {

  private final MemberProvider memberProvider;
  private final MemberValidator memberValidator;
  private final PostGenerator postGenerator;
  private final PostCreator postCreator;
  private final PostReader postReader;
  private final PostMediaGenerator postMediaGenerator;
  private final PostMediaCreator postMediaCreator;
  private final CommentGenerator commentGenerator;
  private final CommentCreator commentCreator;

  public Post createPost(CreatePostDto.Request request) {
    Member member = memberProvider.getCurrentMember();
    PostType postType = request.postType();
    memberValidator.validateRole(member.getRole(), postType);

    Post post = postGenerator.generate(request, member);
    postCreator.save(post);

    return post;
  }

  public Post updatePost(CreatePostDto.Request request) {
    Post post = postReader.findById(request.postId());
    Member member = memberProvider.getCurrentMember();
    PostType postType = request.postType();
    memberValidator.validateRole(member.getRole(), postType);

    Post newPost = postGenerator.update(request, post.getId());
    postCreator.update(newPost);

    return newPost;
  }


  // note : 해당 로직은 snsService 가 아닌, business 단에 위치시키는 것이 좋을지?
  public Post getOrCreatePost(Long postId) {
    Member member = memberProvider.getCurrentMember();
    return Optional.ofNullable(postId)
        .map(postReader::findById)
        .orElseGet(() -> {
          Post newPost = postGenerator.generate(member);
          postCreator.save(newPost);
          return newPost;
        });
  }


  public PostMedia uploadPostMedia(Post post, MultipartFile file) {

    Member member = memberProvider.getCurrentMember();

    PostMedia postMedia = postMediaGenerator.generate(post,file,member);
    postMediaCreator.save(postMedia);

    return postMedia;
  }


  public Comment createComment(CreateCommentDto.Request request) {
    Member member = memberProvider.getCurrentMember();
    postReader.findById(request.postId());
    Comment comment = commentGenerator.generate(request, member);
    commentCreator.save(comment);
    return comment;
  }
}
