package com.bangbangbwa.backend.global.config.log;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.web.firewall.FirewalledRequest;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Slf4j
public class CachingRequestWrapper extends HttpServletRequestWrapper {

  private final byte[] cachedContent;
  private final Map<String, String> cachedParameters = new HashMap<>();
  private final Map<String, MultipartFile> cachedFiles = new HashMap<>();

  public CachingRequestWrapper(HttpServletRequest request) throws IOException {
    super(request);

    if (isMultipartContent(request)) {
      // Multipart 요청 캐싱
      StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();
      MultipartHttpServletRequest multipartRequest = multipartResolver.resolveMultipart(request);

      // 일반 파라미터 캐싱
      multipartRequest.getParameterMap().forEach((key, values) -> {
        cachedParameters.put(key, values[0]);
      });

      // 파일 캐싱
      multipartRequest.getFileMap().forEach((key, file) -> {
        try {
          cachedFiles.put(key, new CachedMultipartFile(file));
        } catch (IOException e) {
          log.error("Failed to cache file: {}", key, e);
        }
      });

      // 전체 raw content 캐싱 (선택적)
      this.cachedContent = StreamUtils.copyToByteArray(request.getInputStream());
    } else {
      // 일반 요청 캐싱
      this.cachedContent = StreamUtils.copyToByteArray(request.getInputStream());
    }
  }

  private boolean isMultipartContent(HttpServletRequest request) {
    String contentType = request.getContentType();
    return contentType != null && contentType.startsWith(MediaType.MULTIPART_FORM_DATA_VALUE);
  }

  @Override
  public ServletInputStream getInputStream() {
    return new CachedServletInputStream(cachedContent);
  }

  // MultipartFile을 캐싱하기 위한 래퍼 클래스
  private static class CachedMultipartFile implements MultipartFile {
    private final String name;
    private final String originalFilename;
    private final String contentType;
    private final byte[] content;

    public CachedMultipartFile(MultipartFile file) throws IOException {
      this.name = file.getName();
      this.originalFilename = file.getOriginalFilename();
      this.contentType = file.getContentType();
      this.content = file.getBytes();
    }

    @Override
    public String getName() {
      return name;
    }

    @Override
    public String getOriginalFilename() {
      return originalFilename;
    }

    @Override
    public String getContentType() {
      return contentType;
    }

    @Override
    public boolean isEmpty() {
      return content.length == 0;
    }

    @Override
    public long getSize() {
      return content.length;
    }

    @Override
    public byte[] getBytes() {
      return content;
    }

    @Override
    public InputStream getInputStream() {
      return new ByteArrayInputStream(content);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
      Files.write(dest.toPath(), content);
    }
  }

  private static class CachedServletInputStream extends ServletInputStream {
    private final InputStream cachedBodyInputStream;

    public CachedServletInputStream(byte[] cachedBody) {
      this.cachedBodyInputStream = new ByteArrayInputStream(cachedBody);
    }

    @Override
    public boolean isFinished() {
      try {
        return cachedBodyInputStream.available() == 0;
      } catch (IOException e) {
        return true;
      }
    }

    @Override
    public boolean isReady() {
      return true;
    }

    @Override
    public void setReadListener(ReadListener listener) {
      throw new UnsupportedOperationException();
    }

    @Override
    public int read() throws IOException {
      return cachedBodyInputStream.read();
    }
  }
}
