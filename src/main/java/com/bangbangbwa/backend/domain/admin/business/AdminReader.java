package com.bangbangbwa.backend.domain.admin.business;

import com.bangbangbwa.backend.domain.admin.common.entity.Admin;
import com.bangbangbwa.backend.domain.admin.exception.NotFoundAdminException;
import com.bangbangbwa.backend.domain.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminReader {

  private final AdminRepository adminRepository;

  public Admin findById(Long id) {
    return adminRepository.findById(id).orElseThrow(NotFoundAdminException::new);
  }


}
