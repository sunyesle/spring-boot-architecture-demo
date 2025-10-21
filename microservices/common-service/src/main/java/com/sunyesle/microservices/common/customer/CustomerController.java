package com.sunyesle.microservices.common.customer;

import com.sunyesle.microservices.common.department.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<Customer> get(@PathVariable String id) {
        return ResponseEntity.ok(customerService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers(@RequestParam List<String> ids){
        return ResponseEntity.ok(customerService.getCustomers(ids));
    }
}
