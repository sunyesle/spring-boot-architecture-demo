package com.sunyesle.microservices.service_request.client.department;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface DepartmentClient {
    @GetExchange("/api/departments/{id}")
    Department getDepartment(@PathVariable String id);
}
