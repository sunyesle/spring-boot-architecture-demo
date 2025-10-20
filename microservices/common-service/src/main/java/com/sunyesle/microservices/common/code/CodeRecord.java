package com.sunyesle.microservices.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CodeRecord {
    private String codeType;
    private String code;
    private String value;
}
