package com.bangbangbwa.backend.domain.admin.repository;

import com.bangbangbwa.backend.domain.admin.common.entity.Admin;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdminRepository {

  private final SqlSession mysql;

  public void save(Admin admin) { mysql.insert("AdminMapper.save", admin); }

  public Optional<Admin> findById(Long adminId) {
    return Optional.ofNullable(mysql.selectOne("AdminMapper.findById", adminId));
  }
}
