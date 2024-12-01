package com.bangbangbwa.backend.domain.sns.service;

import com.bangbangbwa.backend.domain.member.business.MemberProvider;
import com.bangbangbwa.backend.domain.member.business.MemberReader;
import com.bangbangbwa.backend.domain.member.business.MemberValidator;
import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.member.common.enums.Role;
import com.bangbangbwa.backend.domain.sns.business.*;
import com.bangbangbwa.backend.domain.sns.common.dto.*;
import com.bangbangbwa.backend.domain.sns.common.entity.Comment;
import com.bangbangbwa.backend.domain.sns.common.entity.Post;
import com.bangbangbwa.backend.domain.sns.common.entity.PostVisibilityMember;
import com.bangbangbwa.backend.domain.sns.common.entity.ReportPost;
import com.bangbangbwa.backend.domain.sns.common.enums.PostType;
import com.bangbangbwa.backend.domain.sns.common.enums.VisibilityType;
import com.bangbangbwa.backend.global.util.S3Manager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class SnsService {

  private final MemberProvider memberProvider;
  private final MemberValidator memberValidator;
  private final MemberReader memberReader;
  private final PostGenerator postGenerator;
  private final PostCreator postCreator;
  private final PostReader postReader;
  private final PostProvider postProvider;
  private final PostValidator postValidator;
  private final PostVisibilityMemberCreator postVisibilityMemberCreator;
  private final PostVisibilityMemberGenerator postVisibilityMemberGenerator;
  private final S3Manager s3Manager;
  private final CommentGenerator commentGenerator;
  private final CommentCreator commentCreator;
  private final PostTypeProvider postTypeProvider;
  private final ReportValidator reportValidator;
  private final ReportPostCreator reportPostCreator;
  private final ReportPostGenerator reportPostGenerator;


  // 게시글 저장 전, content에서 url을 추출 후 redis 값 삭제하는 과정 필요
  @Transactional
  public Post createPost(CreatePostDto.Request request) {
    Member member = memberProvider.getCurrentMember();
    PostType postType = request.postType();
    memberValidator.validateRole(member.getRole(), postType);

    Post post = postGenerator.generate(request, member);
    postCreator.save(post);

    VisibilityType type = postValidator.validateMembers(
        request.publicMembers(),
        request.privateMembers()
    );

    if (type != null) {
      List<Long> memberList = (type == VisibilityType.PRIVATE) ?
          request.privateMembers() : request.publicMembers();

      List<PostVisibilityMember> postVisibilityMember = postVisibilityMemberGenerator.generate(
          post, type, memberList
      );

      postVisibilityMemberCreator.saveList(postVisibilityMember);
    }

    return post;
  }

  public String uploadPostMedia(MultipartFile file) {
    return s3Manager.upload(file);
  }


  public Comment createComment(CreateCommentDto.Request request) {
    Member member = memberProvider.getCurrentMember();
    postReader.findById(request.postId());
    Comment comment = commentGenerator.generate(request, member);
    commentCreator.save(comment);
    return comment;
  }

  public List<Member> searchMember(String nickname) {
    return memberReader.findByNicknameContaining(nickname);
  }

  public GetPostDetailsDto.Response getPostDetails(Long postId) {
    Long memberId = memberProvider.getCurrentMemberIdOrNull();
    return postReader.getPostDetails(postId, memberId);
  }

  public List<Post> getPostList() {
    Role role = memberProvider.getCurrentRole();
    PostType postType = postTypeProvider.getInversePostTypeForRole(role);
    return postProvider.getRandomPost(postType);
  }

  public List<GetLatestPostsDto> getLatestPosts(PostType postType) {
    return postReader.findPostsWithinLast24Hours(postType);
  }

  public void reportPost(ReportPostDto.Request request) {
    Long memberId = memberProvider.getCurrentMemberId();
    reportValidator.checkForDuplicateReportPost(request.postId(), memberId);
    ReportPost reportPost = reportPostGenerator.generate(request, memberId);
    reportPostCreator.save(reportPost);
  }
}
