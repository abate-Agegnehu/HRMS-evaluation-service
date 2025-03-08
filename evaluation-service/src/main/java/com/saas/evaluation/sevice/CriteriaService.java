package com.saas.evaluation.sevice;

import com.saas.evaluation.dto.request.CriteriaRequest;
import com.saas.evaluation.dto.response.CriteriaResponse;
import com.saas.evaluation.exception.ResourceNotFoundException;
import com.saas.evaluation.mapper.CriteriaMapper;

import com.saas.evaluation.model.Criteria;
import com.saas.evaluation.repository.CriteriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CriteriaService {

    private final CriteriaRepository criteriaRepository;
    private final CriteriaMapper criteriaMapper;

    public CriteriaResponse createCriteria(UUID tenantId,
                                           CriteriaRequest criteriaRequest){

        Criteria criteria =  criteriaMapper.mapToEntity(tenantId, criteriaRequest);
        Criteria savedCriteria= criteriaRepository.save(criteria);
        return criteriaMapper.mapToDto(savedCriteria);
    }

    public List<CriteriaResponse>getAllCriteria(UUID tenantId){
                  List<Criteria>criteria=criteriaRepository.findByTenantId(tenantId);
       return  criteria.stream()
                .map(criteriaMapper::mapToDto)
               .collect(Collectors.toList());

    }

    public CriteriaResponse getCriteriaById(UUID tenantId,
                                            UUID id){

        Criteria criteria=this.getCriteria(tenantId,id);
        return criteriaMapper.mapToDto(criteria);

    }



    public CriteriaResponse updateCriteria(UUID tenantId,
                                           UUID id,
                                           CriteriaRequest criteriaRequest){

        Criteria criteria=this.getCriteria(tenantId,id);
        Criteria mappedCriteria=criteriaMapper.updateEntity(tenantId,criteria,criteriaRequest);
        Criteria savedCriteria=criteriaRepository.save(mappedCriteria);
        return criteriaMapper.mapToDto(savedCriteria);
    }

    public String deleteCriteria(UUID tenantId,
                                 UUID id){

        Criteria criteria= this.getCriteria(tenantId,id);
        criteriaRepository.delete(criteria);
        return "Criteria Deleted Successfully";
    }

    private Criteria getCriteria(UUID tenantId,
                                 UUID criteriaId){

        return criteriaRepository.findById(criteriaId)
                .filter(c -> c.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Criteria not found with id " + criteriaId));
    }
}
