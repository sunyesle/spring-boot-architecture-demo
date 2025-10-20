package com.sunyesle.microservices.service_request.client.department;

import com.sunyesle.microservices.service_request.client.user.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;
import java.util.Set;

public interface DepartmentClient {
    @GetExchange("/api/departments/{id}")
    Department getDepartment(@PathVariable String id);

    @GetExchange("/api/departments")
    List<User> getDepartments(@RequestParam Set<String> ids);
}
