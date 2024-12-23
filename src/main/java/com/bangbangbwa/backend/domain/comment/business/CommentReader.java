package com.bangbangbwa.backend.domain.comment.business;

import com.bangbangbwa.backend.domain.comment.common.dto.MyCommentDto;
import com.bangbangbwa.backend.domain.comment.exception.NotFoundCommentException;
import com.bangbangbwa.backend.domain.member.common.dto.CommentDto.CommentResponse;
import com.bangbangbwa.backend.domain.member.common.dto.CommentDto.CommentResponseCommentInfo;
import com.bangbangbwa.backend.domain.member.common.dto.CommentDto.CommentResponsePostInfo;
import com.bangbangbwa.backend.domain.post.common.dto.MyPostDto.MyPostResponseCommentInfo;
import com.bangbangbwa.backend.domain.sns.common.entity.Comment;
import com.bangbangbwa.backend.domain.sns.repository.CommentRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentReader {

  private final CommentRepository commentRepository;

  public Comment findById(Long commentId) {
    return commentRepository.findById(commentId).orElseThrow(NotFoundCommentException::new);
  }

  public List<MyPostResponseCommentInfo> findCommentListByPostId(Long postId) {
    return commentRepository.findCommentListByPostId(postId);
  }

  public List<CommentResponse> getMyComments(Long memberId) {
    List<MyCommentDto> dtoList = commentRepository.findMyCommentList(memberId);
    List<CommentResponse> responseList = new ArrayList<>();
    for (MyCommentDto dto : dtoList) {
      CommentResponsePostInfo postInfo = new CommentResponsePostInfo(
          dto.getPostInfo().getPostId(),
          dto.getPostInfo().getTitle(),
          dto.getPostInfo().getMemberId(),
          dto.getPostInfo().getMemberNickname(),
          dto.getPostInfo().getMemberImageUrl(),
          dto.getPostInfo().isHasImage(),
          dto.getPostInfo().isHasVideo()
      );

      CommentResponseCommentInfo commentInfo = new CommentResponseCommentInfo(
          dto.getCommentInfo().getCommentId(),
          dto.getCommentInfo().getContent(),
          dto.getCommentInfo().getReplyContent()
      );

      responseList.add(new CommentResponse(postInfo, commentInfo));
    }
    return responseList;
  }
}
