package com.bangbangbwa.backend.domain.member.controller;

import com.bangbangbwa.backend.domain.member.common.dto.CommentDto;
import com.bangbangbwa.backend.domain.member.common.dto.FollowerDto;
import com.bangbangbwa.backend.domain.member.common.dto.MemberLoginDto;
import com.bangbangbwa.backend.domain.member.common.dto.MemberNicknameDto;
import com.bangbangbwa.backend.domain.member.common.dto.MemberSignupDto;
import com.bangbangbwa.backend.domain.member.common.dto.PostDto;
import com.bangbangbwa.backend.domain.member.common.dto.ProfileDto;
import com.bangbangbwa.backend.domain.member.common.dto.SummaryDto;
import com.bangbangbwa.backend.domain.oauth.common.enums.SnsType;
import com.bangbangbwa.backend.domain.token.common.dto.ReissueTokenDto;
import com.bangbangbwa.backend.global.annotation.swagger.ApiResponse200;
import com.bangbangbwa.backend.global.annotation.swagger.ApiResponse500;
import com.bangbangbwa.backend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "MemberAPI", description = "회원 API")
@ApiResponse500
public interface MemberApi {

  @Operation(summary = "회원가입", tags = {"MemberAPI"}, description = "회원가입을 수행합니다.")
  @ApiResponses(value = {
      @io.swagger.v3.oas.annotations.responses.ApiResponse(
          responseCode = "200", description = "OK",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = MemberSignupDto.Response.class)
          )
      ),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(
          responseCode = "400",
          description = "잘못된 요청입니다.",
          content = @Content(
              mediaType = "application/json",
              array = @ArraySchema(schema = @Schema(implementation = ApiResponse.class)),
              examples = {
                  @ExampleObject(
                      value = """
                          {
                          "code": "BAD_REQUEST",
                          "message": "잘못된 요청",
                          "data" : [
                            "oAuthToken : oAuth 토큰을 입력해주세요.",
                            "nickname : 닉네임을 입력해주세요.",
                            "nickname : 최대 14자 이하로 입력해주세요.",
                            "nickname : 한글,영문,숫자, 특수문자('(',')','-','_')만 사용 가능합니다.",
                            "usageAgree : 이용 약관 동의 여부 확인 바랍니다." ,
                            "personalAgree : 개인 정보 수집 및 저장 동의 여부 확인 바랍니다.",
                            "withdrawalAgree : 회원 탈퇴 시 처리 방안 동의 여부 확인 바랍니다."
                          ]
                          }
                          """)
              }
          )
      )
  })
  ApiResponse<MemberSignupDto.Response> signup(
      @Parameter SnsType snsType,
      @RequestBody MultipartFile file,
      @RequestBody MemberSignupDto.Request request
  );

  @Operation(summary = "로그인", tags = {"MemberAPI"}, description = "로그인을 수행합니다.")
  @ApiResponses(value = {
      @io.swagger.v3.oas.annotations.responses.ApiResponse(
          responseCode = "200", description = "OK",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = MemberLoginDto.Response.class)
          )
      ),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(
          responseCode = "401", description = "미가입 회원",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = ApiResponse.class),
              examples = @ExampleObject(
                  value = """
                      {
                      "code": "UNAUTHORIZED",
                      "message": "가입되지 않은 회원입니다.",
                      "data" : "ya29.a0AeDClZAJN53QRLtXX-jiAjQBVLdpBvp3NwkWNi9rITYZKVsj2dvy-7z0CqyvGZC_gR6a-tmjEZLNyXY3-u-srtSzmSzeRBAaPIFXpJdK47F0KH2nMrQhFEF6cXKiXcAT6_49H3QMRbBE1TPht5VYNolFLfU67QyFrmdNh0dwaCgYKAcISARMSFQHGX2MiQHTvShJ8pEyM9Ui51m2Gww0175"
                      }"""
              )
          )
      ),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(
          responseCode = "400",
          description = "잘못된 요청",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = ApiResponse.class),
              examples = @ExampleObject(
                  value = """
                      {
                      "code": "BAD_REQUEST",
                      "message": "잘못된 요청입니다.",
                      "data" : [
                        "authCode: 인가코드를 입력해주세요."
                      ]
                      }
                      """
              )
          )
      )
  })
  ApiResponse<MemberLoginDto.Response> login(
      @Parameter SnsType snsType,
      @RequestBody MemberLoginDto.Request request
  );

  @Operation(
      tags = {"MemberAPI"},
      summary = "닉네임 중복 확인",
      description = "닉네임 중복 여부를 확인합니다.",
      responses = {
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
              responseCode = "400",
              description = "닉네임 중복",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ApiResponse.class),
                  examples = @ExampleObject(
                      value = """
                          {
                          "code" : "DUPLICATED_NICKNAME_ERROR",
                          "message" : "이미 사용중인 닉네임 입니다.",
                          "data" : null
                          }
                          """
                  )
              )
          ),
      }
  )
  @ApiResponse200
  ApiResponse<Null> dupCheck(String nickname);

  @Operation(
      tags = {"MemberAPI"},
      summary = "닉네임 랜덤 제공",
      description = "랜덤 닉네임을 제공해줍니다.",
      responses = {
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
              responseCode = "200",
              description = "OK",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = MemberNicknameDto.Response.class)
              )
          )
      }
  )
  ApiResponse<MemberNicknameDto.Response> randomNicknames();


  @Operation(summary = "토큰 재발급", tags = {"MemberAPI"})
  @ApiResponses(value = {
      @io.swagger.v3.oas.annotations.responses.ApiResponse(
          responseCode = "200", description = "OK",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = ReissueTokenDto.Response.class)
          )
      ),
      @io.swagger.v3.oas.annotations.responses.ApiResponse(
          responseCode = "400",
          description = "유효하지 않은 토큰입니다.",
          content = @Content(
              mediaType = "application/json",
              array = @ArraySchema(schema = @Schema(implementation = ApiResponse.class)),
              examples = {
                  @ExampleObject(
                      value = """
                          {
                          "code": "BAD_REQUEST",
                          "message": "유효하지 않은 토큰입니다.",
                          "data" : [
                            "refreshToken 토큰을 입력해주세요."
                          ]
                          }
                          """
                  )
              }
          )
      )
  })
  ApiResponse<ReissueTokenDto.Response> reissueToken(
      @RequestParam String refreshToken
  );

  @Operation(
      tags = {"MemberAPI"},
      summary = "마이페이지 > memberId 일치여부 조회",
      description = "현재 사용자와 memberId가 일치하는지 조회합니다." +
          "\n비로그인 사용자일 경우에는 false를 리턴합니다.",
      responses = {
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
              responseCode = "200",
              description = "OK",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = Boolean.class)
              )
          ),
      }
  )
  ApiResponse<Boolean> isMyMemberId(Long memberId);

  @Operation(
      tags = {"MemberAPI"},
      summary = "마이페이지 > 프로필 정보 조회",
      description = "프로필 정보를 조회합니다.",
      responses = {
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
              responseCode = "200",
              description = "OK",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ProfileDto.Response.class)
              )
          ),
      }
  )
  ApiResponse<ProfileDto.Response> getProfile(Long memberId);

  @Operation(
      tags = {"MemberAPI"},
      summary = "마이페이지 > 프로필 요약 조회",
      description = "프로필 요약을 조회합니다.",
      responses = {
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
              responseCode = "200",
              description = "OK",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = SummaryDto.Response.class)
              )
          ),
      }
  )
  ApiResponse<SummaryDto.Response> getSummary(Long memberId);

  @Operation(
      tags = {"MemberAPI"},
      summary = "마이페이지 > 게시글 목록 조회",
      description = "게시글 목록을 조회합니다.",
      responses = {
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
              responseCode = "200",
              description = "OK",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = PostDto.Response.class)
              )
          ),
      }
  )
  ApiResponse<PostDto.Response> getPosts(Long memberId);

  @Operation(
      tags = {"MemberAPI"},
      summary = "마이페이지 > 댓글 목록 조회",
      description = "댓글 목록을 조회합니다.",
      responses = {
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
              responseCode = "200",
              description = "OK",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = CommentDto.Response.class)
              )
          ),
      }
  )
  ApiResponse<CommentDto.Response> getComments(Long memberId);

  @Operation(
      tags = {"MemberAPI"},
      summary = "마이페이지 > 팔로워 목록 조회",
      description = "팔로워 목록을 조회합니다.",
      responses = {
          @io.swagger.v3.oas.annotations.responses.ApiResponse(
              responseCode = "200",
              description = "OK",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = FollowerDto.Response.class)
              )
          ),
      }
  )
  ApiResponse<FollowerDto.Response> getFollowers(Long memberId);

}
