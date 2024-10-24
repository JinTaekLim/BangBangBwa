package com.bangbangbwa.backend.global.config.log;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.util.ContentCachingResponseWrapper;

import static org.springframework.web.multipart.support.MultipartResolutionDelegate.isMultipartRequest;


@Slf4j
public class LogFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain)
      throws ServletException, IOException {

    CachingRequestWrapper requestWrapper = new CachingRequestWrapper(request);
    ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
    final UUID uuid = UUID.randomUUID();

    long startTime = System.currentTimeMillis();
    try {
      MDC.put("uuid", uuid.toString()); // 로그 별 ID 값 추가
      logRequest(requestWrapper);
      filterChain.doFilter(requestWrapper, responseWrapper);
    } finally {
      logResponse(responseWrapper, startTime);
      responseWrapper.copyBodyToResponse();
      MDC.clear();
    }
  }

  private void logRequest(HttpServletRequestWrapper request) throws IOException {
    String queryString = request.getQueryString();
    String body = getBody(request.getInputStream());

    log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    log.info("Request : {} uri=[{}] content-type=[{}], body=[{}]"
        , request.getMethod()
        ,
        queryString == null ? request.getRequestURI() : request.getRequestURI() + "?" + queryString
        , request.getContentType()
        , body);

    if (isMultipartRequest(request)) {
      logMultipartRequest(request);
    }
  }

  private void logMultipartRequest(HttpServletRequestWrapper request) {
    try {
      StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();
      MultipartHttpServletRequest multipartRequest = multipartResolver.resolveMultipart(request);

      // 파일 정보 로깅
      multipartRequest.getFileMap().forEach((paramName, file) -> {
        log.info("File Parameter Name: {}, Original File Name: {}, Size: {} bytes"
                , paramName
                , file.getOriginalFilename()
                , file.getSize());
      });

      // 일반 폼 파라미터 로깅
      multipartRequest.getParameterMap().forEach((paramName, values) -> {
        for (String value : values) {
          log.info("Form Field - Name: {}, Value: {}"
                  , paramName
                  , value);
        }
      });

    } catch (Exception e) {
      log.warn("Failed to log multipart request details", e);
    }
  }

  private void logResponse(ContentCachingResponseWrapper response, long startTime)
      throws IOException {
    String body = getBody(response.getContentInputStream());

    log.info("Response : {} body=[{}]"
        , response.getStatus()
        , body);
    log.info("Request processed in {}ms", (System.currentTimeMillis() - startTime));
    log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
  }

  public String getBody(InputStream is) throws IOException {
    byte[] content = StreamUtils.copyToByteArray(is);
    if (content.length == 0) {
      return null;
    }
    return new String(content, StandardCharsets.UTF_8);
  }
}