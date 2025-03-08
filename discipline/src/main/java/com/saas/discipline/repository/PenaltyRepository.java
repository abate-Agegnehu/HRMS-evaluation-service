package com.saas.discipline.repository;

import com.saas.discipline.model.Penalty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PenaltyRepository extends JpaRepository<Penalty, UUID> {
    List<Penalty> findByTenantId(UUID tenantId);
}
