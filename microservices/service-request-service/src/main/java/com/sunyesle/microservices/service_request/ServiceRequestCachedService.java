package com.sunyesle.microservices.service_request;

import com.sunyesle.microservices.service_request.client.code.CodeClient;
import com.sunyesle.microservices.service_request.client.customer.Customer;
import com.sunyesle.microservices.service_request.client.customer.CustomerClient;
import com.sunyesle.microservices.service_request.client.department.Department;
import com.sunyesle.microservices.service_request.client.department.DepartmentClient;
import com.sunyesle.microservices.service_request.client.user.User;
import com.sunyesle.microservices.service_request.client.user.UserClient;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceRequestCachedService extends ServiceRequestBatchService {
    public ServiceRequestCachedService(ServiceRequestMapper serviceRequestMapper, CustomerClient customerClient, CodeClient codeClient, UserClient userClient, DepartmentClient departmentClient, CacheProxy<Customer> customerCache, CacheProxy<User> userCache, CacheProxy<Department> departmentCache) {
        super(serviceRequestMapper, customerClient, codeClient, userClient, departmentClient);
        this.customerCache = customerCache;
        this.userCache = userCache;
        this.departmentCache = departmentCache;
    }

    private final CacheProxy<Customer> customerCache;
    private final CacheProxy<User> userCache;
    private final CacheProxy<Department> departmentCache;

    @Override
    protected Map<String, String> getCustomerNames(Set<String> customerIds) {
        Map<String, String> customerNames = new HashMap<>();
        Iterator<String> iterator = customerIds.iterator();
        while (iterator.hasNext()) {
            Customer customer = customerCache.get(iterator.next());
            if (customer != null) {
                customerNames.put(customer.getId(), customer.getName());
                iterator.remove(); // REST API 요청 목록에서 삭제
            }
        }
        if (!customerIds.isEmpty()) {
            List<Customer> customers = customerClient.getCustomers(customerIds);
            for (Customer customer : customers) {
                customerNames.put(customer.getId(), customer.getName());
                customerCache.put(customer.getId(), customer);
            }
        }
        return customerNames;
    }

    @Override
    protected Map<String, String> getUserNames(Set<String> userIds) {
        Map<String, String> userNames = new HashMap<>();
        Iterator<String> iterator = userIds.iterator();
        while (iterator.hasNext()) {
            User user = userCache.get(iterator.next());
            if (user != null) {
                userNames.put(user.getId(), user.getName());
                iterator.remove();
            }
        }
        if (!userIds.isEmpty()) {
            List<User> users = userClient.getUsers(userIds);
            for (User user : users) {
                userNames.put(user.getId(), user.getName());
                userCache.put(user.getId(), user);
            }
        }
        return userNames;
    }

    @Override
    protected Map<String, String> getDepartmentNames(Set<String> departmentIds) {
        Map<String, String> departmentNames = new HashMap<>();
        Iterator<String> iterator = departmentIds.iterator();
        while (iterator.hasNext()) {
            Department department = departmentCache.get(iterator.next());
            if (department != null) {
                departmentNames.put(department.getId(), department.getName());
                iterator.remove();
            }
        }
        if (!departmentIds.isEmpty()) {
            List<Department> departments = departmentClient.getDepartments(departmentIds);
            for (Department department : departments) {
                departmentNames.put(department.getId(), department.getName());
                departmentCache.put(department.getId(), department);
            }
        }
        return departmentNames;
    }
}
