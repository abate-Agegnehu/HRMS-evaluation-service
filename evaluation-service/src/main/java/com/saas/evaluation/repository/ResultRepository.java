package com.saas.evaluation.repository;

import com.saas.evaluation.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ResultRepository extends JpaRepository<Result, UUID> {
    List<Result> findByTenantId(UUID tenantId);
}