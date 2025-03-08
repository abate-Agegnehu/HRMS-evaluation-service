package com.saas.evaluation.dto.response;

import com.saas.evaluation.enums.LevelStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LevelResponse {
    private UUID id;
    private String level;
    private Double minPoint;
    private Double maxValue;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private UUID tenantId;
}
