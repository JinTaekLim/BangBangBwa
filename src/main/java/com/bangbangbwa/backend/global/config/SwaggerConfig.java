package com.bangbangbwa.backend.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    Info info = new Info();
    info.title("BangBangBwa Api Documentation").description("방방봐의 API 문서입니다.")
        .version("1.0.0");

//    todo : 인증/인가 로직 구현 후 활성화 예정
//    SecurityScheme jwtSecurityScheme = new SecurityScheme().type(SecurityScheme.Type.HTTP)
//        .scheme("bearer")
//        .bearerFormat("JWT")
//        .in(SecurityScheme.In.HEADER)
//        .name("Authorization");

//    SecurityRequirement schemaRequirement = new SecurityRequirement()
//        .addList("jwt");

    return new OpenAPI()
//        .components(new Components().addSecuritySchemes("jwt", jwtSecurityScheme))
//        .addSecurityItem(schemaRequirement)
        .info(info);
  }
}

