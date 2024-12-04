package com.bangbangbwa.backend.domain.member.service;

import com.bangbangbwa.backend.domain.member.business.FollowReader;
import com.bangbangbwa.backend.domain.member.business.MemberCreator;
import com.bangbangbwa.backend.domain.member.business.MemberGenerator;
import com.bangbangbwa.backend.domain.member.business.MemberProcessor;
import com.bangbangbwa.backend.domain.member.business.MemberProvider;
import com.bangbangbwa.backend.domain.member.business.MemberReader;
import com.bangbangbwa.backend.domain.member.business.MemberTagRelation;
import com.bangbangbwa.backend.domain.member.business.MemberValidator;
import com.bangbangbwa.backend.domain.member.business.NicknameProvider;
import com.bangbangbwa.backend.domain.member.common.dto.CommentDto;
import com.bangbangbwa.backend.domain.member.common.dto.FollowDto;
import com.bangbangbwa.backend.domain.member.common.dto.MemberSignupDto;
import com.bangbangbwa.backend.domain.member.common.dto.PostDto;
import com.bangbangbwa.backend.domain.member.common.dto.ProfileDto;
import com.bangbangbwa.backend.domain.member.common.dto.SummaryDto;
import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.oauth.common.dto.OAuthInfoDto;
import com.bangbangbwa.backend.domain.sns.business.CommentReader;
import com.bangbangbwa.backend.domain.sns.business.PostReader;
import com.bangbangbwa.backend.domain.streamer.business.PlatformProcessor;
import com.bangbangbwa.backend.domain.tag.business.TagManager;
import com.bangbangbwa.backend.domain.tag.common.entity.Tag;
import com.bangbangbwa.backend.domain.token.business.TokenProvider;
import com.bangbangbwa.backend.domain.token.common.TokenDto;
import java.util.List;
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
  private final PlatformProcessor platformProcessor;
  private final MemberProcessor memberProcessor;
  private final PostReader postReader;
  private final CommentReader commentReader;
  private final FollowReader followReader;

  @Transactional
  public TokenDto signup(OAuthInfoDto oAuthInfo, MemberSignupDto.Request request,
      MultipartFile profileFile) {
    Member member = memberGenerator.generate(oAuthInfo, request, profileFile);
    memberCreator.save(member);
    List<Tag> tags = tagManager.getTags(request.tags(), String.valueOf(member.getId()));
    // TODO : Bulk 연산 학습 후 적용 예정
    for (Tag tag : tags) {
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

  public Set<String> serveRandomNicknames(Integer count) {
    return nicknameProvider.provideRandomNicknames(count);
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

    platformProcessor.setPlatforms(summaryDto, memberId);

    memberProcessor.removeDataIfAnotherMember(summaryDto, memberId, currentMemberId);

    return summaryDto;
  }

  public List<PostDto> getPosts(Long memberId) {
    return postReader.findAllPost(memberId);
  }

  public List<CommentDto> getComments(Long memberId) {
    memberValidator.checkIsMyMemberId(memberId);

    return commentReader.findAllComments(memberId);
  }

  public List<FollowDto> getFollowers(Long memberId) {
    memberValidator.checkIsMyMemberId(memberId);

    return followReader.findAllFollowers(memberId);
  }
}
