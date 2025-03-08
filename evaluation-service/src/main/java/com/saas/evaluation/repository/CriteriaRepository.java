package com.saas.evaluation.repository;

import com.saas.evaluation.model.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CriteriaRepository extends JpaRepository<Criteria, UUID> {
    List<Criteria> findByTenantId(UUID tenantId);
}