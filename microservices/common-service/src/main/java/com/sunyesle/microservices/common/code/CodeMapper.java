package com.sunyesle.microservices.common.code;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CodeMapper {
    String selectValue(@Param("codeType") String codeType, @Param("code") String code);

    List<CodeRecord> selectByCodeTypes(@Param("codeTypes") List<String> codeTypes);
}
