package com.bangbangbwa.backend.global.test;


import com.bangbangbwa.backend.global.config.MyBatisConfig;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@ActiveProfiles("test")
@MybatisTest
@TestPropertySource(properties = "logging.config=classpath:logback-spring-test.xml")
@Import({MyBatisConfig.class, DatabaseCleaner.class})
abstract public class MyBatisTest {

}
