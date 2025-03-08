package com.saas.discipline.mapper;

import com.saas.discipline.client.EmployeeServiceClient;
import com.saas.discipline.dto.clientDto.EmployeeDto;
import com.saas.discipline.dto.request.DisciplineRequest;
import com.saas.discipline.enums.DecisionStatus;
import com.saas.discipline.model.Discipline;
import com.saas.discipline.dto.response.DisciplineResponse;
import com.saas.discipline.model.Offense;
import com.saas.discipline.utility.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DisciplineMapper {

    private final ValidationUtil validationUtil;
    private final EmployeeServiceClient employeeServiceClient;

    public Discipline mapToEntity(
            UUID tenantId,
            DisciplineRequest disciplineRequest){

        EmployeeDto employee = employeeServiceClient.getEmployeeById(tenantId, disciplineRequest.getEmployeeId());
        EmployeeDto offender = employeeServiceClient.getEmployeeById(tenantId, disciplineRequest.getOffenderId());
        Offense offense = validationUtil.getOffenseById(tenantId, disciplineRequest.getOffenseId());
        Discipline discipline = new Discipline();

        discipline.setEmployeeId(employee.getId());
        discipline.setOffenderId(offender.getId());
        discipline.setOffense(offense);
        discipline.setOffenseDate(disciplineRequest.getOffenseDate());
        discipline.setDescription(disciplineRequest.getDescription());
        discipline.setStatus(DecisionStatus.PENDING);
        discipline.setRemark(disciplineRequest.getRemark());
        discipline.setTenantId(tenantId);
        return discipline;
    }

    public DisciplineResponse mapToDto(Discipline discipline){
        DisciplineResponse disciplineResponse = new DisciplineResponse();

        disciplineResponse.setId(discipline.getId());
        disciplineResponse.setEmployeeId(discipline.getEmployeeId());
        disciplineResponse.setOffenderId(discipline.getOffenderId());
        disciplineResponse.setOffenseId(discipline.getOffense().getId());
        disciplineResponse.setOffenseDate(discipline.getOffenseDate());
        disciplineResponse.setDescription(discipline.getDescription());
        disciplineResponse.setStatus(discipline.getStatus());
        disciplineResponse.setRemark(discipline.getRemark());
        disciplineResponse.setTenantId(discipline.getTenantId());
        disciplineResponse.setCreatedAt(discipline.getCreatedAt());
        disciplineResponse.setCreatedBy(discipline.getCreatedBy());
        disciplineResponse.setUpdatedAt(discipline.getUpdatedAt());
        disciplineResponse.setUpdatedBy(discipline.getUpdatedBy());

        return disciplineResponse;
    }

    public Discipline mapToUpdate(
            UUID tenantId,
            Discipline discipline,
            DisciplineRequest disciplineRequest){

        if (disciplineRequest.getEmployeeId() != null) {

            EmployeeDto employee = employeeServiceClient.getEmployeeById(
                    tenantId,
                    disciplineRequest.getEmployeeId());

            discipline.setEmployeeId(employee.getId());
        }
        if (disciplineRequest.getOffenderId() != null) {

            EmployeeDto offender = employeeServiceClient.getEmployeeById(
                    tenantId,
                    disciplineRequest.getOffenderId());

            discipline.setOffenderId(offender.getId());
        }
        if (disciplineRequest.getOffenseId() != null) {

            Offense offense = validationUtil.getOffenseById(
                    tenantId,
                    disciplineRequest.getOffenseId());

            discipline.setOffense(offense);
        }
        if (disciplineRequest.getOffenseDate() != null) {
            discipline.setOffenseDate(disciplineRequest.getOffenseDate());
        }
        if (disciplineRequest.getStatus() != null) {
            discipline.setStatus(disciplineRequest.getStatus());
        }
        if (disciplineRequest.getDescription() != null) {
            discipline.setDescription(disciplineRequest.getDescription());
        }
        if (disciplineRequest.getRemark() != null) {
            discipline.setRemark(disciplineRequest.getRemark());
        }
        return discipline;
    }

}
