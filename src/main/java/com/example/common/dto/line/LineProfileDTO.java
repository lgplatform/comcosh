package com.example.common.dto.line;

import lombok.Data;

import javax.validation.constraints.NotBlank;

public class LineProfileDTO {

    @Data
    public static class Res{

        @NotBlank(message = "프로필 요청 API에 response 값에 userId는 null이 될 수 없습니다.")
        private String userId;

        @NotBlank(message = "프로필 요청 API에 response 값에 displayName는 null이 될 수 없습니다.")
        private String displayName;

        private String pictureUrl;
        private String statusMessage;
    }
}
