package com.saas.evaluation.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {

    @NotBlank(message = "Category name cannot be blank")
    private String name;

    @NotNull
    @DecimalMin(value = "0.0", message = "Minimum weight must be at least 0.0")
    @DecimalMax(value = "100.0", message = "Maximum weight must be less than 100.0")
    private Double weight;

    private String description;
}
