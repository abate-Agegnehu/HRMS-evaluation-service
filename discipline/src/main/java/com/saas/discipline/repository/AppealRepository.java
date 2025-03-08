package com.saas.discipline.repository;

import com.saas.discipline.model.Appeal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AppealRepository extends JpaRepository<Appeal, UUID> {
    List<Appeal> findByTenantId(UUID tenantId);
}
