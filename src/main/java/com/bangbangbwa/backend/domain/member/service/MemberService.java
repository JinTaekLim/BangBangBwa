package com.bangbangbwa.backend.domain.member.service;

import com.bangbangbwa.backend.domain.member.business.FollowCreator;
import com.bangbangbwa.backend.domain.member.business.FollowDeleter;
import com.bangbangbwa.backend.domain.member.business.FollowGenerator;
import com.bangbangbwa.backend.domain.member.business.FollowReader;
import com.bangbangbwa.backend.domain.member.business.MemberCreator;
import com.bangbangbwa.backend.domain.member.business.MemberGenerator;
import com.bangbangbwa.backend.domain.member.business.MemberProvider;
import com.bangbangbwa.backend.domain.member.business.MemberReader;
import com.bangbangbwa.backend.domain.member.business.MemberTagRelation;
import com.bangbangbwa.backend.domain.member.business.MemberValidator;
import com.bangbangbwa.backend.domain.member.business.NicknameProvider;
import com.bangbangbwa.backend.domain.member.common.dto.CommentDto;
import com.bangbangbwa.backend.domain.member.common.dto.FollowDto.FollowResponse;
import com.bangbangbwa.backend.domain.member.common.dto.FollowerDto.FollowerResponse;
import com.bangbangbwa.backend.domain.member.common.dto.MemberSignupDto;
import com.bangbangbwa.backend.domain.member.common.dto.PostDto;
import com.bangbangbwa.backend.domain.member.common.dto.ProfileDto;
import com.bangbangbwa.backend.domain.member.common.dto.SummaryDto;
import com.bangbangbwa.backend.domain.member.common.dto.ToggleFollowDto;
import com.bangbangbwa.backend.domain.member.common.dto.TogglePostPinDto;
import com.bangbangbwa.backend.domain.member.common.entity.Follow;
import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.member.common.enums.Role;
import com.bangbangbwa.backend.domain.oauth.common.dto.OAuthInfoDto;
import com.bangbangbwa.backend.domain.post.business.PostReader;
import com.bangbangbwa.backend.domain.post.business.PostValidator;
import com.bangbangbwa.backend.domain.promotion.business.StreamerReader;
import com.bangbangbwa.backend.domain.sns.business.PostUpdater;
import com.bangbangbwa.backend.domain.tag.business.TagReader;
import com.bangbangbwa.backend.domain.tag.common.dto.TagDto;
import com.bangbangbwa.backend.domain.token.business.TokenProvider;
import com.bangbangbwa.backend.domain.token.common.dto.TokenDto;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberTagRelation memberTagRelation;
  private final MemberGenerator memberGenerator;
  private final MemberValidator memberValidator;
  private final MemberCreator memberCreator;
  private final MemberReader memberReader;
  private final TokenProvider tokenProvider;
  private final NicknameProvider nicknameProvider;
  private final MemberProvider memberProvider;
  private final TagManager tagManager;
  private final StreamerReader streamerReader;
  private final FollowReader followReader;
  private final PostReader postReader;
  private final FollowGenerator followGenerator;
  private final FollowCreator followCreator;
  private final FollowDeleter followDeleter;
  private final PostValidator postValidator;
  private final PostUpdater postUpdater;

  @Transactional
  public TokenDto signup(
      OAuthInfoDto oAuthInfo,
      List<TagDto> tagList,
      MemberSignupDto.Request request,
      MultipartFile profileFile) {
    memberValidator.validateUniqueMember(oAuthInfo);
    Member member = memberGenerator.generate(oAuthInfo, request, profileFile);
    memberCreator.save(member);
    // TODO : Bulk 연산 학습 후 적용 예정
    for (TagDto tag : tagList) {
      memberTagRelation.relation(member, tag);
    }
    return tokenProvider.getToken(member);
  }

  public TokenDto login(OAuthInfoDto oAuthInfo) {
    Member member = memberReader.findBySns(oAuthInfo);
    return tokenProvider.getToken(member);
  }

  public void checkNickname(String nickname) {
    memberValidator.validateNicknameDuplication(nickname);
  }

  public String serveRandomNickname() {
    Set<String> nicknames = nicknameProvider.provideRandomNicknames(1);
    if (nicknames.iterator().hasNext()) {
      return nicknames.iterator().next();
    }
    return null;
  }

  public Boolean isMyMemberId(Long memberId) {
    return memberValidator.isMyMemberId(memberId);
  }

  public ProfileDto getProfile(Long memberId) {
    Long currentMemberId = memberProvider.getCurrentMemberIdOrNull();
    ProfileDto profileDto = new ProfileDto(memberId, currentMemberId);
    return memberReader.getProfile(profileDto);
  }

  public SummaryDto getSummary(Long memberId) {
    Long currentMemberId = memberProvider.getCurrentMemberIdOrNull();
    SummaryDto request = new SummaryDto(memberId, currentMemberId);

    SummaryDto summaryDto = memberReader.getSummary(request);

    if (Objects.equals(memberProvider.getCurrentRoleOrNull(), Role.STREAMER)) {
      summaryDto.setPlatforms(streamerReader.findStreamerPlatforms(memberId));
    }

    if (!Objects.equals(memberId, currentMemberId)) {
      memberValidator.removeData(summaryDto);
    }
    return summaryDto;
  }

  public List<PostDto> getPosts(Long memberId) {
    return postReader.findPostsByMemberId(memberId);
  }

  public CommentDto getComments(Long memberId) {
    memberReader.findById(memberId);
    return null;
  }

  public List<FollowerResponse> getFollowers(Long memberId) {
    return followReader.findFollowersByMemberId(memberId);
  }

  public List<FollowResponse> getFollows(Long memberId) {
    return followReader.findFollowsByMemberId(memberId);
  }

  public void toggleFollow(ToggleFollowDto.Request req) {
    Long memberId = memberProvider.getCurrentMemberId();
    Follow follow = followGenerator.generate(req, memberId);
    if (req.isFollow()) {
      followCreator.save(follow);
    } else {
      followDeleter.deleteByFollowerIdAndFollowId(memberId, req.memberId());
    }
  }

  public void togglePostPin(TogglePostPinDto.Request req) {
    Long memberId = memberProvider.getCurrentMemberId();
    postValidator.validatePostWriter(req.postId(), memberId);
    if (req.pinned()) {
      postValidator.validatePostPinLimit(memberId);
    }
    postUpdater.updatePostPin(req.postId(), req.pinned());
  }
}
