package com.saas.discipline.mapper;

import com.saas.discipline.dto.request.OffenseRequest;
import com.saas.discipline.dto.response.OffenseResponse;
import com.saas.discipline.model.Offense;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class OffenseMapper {

    public Offense mapToEntity(UUID tenantId, OffenseRequest offenseRequest){

        Offense offense=new Offense();

        offense.setName(offenseRequest.getName());
        offense.setCode(offenseRequest.getCode());
        offense.setPhaseOutTime(offenseRequest.getPhaseOutTime());
        offense.setWeight(offenseRequest.getWeight());
        offense.setDescription(offenseRequest.getDescription());
        offense.setTenantId(tenantId);

        return offense;
    }
    public OffenseResponse mapToDto(Offense offense){

        OffenseResponse offenseResponse=new OffenseResponse();

        offenseResponse.setId(offense.getId());
        offenseResponse.setName(offense.getName());
        offenseResponse.setCode(offense.getCode());
        offenseResponse.setPhaseOutTime(offense.getPhaseOutTime());
        offenseResponse.setWeight(offense.getWeight());
        offenseResponse.setDescription(offense.getDescription());
        offenseResponse.setTenantId(offense.getTenantId());
        offenseResponse.setCreatedAt(offense.getCreatedAt());
        offenseResponse.setCreatedBy(offense.getCreatedBy());
        offenseResponse.setUpdatedAt(offense.getUpdatedAt());
        offenseResponse.setUpdatedBy(offense.getUpdatedBy());
        return  offenseResponse;
    }

    public  Offense updateEntity(
            Offense offense,
            OffenseRequest offenseRequest){

        if(offenseRequest.getCode()!=null){
            offense.setCode(offenseRequest.getCode());
        }
        if(offenseRequest.getName()!=null){
            offense.setName(offenseRequest.getName());
        }
        if(offenseRequest.getPhaseOutTime()!=null){
            offense.setPhaseOutTime(offenseRequest.getPhaseOutTime());
        }
        if(offenseRequest.getWeight()!=null){
            offense.setWeight(offenseRequest.getWeight());
        }
        if(offenseRequest.getDescription()!=null){
            offense.setDescription(offenseRequest.getDescription());
        }
        offense.setUpdatedAt(LocalDateTime.now());
        offense.setUpdatedBy(null);
        return offense;
    }
}
