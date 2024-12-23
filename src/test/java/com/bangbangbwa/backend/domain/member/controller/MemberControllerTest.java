package com.bangbangbwa.backend.domain.member.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bangbangbwa.backend.domain.member.service.MemberService;
import com.bangbangbwa.backend.domain.oauth.service.OAuthService;
import com.bangbangbwa.backend.domain.streamer.service.PendingStreamerService;
import com.bangbangbwa.backend.domain.tag.service.TagService;
import com.bangbangbwa.backend.domain.token.service.TokenService;
import com.bangbangbwa.backend.global.test.ControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@WebMvcTest(MemberControllerTest.class)
class MemberControllerTest extends ControllerTest {

  @MockBean
  private OAuthService oAuthService;
  @MockBean
  private MemberService memberService;
  @MockBean
  private TokenService tokenService;
  @MockBean
  private PendingStreamerService pendingStreamerService;
  @MockBean
  private TagService tagService;

  @Override
  protected Object initController() {
    return new MemberController(oAuthService, memberService, tokenService, pendingStreamerService,
        tagService);
  }

  @Test
  void getProfile() throws Exception {
    mvc.perform(get("/api/v1/members/profile/1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.code").value(HttpStatus.OK.name()))
        .andExpect(jsonPath("$.message").value(HttpStatus.OK.getReasonPhrase()));
  }

  @Test
  void getSummary() throws Exception {
    mvc.perform(get("/api/v1/members/summary/1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.code").value(HttpStatus.OK.name()))
        .andExpect(jsonPath("$.message").value(HttpStatus.OK.getReasonPhrase()));
  }

  @Test
  void getPosts() throws Exception {
    mvc.perform(get("/api/v1/members/posts/1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.code").value(HttpStatus.OK.name()))
        .andExpect(jsonPath("$.message").value(HttpStatus.OK.getReasonPhrase()));
  }

  @Test
  void getComments() throws Exception {
    mvc.perform(get("/api/v1/members/comments"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.code").value(HttpStatus.OK.name()))
        .andExpect(jsonPath("$.message").value(HttpStatus.OK.getReasonPhrase()));
  }

  @Test
  void getFollowers() throws Exception {
    mvc.perform(get("/api/v1/members/followers/1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.code").value(HttpStatus.OK.name()))
        .andExpect(jsonPath("$.message").value(HttpStatus.OK.getReasonPhrase()));
  }
}