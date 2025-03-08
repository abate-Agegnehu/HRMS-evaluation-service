package com.saas.discipline.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OffenseResponse extends BaseResponse{
    private UUID id;
    private String code;
    private String name;
    private Double phaseOutTime;
    private Double weight;
    private String description;

}
