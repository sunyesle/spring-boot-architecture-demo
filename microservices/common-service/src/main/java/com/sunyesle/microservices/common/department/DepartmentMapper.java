package com.sunyesle.microservices.common.department;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    Department selectById(@Param("id") String id);

    List<Department> selectByIds(@Param("ids") List<String> ids);
}
