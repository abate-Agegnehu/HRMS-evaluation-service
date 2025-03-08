package com.saas.discipline.dto.request;

import com.saas.discipline.enums.PenaltyActionTaker;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PenaltyRequest {

    @NotBlank(message = "Code can not be blank")
    private String code;
    @NotBlank(message = "Name can not be blank")
    private String name;
    @NotBlank(message = "Classification can not be blank")
    private String classification;
    @NotNull(message = "Action Tacker can not be null")
    private PenaltyActionTaker actionTaker;
    private String description;
}
