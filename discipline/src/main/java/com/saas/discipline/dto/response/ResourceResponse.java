package com.saas.discipline.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceResponse extends BaseResponse {

    private UUID id;
    private String resourceName;
    private Set<String> requiredRoles;
    private String status;
    private String description;
    private UUID tenantId;
}
