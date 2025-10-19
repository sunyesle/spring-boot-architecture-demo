package com.sunyesle.microservices.common.customer;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper {
    Customer selectById(String id);
}
