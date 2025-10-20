package com.sunyesle.microservices.service_request.client.user;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;
import java.util.Set;

public interface UserClient {
    @GetExchange("/api/users/{id}")
    User getUser(@PathVariable String id);

    @GetExchange("/api/users")
    List<User> getUsers(@RequestParam Set<String> ids);
}
