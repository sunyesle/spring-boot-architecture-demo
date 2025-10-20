package com.sunyesle.microservices.common.department;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentMapper departmentMapper;

    public Department get(String id) {
        return departmentMapper.selectById(id);
    }

    public List<Department> getDepartments(List<String> ids) {
        return departmentMapper.selectByIds(ids);
    }
}
