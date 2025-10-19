package com.sunyesle.microservices.common.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String createdAt;
    private String updatedAt;
}
