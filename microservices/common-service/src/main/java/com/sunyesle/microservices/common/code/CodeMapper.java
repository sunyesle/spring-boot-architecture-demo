package com.sunyesle.microservices.common.code;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CodeMapper {
    String selectValue(@Param("codeType") String codeType, @Param("code") String code);
}
