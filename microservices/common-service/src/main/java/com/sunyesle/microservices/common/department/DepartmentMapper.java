package com.sunyesle.microservices.common.department;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DepartmentMapper {
    Department selectById(@Param("id") String id);
}
