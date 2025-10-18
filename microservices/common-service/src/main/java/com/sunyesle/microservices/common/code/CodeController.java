package com.sunyesle.microservices.common.code;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/codes")
@RequiredArgsConstructor
public class CodeController {
    private final CodeService codeService;

    @GetMapping("/value")
    public ResponseEntity<CodeValue> getValue(@RequestParam String codeType, @RequestParam String code) {
        return ResponseEntity.ok(codeService.getValue(codeType, code));
    }
}
