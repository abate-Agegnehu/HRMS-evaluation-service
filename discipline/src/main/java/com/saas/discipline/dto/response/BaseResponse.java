package com.saas.discipline.dto.response;

import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class BaseResponse {
    private UUID id;
    private UUID tenantId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}
