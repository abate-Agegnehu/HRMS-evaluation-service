package com.saas.evaluation.model;

import com.saas.evaluation.utility.SecurityUtil;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BaseEntityListener {

    private final SecurityUtil securityUtil;

    @PrePersist
    public void setCreatedByAndUpdatedBy(Base base) {
        String name = securityUtil.getName();
        base.setCreatedBy(name != null ? name : "unknown");
        base.setUpdatedBy(null); // Clear updatedBy when a new entity is created
    }

    @PreUpdate
    public void setUpdatedBy(Base base) {
        String name = securityUtil.getName();
        base.setUpdatedBy(name != null ? name : "unknown");

    }
}
