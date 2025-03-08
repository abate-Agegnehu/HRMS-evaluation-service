package com.saas.evaluation.client;

import com.saas.evaluation.dto.clientDto.EmployeeDto;

import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "employee-service", url = "http://172.20.136.101:8180/api/employee")
@Component
public interface EmployeeServiceClient {

    @GetMapping("/employees/{tenant-id}/get")
    EmployeeDto getEmployeeByEmployeeId(@PathVariable("tenant-id") UUID tenantId,
                                        @RequestParam("employee-id") String employeeId);

    @GetMapping("/employees/{tenant-id}/get/{employee-id}")
    EmployeeDto getEmployeeById(@PathVariable("tenant-id") UUID tenantId,
                                @PathVariable("employee-id") UUID employeeId);

}
