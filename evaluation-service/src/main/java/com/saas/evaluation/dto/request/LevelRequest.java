package com.saas.evaluation.dto.request;

import com.saas.evaluation.enums.LevelStatus;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LevelRequest {

    @NotBlank(message = "Level cannot be blank")
    private String level;

    @NotNull
    @DecimalMin(value = "0.0", message = "Minimum point must be at least 0.0")
    @DecimalMax(value = "100.0", message = "Maximum point must be less than 100.0")
    private Double minPoint;


    @NotNull
    @DecimalMin(value = "0.0", message = "Minimum value must be at least 0.0")
    @DecimalMax(value = "100.0", message = "Maximum value must be less than 100.0")
    private Double maxValue;

    private String description;

    @NotNull(message = "Level status cannot be null")
    private LevelStatus status;
}
