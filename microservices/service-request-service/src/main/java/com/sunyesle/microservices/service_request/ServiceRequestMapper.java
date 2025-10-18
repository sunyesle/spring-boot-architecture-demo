package com.sunyesle.microservices.service_request;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ServiceRequestMapper {
    List<ServiceRequest> selectAll(@Param("limit") int limit, @Param("offset") int offset);
}
