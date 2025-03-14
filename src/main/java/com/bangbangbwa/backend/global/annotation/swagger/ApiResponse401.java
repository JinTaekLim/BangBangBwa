package com.bangbangbwa.backend.global.annotation.swagger;

import io.swagger.v3.oas.annotations.media.ArraySchema;
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
import org.springframework.http.MediaType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
@ApiResponse(responseCode = "401", description = "미인증",
    content = @Content(
        mediaType = MediaType.APPLICATION_JSON_VALUE,
        array = @ArraySchema(schema = @Schema(implementation = com.bangbangbwa.backend.global.response.ApiResponse.class)),
        examples = {
            @ExampleObject(
                name = "AUTHENTICATION_NULL_ERROR",
                summary = "인증되지 않은 접근",
                value = """
                    {
                      "code": "AUTHENTICATION_NULL_ERROR",
                      "message": "Authentication 이 NULL 입니다.",
                      "data": null
                    }
                    """
            ),
            @ExampleObject(
                name = "UN_AUTHENTICATION_MEMBER_EXCEPTION",
                summary = "인증되지 않은 접근",
                value = """
                    {
                      "code": "UN_AUTHENTICATION_MEMBER_EXCEPTION",
                      "message": "인증되지 않은 사용자입니다.",
                      "data": null
                    }
                    """
            ),
            @ExampleObject(
                name = "AUTHENTICATION_NAME_NULL_ERROR",
                summary = "인증되지 않은 접근",
                value = """
                    {
                      "code": "AUTHENTICATION_NAME_NULL_ERROR",
                      "message": "Authentication name 이 NULL 입니다.",
                      "data": null
                    }
                    """
            ),
            @ExampleObject(
                name = "EXPIRED_TOKEN",
                summary = "만료된 토큰",
                value = """
                    {
                      "code": "EXPIRED_TOKEN",
                      "message": "만료된 토큰입니다.",
                      "data": null
                    }
                    """
            ),
            @ExampleObject(
                name = "INVALID_JWT_SIGNATURE",
                summary = "유효하지 않은 서명",
                value = """
                    {
                      "code": "INVALID_JWT_SIGNATURE",
                      "message": "유효하지 않은 서명입니다.",
                      "data": null
                    }
                    """
            ),
            @ExampleObject(
                name = "UNAUTHENTICATED",
                summary = "인증되지 않음",
                value = """
                    {
                      "code": "UNAUTHENTICATED",
                      "message": "로그인 후 이용 바랍니다.",
                      "data": null
                    }
                    """
            ),
            @ExampleObject(
                name = "INVALID_TOKEN_ERROR",
                summary = "유효하지 않은 토큰",
                value = """
                    {
                      "code": "INVALID_TOKEN_ERROR",
                      "message": "유효하지 않는 토큰입니다.",
                      "data": null
                    }
                    """
            )
        }
    )
)
public @interface ApiResponse401 {

}
