package com.saas.evaluation.client;

import com.saas.evaluation.dto.clientDto.BudgetYearDto;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "leave-service", url = "http://172.20.136.101:8184/api/leave")
@Component
public interface LeaveServiceClient {

    @GetMapping("/budget-years/{tenantId}/get/{id}")
    BudgetYearDto getBudgetYearById(@PathVariable("tenantId") UUID tenantId,
                                    @PathVariable UUID id);
}