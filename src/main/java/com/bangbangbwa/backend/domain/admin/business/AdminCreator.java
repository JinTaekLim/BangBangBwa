package com.bangbangbwa.backend.domain.admin.business;

import com.bangbangbwa.backend.domain.admin.common.entity.Admin;
import com.bangbangbwa.backend.domain.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminCreator {

  private final AdminRepository adminRepository;

  public void save(Admin admin) {
    adminRepository.save(admin);
  }

}
