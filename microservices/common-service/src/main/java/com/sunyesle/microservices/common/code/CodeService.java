package com.sunyesle.microservices.common.code;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CodeService {
    private final CodeMapper codeMapper;

    public CodeValue getValue(String codeType, String code) {
        return new CodeValue(codeMapper.selectValue(codeType, code));
    }

    public List<Code> getCodes(List<String> codeTypes) {
        List<CodeRecord> codeRecords = codeMapper.selectByCodeTypes(codeTypes);

        return codeRecords.stream()
                .collect(Collectors.groupingBy(CodeRecord::getCodeType))
                .entrySet().stream()
                .map(e -> new Code(
                                e.getKey(),
                                e.getValue().stream()
                                        .map(c -> new Code.CodeItem(c.getCode(), c.getValue()))
                                        .toList()
                        )
                ).toList();
    }
}
