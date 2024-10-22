package com.bangbangbwa.backend.domain.token.business;

import com.bangbangbwa.backend.domain.member.common.Member;
import java.util.Collections;
import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class AuthProvider {

  public Authentication getAuthentication(Member member) {
    List<SimpleGrantedAuthority> authorities = getAuthorities(member);
    User principal = new User(String.valueOf(member.getId()), "", authorities);
    return new UsernamePasswordAuthenticationToken(principal, null, authorities);
  }

  private List<SimpleGrantedAuthority> getAuthorities(Member member) {
    return Collections.singletonList(
        new SimpleGrantedAuthority(member.getRole().name()));
  }
}
