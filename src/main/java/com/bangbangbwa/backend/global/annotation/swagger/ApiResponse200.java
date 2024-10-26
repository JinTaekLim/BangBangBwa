package com.bangbangbwa.backend.global.annotation.swagger;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Documented
@ApiResponse(responseCode = "200", description = "OK",
    content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = com.bangbangbwa.backend.global.response.ApiResponse.class),
        examples = @ExampleObject(
            value = """
                {
                  "code": "OK",
                  "message": "OK",
                  "data": null
                }"""
        )
    )
)
public @interface ApiResponse200 {

}
