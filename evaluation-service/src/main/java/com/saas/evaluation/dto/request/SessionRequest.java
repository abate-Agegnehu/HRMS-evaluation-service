package com.saas.evaluation.dto.request;

import com.saas.evaluation.enums.SessionStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionRequest {

    @NotNull(message = "Budget year  id cannot be null")
    private UUID budgetYearId;

    @NotNull(message = "Session  status cannot be null")
    private SessionStatus term;

    @NotNull(message = "Start date cannot be null")
    private LocalDateTime startDate;

    @NotNull(message = "End date cannot be null")
    private LocalDateTime endDate;

    private String remark;
}
