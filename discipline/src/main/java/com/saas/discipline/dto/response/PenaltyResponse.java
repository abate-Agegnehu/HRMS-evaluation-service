package com.saas.discipline.dto.response;


import com.saas.discipline.enums.PenaltyActionTaker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PenaltyResponse  extends  BaseResponse{
    private UUID id;
    private PenaltyActionTaker actionTaker;
    private String classification;
    private String name;
    private String code;
    private String description;

}
