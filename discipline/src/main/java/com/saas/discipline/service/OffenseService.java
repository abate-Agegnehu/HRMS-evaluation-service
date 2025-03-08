package com.saas.discipline.service;

import com.saas.discipline.dto.request.OffenseRequest;
import com.saas.discipline.dto.response.OffenseResponse;
import com.saas.discipline.mapper.OffenseMapper;
import com.saas.discipline.model.Offense;
import com.saas.discipline.repository.OffenseRepository;
import com.saas.discipline.utility.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OffenseService {

    private final OffenseRepository offenseRepository;
    private final OffenseMapper offenseMapper;
    private final ValidationUtil validationUtil;

    public OffenseResponse createOffense(
            UUID tenantId,
            OffenseRequest offenseRequest){

        Offense offense=offenseMapper.mapToEntity(tenantId,offenseRequest);
        Offense savedOffense=offenseRepository.save(offense);

        return offenseMapper.mapToDto(savedOffense);
    }

    public List<?> getAllOffense(UUID tenantId){

        List<Offense>offenses=offenseRepository.findByTenantId(tenantId);

        return offenses.stream()
                        .map(offenseMapper::mapToDto)
                        .collect(Collectors.toList());
    }
    public OffenseResponse getOffenseById(UUID tenantId,UUID id){

        Offense offense=validationUtil.getOffenseById(tenantId,id);
        return offenseMapper.mapToDto(offense);
    }
    public OffenseResponse updateOffense(
            UUID tenantId,
            UUID id,
            OffenseRequest offenseRequest){

        Offense offense=validationUtil.getOffenseById(tenantId,id);
        Offense mappedOffense=offenseMapper.updateEntity(offense,offenseRequest);
        Offense savedOffense=offenseRepository.save(mappedOffense);

        return offenseMapper.mapToDto(savedOffense);
    }

    public String deleteOffense(UUID tenantId,UUID id){

        Offense offense=validationUtil.getOffenseById(tenantId,id);
        offenseRepository.delete(offense);

        return "Offense Deleted successfully";
    }
}
