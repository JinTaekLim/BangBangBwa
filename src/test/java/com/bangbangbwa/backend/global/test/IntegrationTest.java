package com.bangbangbwa.backend.global.test;

import com.bangbangbwa.backend.global.gson.LocalDateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = "logging.config=classpath:logback-spring-test.xml")
abstract public class IntegrationTest {

  protected MockMvc mvc;

  protected Gson gson = new GsonBuilder()
      .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
      .create();


  @Autowired
  private WebApplicationContext context;

  @Autowired
  private DatabaseCleaner cleaner;

  @BeforeAll
  static void setUpAll() throws IOException {
    EmbeddedServer.startRedis();
  }

  @AfterAll
  static void tearDownAll() throws IOException {
    EmbeddedServer.stopRedis();
  }


  @BeforeEach
  void setUp() {
    mvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @AfterEach
  void tearDown() {
    cleaner.execute();
  }
}
