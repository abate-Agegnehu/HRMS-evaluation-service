package com.saas.discipline.dto.response;


import com.saas.discipline.enums.DecisionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisciplineResponse extends BaseResponse{

    private UUID employeeId;
    private UUID offenderId;
    private UUID offenseId;
    private Integer repetition;
    private LocalDateTime offenseDate;
    private String description;
    private String remark;
    private DecisionStatus status;

}
