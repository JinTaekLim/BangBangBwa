package com.bangbangbwa.backend.domain.token.business;


import com.bangbangbwa.backend.domain.token.common.exception.ExpiredTokenException;
import com.bangbangbwa.backend.domain.token.common.exception.InvalidJwtSignatureException;
import com.bangbangbwa.backend.domain.token.common.exception.InvalidTokenException;
import com.bangbangbwa.backend.global.error.exception.UnAuthenticatedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class TokenValidator {

  private final SecretKey ACCESS_SECRET;
  private final SecretKey REFRESH_SECRET;
  private static final String Bearer = "Bearer ";

  public TokenValidator(
      @Value("${jwt.access.secret}") String accessSecret,
      @Value("${jwt.refresh.secret}") String refreshSecret
  )
  {
    byte[] accessKeyBytes = Decoders.BASE64.decode(accessSecret);
    byte[] refreshKeyBytes = Decoders.BASE64.decode(refreshSecret);
    this.ACCESS_SECRET = Keys.hmacShaKeyFor(accessKeyBytes);
    this.REFRESH_SECRET = Keys.hmacShaKeyFor(refreshKeyBytes);
  }

  public void validateRefreshToken(String refreshToken) {
    validateToken(refreshToken, REFRESH_SECRET);
  }

  public void validateAccessToken(String accessToken) {
    validateToken(accessToken, ACCESS_SECRET);
  }

  private void validateToken(String token, SecretKey refreshSecret) {
    this.getClaims(token, refreshSecret);
  }

  public Claims getClaimsFromAccessToken(String accessToken) {
    return getClaims(accessToken, ACCESS_SECRET);
  }

  private Claims getClaims(String token, SecretKey refreshSecret) {
    try {
      return Jwts.parser().verifyWith(refreshSecret).build().parseSignedClaims(token).getPayload();
    } catch (ExpiredJwtException e) {
      throw new ExpiredTokenException();
    } catch (SignatureException e) {
      throw new InvalidJwtSignatureException();
    } catch (JwtException e) {
      throw new InvalidTokenException(e.getMessage());
    } catch (Exception e) {
      throw new UnAuthenticatedException(e.getMessage());
    }
  }


  public String getTokenFromAuthorizationHeader(HttpServletRequest request) {
    String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (StringUtils.hasText(header) && header.startsWith(Bearer)) {
      return header.replace(Bearer, "");
    }
    return null;
  }
}
