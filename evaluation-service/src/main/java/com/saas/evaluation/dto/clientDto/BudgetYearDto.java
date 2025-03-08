package com.saas.evaluation.dto.clientDto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetYearDto {
    private UUID id;
    private String budgetYear;
    private boolean isActive;
    private String description;
    private UUID tenantId;
}