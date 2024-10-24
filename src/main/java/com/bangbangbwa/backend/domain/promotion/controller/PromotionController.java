package com.bangbangbwa.backend.domain.promotion.controller;

import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionBannerDto;
import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionBannerDto.PromotionBannerResponseBanner;
import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionStreamerDto;
import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionStreamerDto.PromotionStreamerResponseStreamer;
import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionStreamerDto.PromotionStreamerResponseStreamerInterested;
import com.bangbangbwa.backend.global.response.ApiResponse;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/promotions")
@RequiredArgsConstructor
public class PromotionController implements PromotionApi {

  @GetMapping("/banners")
  public ApiResponse<PromotionBannerDto.Response> getBanners() {
    List<PromotionBannerResponseBanner> banners = new ArrayList<>();
    banners.add(
        new PromotionBannerDto.PromotionBannerResponseBanner(
            "https://www.google.co.kr/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
            "#FFFFFF"
        ));
    banners.add(
        new PromotionBannerDto.PromotionBannerResponseBanner(
            "https://www.google.co.kr/images/branding/googlelogo/1x/googlelogo_light_color_272x92dp.png",
            "#000000"
        ));
    banners.add(
        new PromotionBannerDto.PromotionBannerResponseBanner(
            "https://corp-homepage-phinf.pstatic.net/MjAyNDEwMjJfMzkg/MDAxNzI5NTYwNTkxNDU4.D12Vl55dLYFTuVFDahpRJuBM-zbCGGqm3g1JGyOI2isg.pdr_g8xDa4ASnDnQ9or7eoXJZv7oUOkfrfGvd0vmg_Mg.PNG/1_NAVER_%282%29.png",
            "#2DB400"
        ));
    PromotionBannerDto.Response response = new PromotionBannerDto.Response(banners);

    return ApiResponse.ok(response);
  }

  @GetMapping("/streamers")
  public ApiResponse<PromotionStreamerDto.Response> getStreamers() {
    List<PromotionStreamerResponseStreamer> streamerList = new ArrayList<>();
    PromotionStreamerResponseStreamer streamer;
    List<PromotionStreamerResponseStreamerInterested> interestedList;

    interestedList = new LinkedList<>();
    interestedList.add(new PromotionStreamerResponseStreamerInterested(true, "음악"));
    interestedList.add(new PromotionStreamerResponseStreamerInterested(true, "영화"));
    interestedList.add(new PromotionStreamerResponseStreamerInterested(false, "게임"));
    streamer = new PromotionStreamerResponseStreamer(
        "http://res.heraldm.com/phpwas/restmb_idxmake.php?idx=640&simg=/content/image/2024/04/02/20240402050018_0.jpg",
        "/streamers/profiles/1",
        interestedList,
        "정해인"
    );
    streamerList.add(streamer);

    interestedList = new LinkedList<>();
    interestedList.add(new PromotionStreamerResponseStreamerInterested(false, "게임"));
    interestedList.add(new PromotionStreamerResponseStreamerInterested(false, "개그"));
    interestedList.add(new PromotionStreamerResponseStreamerInterested(false, "공부"));
    interestedList.add(new PromotionStreamerResponseStreamerInterested(false, "독서"));
    streamer = new PromotionStreamerResponseStreamer(
        "https://www.paxetv.com/news/photo/202202/137816_111215_445.jpg",
        "/streamers/profiles/2",
        interestedList,
        "잘생김"
    );
    streamerList.add(streamer);

    interestedList = new LinkedList<>();
    interestedList.add(new PromotionStreamerResponseStreamerInterested(true, "토크"));
    interestedList.add(new PromotionStreamerResponseStreamerInterested(true, "여행"));
    interestedList.add(new PromotionStreamerResponseStreamerInterested(false, "등산"));
    streamer = new PromotionStreamerResponseStreamer(
        "https://i.namu.wiki/i/dSNY8iAyQ5BJLLeorpoQkaF_X3Hi1ewKaHxWb8I7rJPj8mq4ho_R9SmpMibUn-AwoK9vpdc7WlgcbKxIwEOj1m6XUbAa-8q8qM-byMZVrtQhrQVmC-sQiv8t8jPcrhz2pXo9lbsnTZDlD3FG7ZviyQ.webp",
        "/streamers/profiles/3",
        interestedList,
        "차은우"
    );
    streamerList.add(streamer);

    interestedList = new LinkedList<>();
    interestedList.add(new PromotionStreamerResponseStreamerInterested(true, "영화"));
    interestedList.add(new PromotionStreamerResponseStreamerInterested(true, "토크"));
    interestedList.add(new PromotionStreamerResponseStreamerInterested(false, "개그"));
    streamer = new PromotionStreamerResponseStreamer(
        "https://image.news1.kr/system/photos/2024/2/24/6498986/high.jpg/dims/optimize",
        "/streamers/profiles/4",
        interestedList,
        "멋있다"
    );
    streamerList.add(streamer);

    PromotionStreamerDto.Response response = new PromotionStreamerDto.Response(streamerList);
    return ApiResponse.ok(response);
  }
}
