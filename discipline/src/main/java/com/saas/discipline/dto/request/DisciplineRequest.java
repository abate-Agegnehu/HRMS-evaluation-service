package com.saas.discipline.dto.request;

import com.saas.discipline.enums.DecisionStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisciplineRequest {
    @NotNull(message = "Employee Id can not be null")
    private UUID employeeId;
    @NotNull(message = "Offender Id can not be null")
    private UUID offenderId;
    @NotNull(message = "Offense Id can not be null")
    private UUID offenseId;
    @NotNull(message = "Repetition can not be null")
    private Integer repetition;
    private DecisionStatus status;
    private LocalDateTime offenseDate;
    private String description;
    private String remark;

}
