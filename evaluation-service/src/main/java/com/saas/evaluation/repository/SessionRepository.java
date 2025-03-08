package com.saas.evaluation.repository;

import com.saas.evaluation.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {
    List<Session> findByTenantId(UUID tenantId);
}