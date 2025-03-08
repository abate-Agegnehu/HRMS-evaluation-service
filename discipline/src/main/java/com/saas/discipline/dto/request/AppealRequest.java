package com.saas.discipline.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppealRequest {

    @NotNull(message = "Discipline Id cannot be null")
    private UUID disciplineId;
    @NotNull(message = "Discipline Decision cannot be null")
    private String remark;

}
