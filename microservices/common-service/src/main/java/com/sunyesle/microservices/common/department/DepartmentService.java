package com.sunyesle.microservices.common.department;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentMapper departmentMapper;

    public Department get(String id) {
        return departmentMapper.selectById(id);
    }
}
