package com.sunyesle.microservices.service_request.client.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Code {
    private String codeType;
    private List<CodeItem> codes;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CodeItem {
        private String code;
        private String value;
    }
}
