package com.bangbangbwa.backend.domain.token.interceptor;

import com.bangbangbwa.backend.domain.token.common.exception.AuthenticationRequiredException;
import com.bangbangbwa.backend.domain.token.service.TokenService;
import com.bangbangbwa.backend.global.annotation.authentication.PermitAll;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

  private final TokenService tokenService;

  // PreAuthorize 어노테이션이 달린 API 는 무조건 accessToken 이 포함되어야한다.
  // 이외 어노테이션이 달린 API는 accessToken이 있으면 인증 과정을, 없으면 true를 반환해야한다.
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    // 현재 요구사항
    // 미인증 회원 + 인증 회원 둘 모두 사용이 가능한 API가 존재.


    String accessToken = tokenService.getTokenFromAuthorizationHeader(request);

    if (hasAnnotation(handler, PreAuthorize.class)) {
      validateToken(accessToken); // 인증 + 토큰 검증
      setAuthentication(accessToken); // SpringSecurityContextHolder Authentication 등록
      // 인증 완료.
    } else if(hasAnnotation(handler, PermitAll.class)){
      if (!isTokenNull(accessToken)) {
        setAuthentication(accessToken);
      }
    }
    return true;
  }

  private boolean hasAnnotation(Object handler, Class<? extends Annotation> clazz) {
    // Swagger 같은 js/html 관련 파일들은 통과한다.(view 관련 요청 = ResourceHttpRequestHandler)
    if (handler instanceof ResourceHttpRequestHandler) {
      return false;
    }
    HandlerMethod handlerMethod = (HandlerMethod) handler;
    return handlerMethod.getMethod().isAnnotationPresent(clazz);
  }

  private void validateToken(String token){
    if(isTokenNull(token)){ throw new AuthenticationRequiredException(); }
  }

  private boolean isTokenNull(String token) {
    return !StringUtils.hasText(token);
  }

  private void setAuthentication(String token) {
    tokenService.validateAccessToken(token);
    Authentication authentication = tokenService.getAuthenticationByAccessToken(token);
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }
}
