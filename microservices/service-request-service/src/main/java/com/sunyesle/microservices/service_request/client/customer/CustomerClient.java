package com.sunyesle.microservices.service_request.client.customer;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface CustomerClient {
    @GetExchange("/api/customers/{id}")
    Customer getCustomer(@PathVariable String id);
}
