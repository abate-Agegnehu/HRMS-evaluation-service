package com.saas.discipline.dto.eventDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceEvent {

    private UUID tenantId;
    private String tenantAbbreviatedName;
    private String createdBy;
    private String updatedBy;
}
