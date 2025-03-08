package com.saas.evaluation.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultRequest {

    @NotNull(message = "Session  id cannot be null")
    private UUID sessionId;

    @NotNull(message = "Employee  id cannot be null")
    private UUID employeeId;

    @NotNull(message = "Category  id cannot be null")
    private UUID categoryId;

    @NotNull(message = "Criteria  id cannot be null")
    private UUID criteriaId;

    @NotNull
    @DecimalMin(value = "0.0", message = "Minimum result must be at least 0.0")
    @DecimalMax(value = "100.0", message = "Maximum result must be less than 100.0")
    private Double result;

    private  String reason;
}
