package com.sunyesle.microservices.common.department;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    private String id;
    private String name;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
