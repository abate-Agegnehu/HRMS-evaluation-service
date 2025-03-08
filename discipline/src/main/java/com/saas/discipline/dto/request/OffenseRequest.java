package com.saas.discipline.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OffenseRequest {

    @NotBlank(message = "Code cannot be blank")
    private String code;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotNull(message = "Phase Out Time cannot be null")
    private Double phaseOutTime;
    @NotNull(message = "Weight cannot be null")
    private Double weight;
    private String description;
}
