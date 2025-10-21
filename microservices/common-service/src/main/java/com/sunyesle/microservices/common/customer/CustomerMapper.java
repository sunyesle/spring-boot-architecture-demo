package com.sunyesle.microservices.common.customer;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerMapper {
    Customer selectById(@Param("id") String id);

    List<Customer> selectByIds(@Param("ids") List<String> ids);
}
