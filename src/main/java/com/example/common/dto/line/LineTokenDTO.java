package com.example.common.dto.line;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

public class LineTokenDTO {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Req{

        @JsonProperty(value = "grant_type")
        private String grantType;

        private String code;

        @JsonProperty(value = "redirect_uri")
        private String redirectUri;

        @JsonProperty(value = "client_id")
        private String clientId;

        @JsonProperty(value = "client_secret")
        private String clientSecret;
    }

    @Data
    public static class Res{

        @NotBlank(message = "login 성공 이후 응답 값에 scope 값이 없습니다.")
        private String scope;

        @NotBlank(message = "login 성공 이후 응답 값에 access_token이 없습니다.")
        @JsonProperty(value = "access_token")
        private String accessToken;

        @NotBlank(message = "login 성공 이후 응답 값에 token_type이 없습니다.")
        @JsonProperty(value = "token_type")
        private String tokenType;

        //@NotBlank(message = "login 성공 이후 응답 값에 expires_in이 없습니다.")
        @JsonProperty(value = "expires_in")
        private Integer expiresIn;

        @NotBlank(message = "login 성공 이후 응답 값에 refresh_token이 없습니다.")
        @JsonProperty(value = "refresh_token")
        private String refreshToken;
    }
}
