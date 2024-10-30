package com.bangbangbwa.backend.domain.promotion.repository;

import com.bangbangbwa.backend.domain.promotion.common.entity.Banner;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BannerRepository {

  private final SqlSession mysql;


  public List<Banner> findAll() {
    return mysql.selectList("BannerMapper.findAll");
  }
}
