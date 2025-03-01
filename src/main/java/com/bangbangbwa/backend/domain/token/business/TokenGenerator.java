package com.bangbangbwa.backend.domain.token.business;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class TokenGenerator {

  public static final String ROLE_CLAIM = "role";

  private final SecretKey ACCESS_SECRET;
  private final SecretKey REFRESH_SECRET;
  private final Long ACCESS_EXP;
  private final Long REFRESH_EXP;

  public TokenGenerator(
      @Value("${jwt.access.secret}") String accessSecret,
      @Value("${jwt.access.exp}") Long accessExp,
      @Value("${jwt.refresh.secret}") String refreshSecret,
      @Value("${jwt.refresh.exp}") Long refreshExp
  ) {
    byte[] accessKeyBytes = Decoders.BASE64.decode(accessSecret);
    byte[] refreshKeyBytes = Decoders.BASE64.decode(refreshSecret);
    this.ACCESS_SECRET = Keys.hmacShaKeyFor(accessKeyBytes);
    this.REFRESH_SECRET = Keys.hmacShaKeyFor(refreshKeyBytes);
    this.ACCESS_EXP = accessExp;
    this.REFRESH_EXP = refreshExp;
  }

  protected long getRefreshExp(){ return REFRESH_EXP; }

  public String generateAccessToken(Authentication authentication) {
    return createToken(authentication, ACCESS_SECRET, ACCESS_EXP);
  }

  public String generateRefreshToken(Authentication authentication) {
    return createToken(authentication, REFRESH_SECRET, REFRESH_EXP);
  }

  private String createToken(Authentication authentication, SecretKey secret, Long exp) {
    Date now = new Date();
    Date expiredDate = new Date(now.getTime() + exp);
    String authorities = authentication.getAuthorities()
        .stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining());
    return Jwts.builder()
        .subject(authentication.getName())
        .claim(ROLE_CLAIM, authorities)
        .issuedAt(now)
        .expiration(expiredDate)
        .signWith(secret)
        .compact();
  }
}
