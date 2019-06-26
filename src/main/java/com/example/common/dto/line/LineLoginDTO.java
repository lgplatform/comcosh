package com.example.common.dto.line;

import lombok.Data;

import javax.validation.constraints.NotBlank;

public class LineLoginDTO {

    @Data
    public static class Res{
        @NotBlank(message = "login 이후 response 받는 code값은 null이 될 수 없습니다.")
        private String code;
        private String state;
    }
}
