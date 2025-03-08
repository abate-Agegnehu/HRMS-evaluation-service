package com.saas.evaluation.dto.response;

import com.saas.evaluation.dto.clientDto.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultResponse {
    private UUID id;
    private UUID sessionId;
    private UUID employeeId;
    private UUID categoryId;
    private UUID criteriaId;
    private Double result;
    private  String reason;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private UUID tenantId;
}
