package com.saas.discipline.repository;

import com.saas.discipline.model.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DisciplineRepository extends JpaRepository<Discipline, UUID> {
    List<Discipline> findByTenantId(UUID tenantId);
}
