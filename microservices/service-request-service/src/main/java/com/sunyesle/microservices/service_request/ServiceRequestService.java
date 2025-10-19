package com.sunyesle.microservices.service_request;

import com.sunyesle.microservices.service_request.client.code.CodeClient;
import com.sunyesle.microservices.service_request.client.customer.CustomerClient;
import com.sunyesle.microservices.service_request.client.department.DepartmentClient;
import com.sunyesle.microservices.service_request.client.user.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceRequestService {
    private final ServiceRequestMapper serviceRequestMapper;
    private final CustomerClient customerClient;
    private final CodeClient codeClient;
    private final UserClient userClient;
    private final DepartmentClient departmentClient;

    public List<ServiceRequest> getAll(int limit, int offset) {
        List<ServiceRequest> serviceRequests = serviceRequestMapper.selectAll(limit, offset);
        for (ServiceRequest sr : serviceRequests) {
            sr.setCustomerName(getCustomerName(sr.getCustomerId()));
            sr.setTypeName(getTypeName(sr.getType()));
            sr.setStatusName(getStatusName(sr.getStatus()));
            sr.setCallAgentName(getUserName(sr.getCallAgentId()));
            sr.setVocAssigneeName(getUserName(sr.getVocAssigneeId()));
            sr.setVocAssigneeDeptName(getDeptName(sr.getVocAssigneeDeptId()));
        }
        return serviceRequests;
    }

    private String getCustomerName(String customerId) {
        return customerClient.getCustomer(customerId).getName();
    }

    private String getTypeName(String type) {
        return codeClient.getValue("SR_TYPE", type).getValue();
    }

    private String getStatusName(String status) {
        return codeClient.getValue("SR_STATUS", status).getValue();
    }

    private String getUserName(String userId) {
        return userClient.getUser(userId).getName();
    }

    private String getDeptName(String deptId) {
        return departmentClient.getDepartment(deptId).getName();
    }
}

