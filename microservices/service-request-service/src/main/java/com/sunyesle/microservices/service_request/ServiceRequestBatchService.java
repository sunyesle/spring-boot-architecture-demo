package com.sunyesle.microservices.service_request;

import com.sunyesle.microservices.service_request.client.code.Code;
import com.sunyesle.microservices.service_request.client.code.CodeClient;
import com.sunyesle.microservices.service_request.client.customer.Customer;
import com.sunyesle.microservices.service_request.client.customer.CustomerClient;
import com.sunyesle.microservices.service_request.client.department.DepartmentClient;
import com.sunyesle.microservices.service_request.client.user.User;
import com.sunyesle.microservices.service_request.client.user.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceRequestBatchService {
    private final ServiceRequestMapper serviceRequestMapper;
    private final CustomerClient customerClient;
    private final CodeClient codeClient;
    private final UserClient userClient;
    private final DepartmentClient departmentClient;

    public List<ServiceRequest> getAll(int limit, int offset) {
        List<ServiceRequest> serviceRequests = serviceRequestMapper.selectAll(limit, offset);

        Set<String> customerIds = new HashSet<>();
        Set<String> userIds = new HashSet<>();
        Set<String> departmentIds = new HashSet<>();
        for (ServiceRequest sr : serviceRequests) {
            customerIds.add(sr.getCustomerId());
            userIds.add(sr.getCallAgentId());
            userIds.add(sr.getVocAssigneeId());
            departmentIds.add(sr.getVocAssigneeDeptId());
        }

        Map<String, Map<String, String>> codeTypes = getCodeNamesByTypes("SR_TYPE", "SR_STATUS");
        Map<String, String> customerNames = getCustomerNames(customerIds);
        Map<String, String> userNames = getUserNames(userIds);
        Map<String, String> departmentNames = getDepartmentNames(departmentIds);

        for (ServiceRequest sr : serviceRequests) {
            sr.setCustomerName(customerNames.get(sr.getCustomerId()));
            sr.setTypeName(codeTypes.get("SR_TYPE").get(sr.getType()));
            sr.setStatusName(codeTypes.get("SR_STATUS").get(sr.getStatus()));
            sr.setCallAgentName(userNames.get(sr.getCallAgentId()));
            sr.setVocAssigneeName(userNames.get(sr.getVocAssigneeId()));
            sr.setVocAssigneeDeptName(departmentNames.get(sr.getVocAssigneeDeptId()));
        }
        return serviceRequests;
    }

    private Map<String, Map<String, String>> getCodeNamesByTypes(String... codeTypes) {
        List<String> codeTypeList = Arrays.asList(codeTypes);

        return codeClient.getCodes(codeTypeList).stream()
                .collect(Collectors.toMap(
                        Code::getCodeType,
                        c -> c.getCodes().stream()
                                .collect(Collectors.toMap(
                                        Code.CodeItem::getCode,
                                        Code.CodeItem::getValue
                                ))
                ));
    }

    private Map<String, String> getCustomerNames(Set<String> customerIds) {
        return customerClient.getCustomers(customerIds).stream()
                .collect(Collectors.toMap(
                        Customer::getId,
                        Customer::getName
                ));
    }

    private Map<String, String> getUserNames(Set<String> userIds) {
        return userClient.getUsers(userIds).stream()
                .collect(Collectors.toMap(
                        User::getId,
                        User::getName
                ));
    }

    private Map<String, String> getDepartmentNames(Set<String> departmentIds) {
        return departmentClient.getDepartments(departmentIds).stream()
                .collect(Collectors.toMap(
                        User::getId,
                        User::getName
                ));
    }
}
