package com.bangbangbwa.backend.domain.token.repository;

import com.bangbangbwa.backend.domain.token.common.entity.Token;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TokenRepository {

  private final SqlSession mysql;

  public void save(Token token) {
    mysql.insert("TokenMapper.save", token);
  }

  public Optional<Token> findByRefreshToken(String refreshToken) {
    return Optional.ofNullable(mysql.selectOne("TokenMapper.findByToken", refreshToken));
  }

  public void deleteByRefreshToken(Token token){
    mysql.delete("TokenMapper.delete", token);
  };
}
