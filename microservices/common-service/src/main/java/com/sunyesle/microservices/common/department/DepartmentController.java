package com.sunyesle.microservices.common.department;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("/{id}")
    public ResponseEntity<Department> get(@PathVariable String id) {
        return ResponseEntity.ok(departmentService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<Department>> getDepartments(@RequestParam List<String> ids){
        return ResponseEntity.ok(departmentService.getDepartments(ids));
    }
}
