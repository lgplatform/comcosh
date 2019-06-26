package com.example.common.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class CommonUtils {

    /**
     * Spring Valid어노테이션에 체크된 에러의 메시지를 가져옴
     *
     * @param result
     * @param msgSeperator 에러가 여러개일 때 에러메시지 구분자
     * @return
     */
    public static String getErrMsgValidAnnotation(BindingResult result, String msgSeperator) {

        StringBuilder message = new StringBuilder("");

        // Validation 오류 발생시
        if (result.hasErrors()) {

            List<FieldError> errors = result.getFieldErrors();
            int errSize = errors.size();
            for (int i = 0; i < errSize; i++) {
                message.append(errors.get(i).getDefaultMessage());
                if (i != errSize - 1) {
                    message.append(msgSeperator);
                }
            }
        }

        return message.toString();
    }
}
