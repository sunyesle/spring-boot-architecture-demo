package com.sunyesle.microservices.service_request;

import com.sunyesle.microservices.service_request.client.code.Code;
import com.sunyesle.microservices.service_request.client.code.CodeClient;
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
    private final CodeClient codeClient;
    private final UserClient userClient;

    public List<ServiceRequest> getAll(int limit, int offset) {
        List<ServiceRequest> serviceRequests = serviceRequestMapper.selectAll(limit, offset);

        Set<String> userIds = new HashSet<>();
        for (ServiceRequest sr : serviceRequests) {
            userIds.add(sr.getCallAgentId());
            userIds.add(sr.getVocAssigneeId());
        }

        Map<String, Map<String, String>> codeTypes = getCodeNamesByTypes("SR_TYPE", "SR_STATUS");
        Map<String, String> userNames = getUserNames(userIds);

        for (ServiceRequest sr : serviceRequests) {
            sr.setTypeName(codeTypes.get("SR_TYPE").get(sr.getType()));
            sr.setStatusName(codeTypes.get("SR_STATUS").get(sr.getStatus()));
            sr.setCallAgentName(userNames.get(sr.getCallAgentId()));
            sr.setVocAssigneeName(userNames.get(sr.getVocAssigneeId()));
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

    private Map<String, String> getUserNames(Set<String> userIds) {
        return userClient.getUsers(userIds).stream()
                .collect(Collectors.toMap(
                        User::getId,
                        User::getName
                ));
    }
}
