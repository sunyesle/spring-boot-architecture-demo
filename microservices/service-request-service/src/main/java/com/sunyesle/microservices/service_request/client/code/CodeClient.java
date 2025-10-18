package com.sunyesle.microservices.service_request.client.code;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface CodeClient {
    @GetExchange("/api/codes/value")
    CodeValue getValue(@RequestParam String codeType, @RequestParam String code);
}
