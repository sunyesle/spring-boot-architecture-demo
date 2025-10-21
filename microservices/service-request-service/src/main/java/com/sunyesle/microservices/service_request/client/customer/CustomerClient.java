package com.sunyesle.microservices.service_request.client.customer;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;
import java.util.Set;

public interface CustomerClient {
    @GetExchange("/api/customers/{id}")
    Customer getCustomer(@PathVariable String id);

    @GetExchange("/api/customers")
    List<Customer> getCustomers(@RequestParam Set<String> ids);
}
