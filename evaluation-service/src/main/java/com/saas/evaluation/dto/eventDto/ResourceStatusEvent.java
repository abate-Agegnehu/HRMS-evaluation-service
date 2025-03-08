package com.saas.evaluation.dto.eventDto;

import com.saas.evaluation.enums.ResourceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceStatusEvent {

    private UUID tenantId;
    private ResourceStatus status;
}
