package com.saas.discipline.service;

import com.saas.discipline.dto.request.ApproveRequest;
import com.saas.discipline.dto.request.DisciplineRequest;
import com.saas.discipline.dto.response.DisciplineResponse;
import com.saas.discipline.enums.DecisionStatus;
import com.saas.discipline.mapper.DisciplineMapper;
import com.saas.discipline.model.Discipline;
import com.saas.discipline.repository.DisciplineRepository;
import com.saas.discipline.utility.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DisciplineService {

    private final DisciplineRepository disciplineRepository;
    private final DisciplineMapper disciplineMapper;
    private final ValidationUtil validationUtil;

    public DisciplineResponse createDiscipline(
            UUID tenantId,
            DisciplineRequest disciplineRequest){

           Discipline discipline=disciplineMapper.mapToEntity(tenantId,disciplineRequest);
           Discipline savedRequest=disciplineRepository.save(discipline);

           return disciplineMapper.mapToDto(savedRequest);
    }

    public List<?> getAllDiscipline(UUID tenantId){

        List<Discipline>disciplines=disciplineRepository.findByTenantId(tenantId);

        return disciplines.stream()
                                  .map(disciplineMapper::mapToDto)
                                  .collect(Collectors.toList());

    }

    public DisciplineResponse getDisciplineById(
            UUID tenantId,
            UUID id){

        Discipline discipline=validationUtil.getDisciplineById(tenantId,id);
        return disciplineMapper.mapToDto(discipline);
    }

    public DisciplineResponse updateDiscipline(
            UUID tenantId,
            UUID id ,
            DisciplineRequest disciplineRequest){

        Discipline discipline=validationUtil.getDisciplineById(tenantId,id);

        Discipline mappedDiscipline=disciplineMapper.mapToUpdate(
                tenantId,
                discipline,
                disciplineRequest);

        Discipline savedDiscipline= disciplineRepository.save(mappedDiscipline);
        return disciplineMapper.mapToDto(savedDiscipline);
    }

    public String deleteDiscipline(UUID tenantId,UUID id){
        Discipline discipline=validationUtil.getDisciplineById(tenantId,id);
        disciplineRepository.delete(discipline);

        return " Request is Successfully deleted";
    }

    public DisciplineResponse approveDiscipline(
            UUID tenantId,
            UUID disciplineId,
            ApproveRequest approveRequest){

        Discipline discipline=validationUtil.getDisciplineById(tenantId,disciplineId);
        if (approveRequest.getDecisionStatus() != null) {
            discipline.setStatus(approveRequest.getDecisionStatus());
        }
        if(approveRequest.getRemark()!=null){
            discipline.setRemark(approveRequest.getRemark());
        }
        Discipline approvedDiscipline=disciplineRepository.save(discipline);
        return disciplineMapper.mapToDto(approvedDiscipline);
    }

}
