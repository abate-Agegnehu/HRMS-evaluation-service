package com.saas.discipline.repository;

import com.saas.discipline.model.Offense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OffenseRepository extends JpaRepository<Offense, UUID> {
    List<Offense> findByTenantId(UUID tenantId);
}
