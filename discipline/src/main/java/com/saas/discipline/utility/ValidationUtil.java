package com.saas.discipline.utility;

import com.saas.discipline.exception.ResourceNotFoundException;
import com.saas.discipline.model.*;
import com.saas.discipline.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ValidationUtil {

   private final AppealRepository disciplineAppealRepository;
   private final DisciplineRepository disciplineRequestRepository;
   private final OffenseRepository offenseRepository;
   private final PenaltyRepository penaltyRepository;

    public Appeal getAppealById(UUID tenantId, UUID id){
        return disciplineAppealRepository.findById(id).
                filter(c -> c.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException(
                        " Appeal not found with id " + id));
    }

    public Discipline getDisciplineById(UUID tenantId, UUID id){
        return disciplineRequestRepository.findById(id).
                filter(c -> c.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "  Discipline  not found with id " + id));
    }
    public Offense getOffenseById(UUID tenantId, UUID id){
        return offenseRepository.findById(id).
                filter(c -> c.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Offense not found with id " + id));
    }
    public Penalty getPenaltyById(UUID tenantId, UUID id){
        return penaltyRepository.findById(id).
                filter(c -> c.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Penalty not found with id " + id));
    }

}
