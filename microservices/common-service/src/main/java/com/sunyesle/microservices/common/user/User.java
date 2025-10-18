package com.sunyesle.microservices.common.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String name;
    private String phoneNumber;
    private String email;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
