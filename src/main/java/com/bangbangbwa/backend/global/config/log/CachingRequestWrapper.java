package com.bangbangbwa.backend.global.config.log;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CachingRequestWrapper extends HttpServletRequestWrapper {

  private final byte[] cachedContent;

  public CachingRequestWrapper(HttpServletRequest request) throws IOException {
    super(request);

    if (isMultipartContent(request)) {
      StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();
      multipartResolver.resolveMultipart(request);
    }

    this.cachedContent = StreamUtils.copyToByteArray(request.getInputStream());
  }

  private boolean isMultipartContent(HttpServletRequest request) {
    String contentType = request.getContentType();
    return contentType != null && contentType.startsWith(MediaType.MULTIPART_FORM_DATA_VALUE);
  }

  @Override
  public ServletInputStream getInputStream() {
    return new CachedServletInputStream(cachedContent);
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
