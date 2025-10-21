package com.sunyesle.microservices.common.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerMapper customerMapper;

    public Customer get(String id) {
        return customerMapper.selectById(id);
    }

    public List<Customer> getCustomers(List<String> ids) {
        return customerMapper.selectByIds(ids);
    }
}
