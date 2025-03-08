package com.saas.discipline.service;

import com.saas.discipline.dto.request.AppealRequest;
import com.saas.discipline.dto.response.AppealResponse;
import com.saas.discipline.mapper.AppealMapper;
import com.saas.discipline.model.Appeal;
import com.saas.discipline.repository.AppealRepository;
import com.saas.discipline.utility.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppealService {

    private final  AppealRepository appealRepository;
    private final AppealMapper appealMapper;
    private ValidationUtil validationUtil;

    public AppealResponse createAppeal(
            UUID tenantId,
            AppealRequest appealRequest){

        try {
        Appeal appeal= appealMapper.mapToEntity(
                tenantId,appealRequest);
        Appeal savedAppeal=appealRepository.save(appeal);

        return appealMapper.mapToDto(savedAppeal);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public List<?>getAllAppeal(UUID tenantId){

        List<Appeal> appeals=appealRepository
                                                .findByTenantId(tenantId);

        return appeals.stream()
                .map(appealMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public AppealResponse getAppealById(
            UUID tenantId,
            UUID id){

        Appeal appeal=validationUtil.getAppealById(tenantId,id);
        return appealMapper.mapToDto(appeal);
    }

    public AppealResponse updateAppeal(
            UUID tenantId,
            UUID id,
            AppealRequest appealRequest){

        try {
        Appeal appeal=validationUtil.getAppealById(tenantId,id);
        Appeal mappedAppeal=appealMapper.updateEntity(tenantId,
                                                 appeal,
                                                 appealRequest);
        Appeal savedAppeal=appealRepository.save(mappedAppeal);
        return  appealMapper.mapToDto(savedAppeal);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String deleteAppeal(UUID tenantId,UUID id){

        Appeal appeal=validationUtil.getAppealById(tenantId,id);
        appealRepository.delete(appeal);

        return " Appeal is Deleted successfully";
    }


}
