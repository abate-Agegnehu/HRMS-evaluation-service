package com.saas.evaluation.repository;

import com.saas.evaluation.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LevelRepository extends JpaRepository<Level, UUID> {
    List<Level> findByTenantId(UUID tenantId);
}