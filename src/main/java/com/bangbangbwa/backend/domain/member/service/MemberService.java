package com.bangbangbwa.backend.domain.member.service;

import com.bangbangbwa.backend.domain.member.business.MemberCreator;
import com.bangbangbwa.backend.domain.member.business.MemberGenerator;
import com.bangbangbwa.backend.domain.member.business.MemberProvider;
import com.bangbangbwa.backend.domain.member.business.MemberReader;
import com.bangbangbwa.backend.domain.member.business.MemberTagRelation;
import com.bangbangbwa.backend.domain.member.business.MemberValidator;
import com.bangbangbwa.backend.domain.member.business.NicknameProvider;
import com.bangbangbwa.backend.domain.member.common.dto.CommentDto;
import com.bangbangbwa.backend.domain.member.common.dto.FollowerDto;
import com.bangbangbwa.backend.domain.member.common.dto.MemberSignupDto;
import com.bangbangbwa.backend.domain.member.common.dto.PostDto;
import com.bangbangbwa.backend.domain.member.common.dto.ProfileDto;
import com.bangbangbwa.backend.domain.member.common.dto.SummaryDto;
import com.bangbangbwa.backend.domain.member.common.entity.Member;
import com.bangbangbwa.backend.domain.member.common.enums.Role;
import com.bangbangbwa.backend.domain.oauth.common.dto.OAuthInfoDto;
import com.bangbangbwa.backend.domain.promotion.business.StreamerReader;
import com.bangbangbwa.backend.domain.tag.business.TagManager;
import com.bangbangbwa.backend.domain.tag.common.entity.Tag;
import com.bangbangbwa.backend.domain.token.business.TokenProvider;
import com.bangbangbwa.backend.domain.token.common.TokenDto;
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

    if (Objects.equals(memberProvider.getCurrentRoleOrNull(), Role.STREAMER)) {
      summaryDto.setPlatforms(streamerReader.findStreamerPlatforms(memberId));
    }

    if (!Objects.equals(memberId, currentMemberId)) {
      memberValidator.removeData(summaryDto);
    }
    return summaryDto;
  }

  public List<PostDto> getPosts(Long memberId) {
    memberReader.findById(memberId);
    return null;
  }

  public CommentDto getComments(Long memberId) {
    memberReader.findById(memberId);
    return null;
  }

  public List<FollowerDto> getFollowers(Long memberId) {
    memberReader.findById(memberId);
    return null;
  }
}
