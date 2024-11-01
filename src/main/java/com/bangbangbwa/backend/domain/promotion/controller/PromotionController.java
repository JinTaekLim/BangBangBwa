package com.bangbangbwa.backend.domain.promotion.controller;

import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionBannerDto;
import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionBannerDto.PromotionBannerResponseBanner;
import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionStreamerDto;
import com.bangbangbwa.backend.domain.promotion.common.dto.PromotionStreamerDto.PromotionStreamerResponseStreamer;
import com.bangbangbwa.backend.domain.promotion.service.BannerService;
import com.bangbangbwa.backend.domain.promotion.service.StreamerService;
import com.bangbangbwa.backend.global.response.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/promotions")
@RequiredArgsConstructor
public class PromotionController implements PromotionApi {

  private final BannerService bannerService;
  private final StreamerService streamerService;

  @GetMapping("/banners")
  public ApiResponse<PromotionBannerDto.Response> getBanners() {
    List<PromotionBannerResponseBanner> banners = bannerService.getBanners();
    PromotionBannerDto.Response response = new PromotionBannerDto.Response(banners);
    return ApiResponse.ok(response);
  }

  @GetMapping("/streamers")
  public ApiResponse<PromotionStreamerDto.Response> getStreamers() {
    List<PromotionStreamerResponseStreamer> streamerList = streamerService.getRandomStreamers();
//    PromotionStreamerResponseStreamer streamer;
//    List<PromotionStreamerResponseStreamerInterested> interestedList;
//    List<PromotionStreamerResponseStreamerPlatform> platformList;
//
//    interestedList = new LinkedList<>();
//    interestedList.add(new PromotionStreamerResponseStreamerInterested(true, "음악"));
//    interestedList.add(new PromotionStreamerResponseStreamerInterested(true, "영화"));
//    interestedList.add(new PromotionStreamerResponseStreamerInterested(false, "게임"));
//    platformList = new LinkedList<>();
//    platformList.add(new PromotionStreamerResponseStreamerPlatform(
//        "SOOP",
//        "https://nimage.g-enews.com/phpwas/restmb_allidxmake.php?idx=5&simg=2024032909554105611c5fa75ef8612254575.jpg",
//        "https://ch.sooplive.co.kr/rrvv17"));
//    platformList.add(new PromotionStreamerResponseStreamerPlatform(
//        "YOUTUBE",
//        "https://yt3.googleusercontent.com/ytc/AIdro_mjPOB8h-cMEZq3ctWbl3AHfCcNiO_vgr5Gym-NJAlDXJ4=s900-c-k-c0x00ffffff-no-rj",
//        "https://www.youtube.com/@ksttv"));
//    platformList.add(new PromotionStreamerResponseStreamerPlatform(
//        "CHZZK",
//        "https://api.brandb.net/api/v2/common/image/?fileId=15004",
//        "https://chzzk.naver.com/b8263cd74372864eacb2d5bd16551882"));
//    streamer = new PromotionStreamerResponseStreamer(
//        "한마디",
//        "안녕하세요 이번에 새로 시작하게된 스트리머 이수연 입니다.",
//        "http://res.heraldm.com/phpwas/restmb_idxmake.php?idx=640&simg=/content/image/2024/04/02/20240402050018_0.jpg",
//        "이수연",
//        interestedList,
//        platformList
//    );
//    streamerList.add(streamer);
//
//    interestedList = new LinkedList<>();
//    interestedList.add(new PromotionStreamerResponseStreamerInterested(false, "게임"));
//    interestedList.add(new PromotionStreamerResponseStreamerInterested(false, "개그"));
//    interestedList.add(new PromotionStreamerResponseStreamerInterested(false, "공부"));
//    interestedList.add(new PromotionStreamerResponseStreamerInterested(false, "독서"));
//    platformList = new LinkedList<>();
//    platformList.add(new PromotionStreamerResponseStreamerPlatform(
//        "SOOP",
//        "https://nimage.g-enews.com/phpwas/restmb_allidxmake.php?idx=5&simg=2024032909554105611c5fa75ef8612254575.jpg",
//        "https://ch.sooplive.co.kr/poos69"));
//    platformList.add(new PromotionStreamerResponseStreamerPlatform(
//        "YOUTUBE",
//        "https://yt3.googleusercontent.com/ytc/AIdro_mjPOB8h-cMEZq3ctWbl3AHfCcNiO_vgr5Gym-NJAlDXJ4=s900-c-k-c0x00ffffff-no-rj",
//        "https://www.youtube.com/@%EC%A7%80%EB%AC%B4%EB%B9%84"));
//    platformList.add(new PromotionStreamerResponseStreamerPlatform(
//        "CHZZK",
//        "https://api.brandb.net/api/v2/common/image/?fileId=15004",
//        "https://chzzk.naver.com/c100f81959d1c17044be0541eed56f5b"));
//    streamer = new PromotionStreamerResponseStreamer(
//        "두마디",
//        "안녕하세요 이번에 새로 시작하게된 스트리머 손지영 입니다.",
//        "http://res.heraldm.com/phpwas/restmb_idxmake.php?idx=640&simg=/content/image/2024/04/02/20240402050018_0.jpg",
//        "손지영",
//        interestedList,
//        platformList
//    );
//    streamerList.add(streamer);
//
//    interestedList = new LinkedList<>();
//    interestedList.add(new PromotionStreamerResponseStreamerInterested(true, "토크"));
//    interestedList.add(new PromotionStreamerResponseStreamerInterested(true, "여행"));
//    interestedList.add(new PromotionStreamerResponseStreamerInterested(false, "등산"));
//    platformList = new LinkedList<>();
//    platformList.add(new PromotionStreamerResponseStreamerPlatform(
//        "SOOP",
//        "https://nimage.g-enews.com/phpwas/restmb_allidxmake.php?idx=5&simg=2024032909554105611c5fa75ef8612254575.jpg",
//        "https://ch.sooplive.co.kr/lovely5959"));
//    platformList.add(new PromotionStreamerResponseStreamerPlatform(
//        "YOUTUBE",
//        "https://yt3.googleusercontent.com/ytc/AIdro_mjPOB8h-cMEZq3ctWbl3AHfCcNiO_vgr5Gym-NJAlDXJ4=s900-c-k-c0x00ffffff-no-rj",
//        "https://www.youtube.com/@sangho_full"));
//    platformList.add(new PromotionStreamerResponseStreamerPlatform(
//        "CHZZK",
//        "https://api.brandb.net/api/v2/common/image/?fileId=15004",
//        "https://chzzk.naver.com/0dad8baf12a436f722faa8e5001c5011"));
//    streamer = new PromotionStreamerResponseStreamer(
//        "세마디",
//        "안녕하세요 이번에 새로 시작하게된 스트리머 김철수 입니다.",
//        "http://res.heraldm.com/phpwas/restmb_idxmake.php?idx=640&simg=/content/image/2024/04/02/20240402050018_0.jpg",
//        "김철수",
//        interestedList,
//        platformList
//    );
//    streamerList.add(streamer);

    //    interestedList = new LinkedList<>();
//    interestedList.add(new PromotionStreamerResponseStreamerInterested(true, "국내여행"));
//    interestedList.add(new PromotionStreamerResponseStreamerInterested(true, "산악바이크"));
//    interestedList.add(new PromotionStreamerResponseStreamerInterested(false, "산책"));
//    platformList = new LinkedList<>();
//    platformList.add(new PromotionStreamerResponseStreamerPlatform(
//        "SOOP",
//        "https://nimage.g-enews.com/phpwas/restmb_allidxmake.php?idx=5&simg=2024032909554105611c5fa75ef8612254575.jpg",
//        "https://ch.sooplive.co.kr/lovely5959"));
//    platformList.add(new PromotionStreamerResponseStreamerPlatform(
//        "YOUTUBE",
//        "https://yt3.googleusercontent.com/ytc/AIdro_mjPOB8h-cMEZq3ctWbl3AHfCcNiO_vgr5Gym-NJAlDXJ4=s900-c-k-c0x00ffffff-no-rj",
//        "https://www.youtube.com/@sangho_full"));
//    platformList.add(new PromotionStreamerResponseStreamerPlatform(
//        "CHZZK",
//        "https://api.brandb.net/api/v2/common/image/?fileId=15004",
//        "https://chzzk.naver.com/0dad8baf12a436f722faa8e5001c5011"));
//    streamer = new PromotionStreamerResponseStreamer(
//        "네마디",
//        "안녕하세요 이번에 새로 시작하게된 스트리머 이민정 입니다.",
//        "http://res.heraldm.com/phpwas/restmb_idxmake.php?idx=640&simg=/content/image/2024/04/02/20240402050018_0.jpg",
//        "이민정",
//        interestedList,
//        platformList
//    );
//    streamerList.add(streamer);

    PromotionStreamerDto.Response response = new PromotionStreamerDto.Response(streamerList);
    return ApiResponse.ok(response);
  }
}
