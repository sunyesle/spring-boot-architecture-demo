package com.sunyesle.microservices.service_request;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceRequestBatchService {
    private final ServiceRequestMapper serviceRequestMapper;

    public List<ServiceRequest> getAll(int limit, int offset) {
        return serviceRequestMapper.selectAll(limit, offset);
    }
}
