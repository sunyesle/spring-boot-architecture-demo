package com.sunyesle.monolith.service_request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequest {
    private String id;
    private String title;
    private String customerId;
    private String customerName;
    private String type;
    private String typeName;
    private String detail;
    private String status;
    private String statusName;
    private String callAgentId;
    private String callAgentName;
    private String vocAssigneeId;
    private String vocAssigneeName;
    private String vocAssigneeDeptId;
    private String vocAssigneeDeptName;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
