package com.bangbangbwa.backend.domain.member.controller;

import com.bangbangbwa.backend.domain.member.common.dto.CommentDto;
import com.bangbangbwa.backend.domain.member.common.dto.FollowDto;
import com.bangbangbwa.backend.domain.member.common.dto.MemberLoginDto;
import com.bangbangbwa.backend.domain.member.common.dto.MemberNicknameDto;
import com.bangbangbwa.backend.domain.member.common.dto.MemberSignupDto;
import com.bangbangbwa.backend.domain.member.common.dto.PostDto;
import com.bangbangbwa.backend.domain.member.common.dto.ProfileDto;
import com.bangbangbwa.backend.domain.member.common.dto.PromoteStreamerDto;
import com.bangbangbwa.backend.domain.member.common.dto.SummaryDto;
import com.bangbangbwa.backend.domain.member.common.mapper.FollowMapper;
import com.bangbangbwa.backend.domain.member.common.mapper.MemberMapper;
import com.bangbangbwa.backend.domain.member.common.mapper.ProfileMapper;
import com.bangbangbwa.backend.domain.member.common.mapper.SummaryMapper;
import com.bangbangbwa.backend.domain.member.service.MemberService;
import com.bangbangbwa.backend.domain.oauth.common.dto.OAuthInfoDto;
import com.bangbangbwa.backend.domain.oauth.common.enums.SnsType;
import com.bangbangbwa.backend.domain.oauth.service.OAuthService;
import com.bangbangbwa.backend.domain.sns.common.mapper.CommentMapper;
import com.bangbangbwa.backend.domain.sns.common.mapper.PostMapper;
import com.bangbangbwa.backend.domain.streamer.common.entity.PendingStreamer;
import com.bangbangbwa.backend.domain.streamer.common.mapper.PendingStreamerMapper;
import com.bangbangbwa.backend.domain.streamer.service.PendingStreamerService;
import com.bangbangbwa.backend.domain.token.common.TokenDto;
import com.bangbangbwa.backend.domain.token.service.TokenService;
import com.bangbangbwa.backend.global.response.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController implements MemberApi {

  private final OAuthService oAuthService;
  private final MemberService memberService;
  private final TokenService tokenService;
  private final PendingStreamerService pendingStreamerService;

  @PostMapping(value = "/{snsType}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  public ApiResponse<MemberSignupDto.Response> signup(
      @PathVariable("snsType") SnsType snsType,
      @RequestPart(value = "file", required = false) MultipartFile file,
      @RequestPart @Valid MemberSignupDto.Request request
  ) {
    String oauthToken = request.oauthToken();
    OAuthInfoDto oAuthInfo = oAuthService.getInfoByToken(snsType, oauthToken);
    TokenDto token = memberService.signup(oAuthInfo, request, file);
    MemberSignupDto.Response response = MemberMapper.INSTANCE.dtoToSignupResponse(token);
    return ApiResponse.ok(response);
  }

  @PostMapping("/login/{snsType}")
  public ApiResponse<MemberLoginDto.Response> login(
      @PathVariable SnsType snsType,
      @RequestBody @Valid MemberLoginDto.Request request
  ) {
    String authCode = request.authCode();
    OAuthInfoDto oAuthInfo = oAuthService.getInfoByCode(snsType, authCode);
    TokenDto token = memberService.login(oAuthInfo);
    MemberLoginDto.Response response = MemberMapper.INSTANCE.dtoToLoginResponse(token);
    return ApiResponse.ok(response);
  }

  @GetMapping("/check/{nickname}")
  public ApiResponse<Null> dupCheck(@PathVariable("nickname") String nickname) {
    memberService.checkNickname(nickname);
    return ApiResponse.ok();
  }

  @GetMapping("/nicknames")
  public ApiResponse<MemberNicknameDto.Response> randomNicknames(
      @RequestParam("count") int count
  ) {
    Set<String> nicknames = memberService.serveRandomNicknames(count);
    MemberNicknameDto.Response response = new MemberNicknameDto.Response(nicknames);
    return ApiResponse.ok(response);
  }

  @PostMapping("/reissueToken")
  public ApiResponse<TokenDto> reissueToken(@RequestParam String refreshToken) {
    TokenDto tokenDto = tokenService.reissueToken(refreshToken);
    return ApiResponse.ok(tokenDto);
  }

  @PostMapping("/promoteStreamer")
  @PreAuthorize("hasAuthority('MEMBER')")
  public ApiResponse<PromoteStreamerDto.Response> promoteStreamer(
      @RequestBody @Valid PromoteStreamerDto.Request request
  ) {
    PendingStreamer pendingStreamer = pendingStreamerService.promoteStreamer(request);
    PromoteStreamerDto.Response response = PendingStreamerMapper
        .INSTANCE
        .dtoToPromoteStreamerResponse(pendingStreamer);

    return ApiResponse.ok(response);
  }

  @GetMapping("/isMyMemberId/{memberId}")
  public ApiResponse<Boolean> isMyMemberId(@PathVariable("memberId") Long memberId) {
    Boolean isMyMemberId = memberService.isMyMemberId(memberId);
    return ApiResponse.ok(isMyMemberId);
  }

  @GetMapping("/profile/{memberId}")
  @PreAuthorize("permitAll()")
  public ApiResponse<ProfileDto.Response> getProfile(@PathVariable("memberId") Long memberId) {
    ProfileDto profile = memberService.getProfile(memberId);
    ProfileDto.Response response = ProfileMapper.INSTANCE.dtoToResponse(profile);
    return ApiResponse.ok(response);
  }

  @GetMapping("/summary/{memberId}")
  @PreAuthorize("permitAll()")
  public ApiResponse<SummaryDto.Response> getSummary(@PathVariable("memberId") Long memberId) {
    SummaryDto summaryDto = memberService.getSummary(memberId);
    SummaryDto.Response response = SummaryMapper.INSTANCE.dtoToResponse(summaryDto);
    return ApiResponse.ok(response);
  }

  @GetMapping("/posts/{memberId}")
  @PreAuthorize("permitAll()")
  public ApiResponse<PostDto.Response> getPosts(@PathVariable("memberId") Long memberId) {
    List<PostDto> postDto = memberService.getPosts(memberId);
    PostDto.Response response = PostMapper.INSTANCE.dtoToResponse(postDto);
    return ApiResponse.ok(response);
  }

  @GetMapping("/comments/{memberId}")
  @PreAuthorize("permitAll()")
  public ApiResponse<CommentDto.Response> getComments(@PathVariable("memberId") Long memberId) {
    List<CommentDto> commentDto = memberService.getComments(memberId);
    CommentDto.Response response = CommentMapper.INSTANCE.dtoToResponse(commentDto);
    return ApiResponse.ok(response);
  }

  @GetMapping("/followers/{memberId}")
  @PreAuthorize("permitAll()")
  public ApiResponse<FollowDto.Response> getFollowers(@PathVariable("memberId") Long memberId) {
    List<FollowDto> followDto = memberService.getFollowers(memberId);
    FollowDto.Response response = FollowMapper.INSTANCE.dtoToResponse(followDto);
    return ApiResponse.ok(response);
  }
}
