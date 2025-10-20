package com.sunyesle.microservices.common.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    User selectById(@Param("id") String id);

    List<User> selectByIds(@Param("ids") List<String> ids);
}
