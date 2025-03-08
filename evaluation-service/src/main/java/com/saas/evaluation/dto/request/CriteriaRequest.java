package com.saas.evaluation.dto.request;

import com.saas.evaluation.enums.CriteriaStatus;
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
public class CriteriaRequest {

    @NotBlank(message = "Criteria name cannot be blank")
    private String name;

    @NotNull
    @DecimalMin(value = "0.0", message = "Minimum weight must be at least 0.0")
    @DecimalMax(value = "100.0", message = "Maximum weight must be less than 100.0")
    private Double weight;

    @NotNull(message = "Category status cannot be null")
    private CriteriaStatus status;

    private String description;

    @NotNull(message = "Category id cannot be null")
    private UUID categoryId;
}
