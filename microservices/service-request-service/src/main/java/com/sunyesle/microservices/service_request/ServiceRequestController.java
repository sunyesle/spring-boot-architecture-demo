package com.sunyesle.microservices.service_request;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/service-requests")
@RequiredArgsConstructor
public class ServiceRequestController {
    private final ServiceRequestService serviceRequestService;
    private final ServiceRequestBatchService serviceRequestBatchService;

    @GetMapping
    public ResponseEntity<List<ServiceRequest>> getAll(@RequestParam(defaultValue = "30") int limit, @RequestParam(defaultValue = "0") int offset) {
        return ResponseEntity.ok(serviceRequestService.getAll(limit, offset));
    }

    @GetMapping("/batch")
    public ResponseEntity<List<ServiceRequest>> getAllWithBatch(@RequestParam(defaultValue = "30") int limit, @RequestParam(defaultValue = "0") int offset) {
        return ResponseEntity.ok(serviceRequestBatchService.getAll(limit, offset));
    }
}
