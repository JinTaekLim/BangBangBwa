package com.bangbangbwa.backend.global.test;

import com.bangbangbwa.backend.global.error.GlobalExceptionHandler;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ActiveProfiles("test")
@SuppressWarnings("NonAsciiCharacters")
@TestPropertySource(properties = "logging.config=classpath:logback-spring-test.xml")
abstract public class ControllerTest {

  protected MockMvc mvc;

  protected final Gson gson = new Gson();

  @BeforeEach
  void setUp() {
    mvc = MockMvcBuilders.standaloneSetup(initController())
        .setControllerAdvice(GlobalExceptionHandler.class)
        .build();
  }

  abstract protected Object initController();

}