package com.bangbangbwa.backend.global.annotation.authentication;

import com.bangbangbwa.backend.domain.token.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RequiredArgsConstructor
@Component
@Aspect
public class SetAuthenticationContext {

  private final TokenService tokenService;

  @Before("@annotation(com.bangbangbwa.backend.global.annotation.authentication.AuthenticationContext)")
  public void setAuthentication() {
    HttpServletRequest request = getRequest();
    if (request == null) return;

    String accessToken = tokenService.getTokenFromAuthorizationHeader(request);

    if (accessToken == null || accessToken.isEmpty()) return;
    tokenService.validateAccessToken(accessToken);

    Authentication auth = tokenService.getAuthenticationByAccessToken(accessToken);
    SecurityContextHolder.getContext().setAuthentication(auth);
  }

  private HttpServletRequest getRequest() {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    return (attributes != null) ? attributes.getRequest() : null;
  }
}
