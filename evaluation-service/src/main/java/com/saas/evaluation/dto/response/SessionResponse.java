package com.saas.evaluation.dto.response;

import com.saas.evaluation.dto.clientDto.BudgetYearDto;
import com.saas.evaluation.enums.SessionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionResponse {
    private UUID id;
    private UUID  budgetYearId;
    private String term;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String remark;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private UUID tenantId;
}
