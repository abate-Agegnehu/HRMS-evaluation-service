package com.saas.discipline.mapper;

import com.saas.discipline.dto.request.AppealRequest;
import com.saas.discipline.dto.response.AppealResponse;
import com.saas.discipline.enums.DecisionStatus;
import com.saas.discipline.model.Appeal;
import com.saas.discipline.model.Discipline;
import com.saas.discipline.utility.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class AppealMapper {

    private final ValidationUtil validationUtil;

    public Appeal mapToEntity(
            UUID tenantId,
            AppealRequest appealRequest){


        Discipline discipline=validationUtil.getDisciplineById(
                tenantId,
                appealRequest.getDisciplineId());

        Appeal appeal=new Appeal();

        if(discipline.getStatus().equals(DecisionStatus.ACCEPT)){
            appeal.setDiscipline(discipline);
            appeal.setRemark(appealRequest.getRemark());
            appeal.setTenantId(tenantId);
        }else {
            throw new IllegalArgumentException(
                    "You can appeal if your disciplinary status has already been accepted/approved." );
        }

        return appeal;
    }

    public AppealResponse mapToDto(
            Appeal appeal){

        AppealResponse appealResponse =new AppealResponse();

        appealResponse.setId(appeal.getId());
        appealResponse.setDisciplineId(appeal.getDiscipline().getId());
        appealResponse.setRemark(appeal.getRemark());
        appealResponse.setTenantId(appeal.getTenantId());
        appealResponse.setCreatedAt(appeal.getCreatedAt());
        appealResponse.setCreatedBy(null);
        appealResponse.setUpdatedAt(appeal.getUpdatedAt());
        appealResponse.setUpdatedBy(appeal.getUpdatedBy());

        return appealResponse;
    }

    public Appeal updateEntity(
            UUID tenantId,
            Appeal appeal,
            AppealRequest appealRequest){


        Discipline discipline=validationUtil.getDisciplineById(
                tenantId,
                appealRequest.getDisciplineId());

        if(discipline.getStatus().equals(DecisionStatus.ACCEPT)){

        if(tenantId!=null){
            appeal.setTenantId(tenantId);
        }
        if(appealRequest.getDisciplineId()!=null){
            appeal.setDiscipline(discipline);
        }
        if(appealRequest.getRemark()!=null){
            appeal.setRemark(appealRequest.getRemark());
        }
        }else {
            throw new IllegalArgumentException(
                    "You can appeal if your disciplinary status has already been accepted/approved." );
        }

        return  appeal;
    }

}
