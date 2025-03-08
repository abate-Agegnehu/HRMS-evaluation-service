package com.saas.discipline.mapper;

import com.saas.discipline.dto.request.PenaltyRequest;
import com.saas.discipline.dto.response.PenaltyResponse;
import com.saas.discipline.model.Penalty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PenaltyMapper {

    public Penalty mapToEntity(UUID tenantId, PenaltyRequest penaltyRequest){

        Penalty penalty=new Penalty();

        penalty.setName(penaltyRequest.getName());
        penalty.setCode(penaltyRequest.getCode());
        penalty.setClassification(penaltyRequest.getClassification());
        penalty.setActionTaker(penaltyRequest.getActionTaker());
        penalty.setDescription(penaltyRequest.getDescription());
        penalty.setTenantId(tenantId);
        return penalty;
    }

    public PenaltyResponse mapToDto(Penalty penalty){
        PenaltyResponse penaltyResponse=new PenaltyResponse();

        penaltyResponse.setId(penalty.getId());
        penaltyResponse.setName(penalty.getName());
        penaltyResponse.setCode(penalty.getCode());
        penaltyResponse.setClassification(penalty.getClassification());
        penaltyResponse.setActionTaker(penalty.getActionTaker());
        penaltyResponse.setDescription(penalty.getDescription());
        penaltyResponse.setTenantId(penalty.getTenantId());
        penaltyResponse.setCreatedAt(LocalDateTime.now());
        penaltyResponse.setCreatedBy(penalty.getCreatedBy());
        penaltyResponse.setUpdatedAt(penalty.getUpdatedAt());
        penaltyResponse.setUpdatedBy(penalty.getUpdatedBy());
        return penaltyResponse;
    }

    public  Penalty updateEntity(
            Penalty penalty,
            PenaltyRequest penaltyRequest){

        if(penaltyRequest.getCode()!=null){
            penalty.setCode(penaltyRequest.getCode());
        }
        if(penaltyRequest.getName()!=null){
            penalty.setName(penaltyRequest.getName());
        }
        if(penaltyRequest.getClassification()!=null){
            penalty.setClassification(penaltyRequest.getClassification());
        }
        if(penaltyRequest.getActionTaker()!=null){
            penalty.setActionTaker(penaltyRequest.getActionTaker());
        }
        if(penaltyRequest.getDescription()!=null){
            penalty.setDescription(penaltyRequest.getDescription());
        }

        return penalty;
    }
}
