package com.bangbangbwa.backend.global.test;

import jakarta.annotation.PostConstruct;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DatabaseCleaner {

  private final JdbcTemplate jdbcTemplate;
  private List<String> tableNames;

  public DatabaseCleaner(final JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @PostConstruct
  public void init() {
    tableNames = jdbcTemplate.queryForList(
        "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC'",
        String.class);

    this.execute();
  }

  @Transactional
  public void execute() {
    jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY FALSE");
    for (String tableName : tableNames) {
      jdbcTemplate.execute("TRUNCATE TABLE " + tableName);
    }
    jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY TRUE");
  }
}
