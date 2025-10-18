package com.sunyesle.microservices.common.code;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CodeService {
    private final CodeMapper codeMapper;

    public CodeValue getValue(String codeType, String code) {
        return new CodeValue(codeMapper.selectValue(codeType, code));
    }
}
