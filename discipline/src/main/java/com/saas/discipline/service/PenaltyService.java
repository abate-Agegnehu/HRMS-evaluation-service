package com.saas.discipline.service;

import com.saas.discipline.dto.request.PenaltyRequest;
import com.saas.discipline.dto.response.PenaltyResponse;
import com.saas.discipline.mapper.PenaltyMapper;
import com.saas.discipline.model.Penalty;
import com.saas.discipline.repository.PenaltyRepository;
import com.saas.discipline.utility.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PenaltyService {

    private final PenaltyRepository penaltyRepository;
    private final PenaltyMapper penaltyMapper;
    private final ValidationUtil validationUtil;

    public PenaltyResponse createPenalty(
            UUID tenantId,
            PenaltyRequest penaltyRequest){

        Penalty penalty=penaltyMapper.mapToEntity(tenantId,penaltyRequest);
        Penalty savedPenalty=penaltyRepository.save(penalty);

        return penaltyMapper.mapToDto(savedPenalty);
    }

    public List<?> getAllPenalty(UUID tenantId){

     List<Penalty>penalties=penaltyRepository.findByTenantId(tenantId);

     return penalties.stream()
                     .map(penaltyMapper::mapToDto)
                     .collect(Collectors.toList());
    }

    public PenaltyResponse getPenaltyById(UUID tenantId,UUID id){

        Penalty penalty=validationUtil.getPenaltyById(tenantId,id);
        return penaltyMapper.mapToDto(penalty);
    }

    public PenaltyResponse updatePenalty(
            UUID tenantId,
            UUID id,
            PenaltyRequest penaltyRequest){

        Penalty penalty=validationUtil.getPenaltyById(tenantId,id);
        Penalty mappedPenalty=penaltyMapper.updateEntity(penalty,penaltyRequest);
        Penalty savedPenalty=penaltyRepository.save(mappedPenalty);

        return penaltyMapper.mapToDto(savedPenalty);
    }

    public String deletePenalty(UUID tenantId,UUID id){

        Penalty penalty=validationUtil.getPenaltyById(tenantId,id);
        penaltyRepository.delete(penalty);

        return "Penalty Deleted successfully";
    }
}
