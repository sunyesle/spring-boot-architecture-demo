package com.sunyesle.microservices.service_request.client.user;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface UserClient {
    @GetExchange("/api/users/{id}")
    User getUser(@PathVariable String id);
}
