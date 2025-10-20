package com.sunyesle.microservices.service_request.client.code;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface CodeClient {
    @GetExchange("/api/codes/value")
    CodeValue getValue(@RequestParam String codeType, @RequestParam String code);

    @GetExchange("/api/codes")
    List<Code> getCodes(@RequestParam List<String> codeTypes);
}
