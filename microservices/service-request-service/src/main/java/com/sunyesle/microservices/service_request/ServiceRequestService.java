package com.sunyesle.microservices.service_request;

import com.sunyesle.microservices.service_request.client.code.CodeClient;
import com.sunyesle.microservices.service_request.client.user.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceRequestService {
    private final ServiceRequestMapper serviceRequestMapper;
    private final CodeClient codeClient;
    private final UserClient userClient;

    public List<ServiceRequest> getAll(int limit, int offset) {
        List<ServiceRequest> serviceRequests = serviceRequestMapper.selectAll(limit, offset);
        for (ServiceRequest sr : serviceRequests) {
            sr.setTypeName(getTypeName(sr.getType()));
            sr.setStatusName(getStatusName(sr.getStatus()));
            sr.setCallAgentName(getUserName(sr.getCallAgentId()));
            sr.setVocAssigneeName(getUserName(sr.getVocAssigneeId()));
        }
        return serviceRequests;
    }

    private String getTypeName(String type) {
        return codeClient.getValue("SR_TYPE", type).getValue();
    }

    private String getStatusName(String status) {
        return codeClient.getValue("SR_STATUS", status).getValue();
    }

    private String getUserName(String id) {
        return userClient.getUser(id).getName();
    }
}

