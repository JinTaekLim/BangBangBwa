package com.bangbangbwa.backend.domain.comment.service;

import com.bangbangbwa.backend.domain.comment.business.CommentReader;
import com.bangbangbwa.backend.domain.comment.business.CommentUpdater;
import com.bangbangbwa.backend.domain.comment.common.dto.AddCommentReplyDto;
import com.bangbangbwa.backend.domain.member.business.MemberProvider;
import com.bangbangbwa.backend.domain.sns.common.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentReader commentReader;
  private final CommentUpdater commentUpdater;
  private final MemberProvider memberProvider;

  public void addCommentReply(AddCommentReplyDto.Request request) {
    Long commentId = request.commentId();
    String replyMessage = request.message();

    Comment comment = commentReader.findById(commentId);
    comment.updateReplyComment(replyMessage);
    commentUpdater.updateForAddReply(comment);
  }
}
