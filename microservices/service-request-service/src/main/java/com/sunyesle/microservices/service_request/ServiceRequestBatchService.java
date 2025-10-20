package com.sunyesle.microservices.service_request;

import com.sunyesle.microservices.service_request.client.code.Code;
import com.sunyesle.microservices.service_request.client.code.CodeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceRequestBatchService {
    private final ServiceRequestMapper serviceRequestMapper;
    private final CodeClient codeClient;

    public List<ServiceRequest> getAll(int limit, int offset) {
        List<ServiceRequest> serviceRequests = serviceRequestMapper.selectAll(limit, offset);

        Map<String, Map<String, String>> codeTypes = getCodeNamesByTypes("SR_TYPE", "SR_STATUS");

        for (ServiceRequest sr : serviceRequests) {
            sr.setTypeName(codeTypes.get("SR_TYPE").get(sr.getType()));
            sr.setStatusName(codeTypes.get("SR_STATUS").get(sr.getStatus()));
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
}
