package com.bangbangbwa.backend.domain.admin.business;

import com.bangbangbwa.backend.domain.admin.common.entity.Admin;
import com.bangbangbwa.backend.domain.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminProvider {

  private final AdminReader adminReader;
  private final AdminCreator adminCreator;
  private final AdminRepository adminRepository;

  public Admin getCurrentAdmin() {
    //note. 세션 로그인 및 관리자 작업시 수정 필요
    return adminRepository.findById(1L).orElseGet(() -> {
      Admin admin = Admin.builder().build();
      adminCreator.save(admin);
      return admin;
    });
  }

}
