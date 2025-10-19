package com.sunyesle.microservices.common.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerMapper customerMapper;

    public Customer get(String id) {
        return customerMapper.selectById(id);
    }
}
