package com.sunyesle.microservices.service_request;

import com.sunyesle.microservices.service_request.client.customer.Customer;
import com.sunyesle.microservices.service_request.client.department.Department;
import com.sunyesle.microservices.service_request.client.user.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {
    @Bean
    public CacheProxy<Customer> customerCache() {
        return new CacheProxy<>();
    }

    @Bean
    public CacheProxy<User> userCache() {
        return new CacheProxy<>();
    }

    @Bean
    public CacheProxy<Department> departmentCache() {
        return new CacheProxy<>();
    }
}
