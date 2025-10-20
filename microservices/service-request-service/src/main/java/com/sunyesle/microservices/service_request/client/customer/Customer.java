package com.sunyesle.microservices.service_request.client.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private String id;
    private String name;
    private String birthday;
    private String gender;
    private String address;
    private String phoneNumber;
    private String type;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
