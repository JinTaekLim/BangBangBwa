package com.bangbangbwa.backend.domain.tag.controller;

import com.bangbangbwa.backend.domain.tag.common.dto.TagListDto;
import com.bangbangbwa.backend.domain.tag.common.dto.TagListDto.Response;
import com.bangbangbwa.backend.domain.tag.service.TagService;
import com.bangbangbwa.backend.global.response.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController implements TagApi {

  private final TagService tagService;

  @GetMapping
  public ApiResponse<TagListDto.Response> tagList(
      @RequestParam String tagWord
  ) {
    List<String> tagList = tagService.getTagList(tagWord);
    TagListDto.Response response = new Response(tagList);
    return ApiResponse.ok(response);
  }
}
