package com.saas.evaluation.dto.response;

import com.saas.evaluation.enums.CriteriaStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriteriaResponse {
    private UUID id;
    private String name;
    private Double weight;
    private String status;
    private String description;
    private UUID categoryId;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private UUID tenantId;


}
