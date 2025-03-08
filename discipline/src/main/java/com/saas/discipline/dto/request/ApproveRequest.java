package com.saas.discipline.dto.request;

import com.saas.discipline.enums.DecisionStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApproveRequest {

    @NotNull(message = "Approve status cannot be null")
    private DecisionStatus decisionStatus;
    private String remark;
}
