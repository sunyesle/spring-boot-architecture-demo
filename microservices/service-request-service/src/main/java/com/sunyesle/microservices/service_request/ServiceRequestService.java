package com.sunyesle.microservices.service_request;

import com.sunyesle.microservices.service_request.client.code.CodeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceRequestService {
    private final ServiceRequestMapper serviceRequestMapper;
    private final CodeClient codeClient;

    public List<ServiceRequest> getAll(int limit, int offset) {
        List<ServiceRequest> serviceRequests = serviceRequestMapper.selectAll(limit, offset);
        for (ServiceRequest sr : serviceRequests) {
            sr.setTypeName(getTypeName(sr.getType()));
            sr.setStatusName(getStatusName(sr.getStatus()));
        }
        return serviceRequests;
    }

    private String getTypeName(String type) {
        return codeClient.getValue("SR_TYPE", type).getValue();
    }

    private String getStatusName(String status) {
        return codeClient.getValue("SR_STATUS", status).getValue();
    }
}

