package com.saas.evaluation.mapper;

import com.saas.evaluation.dto.request.CriteriaRequest;
import com.saas.evaluation.dto.response.CriteriaResponse;
import com.saas.evaluation.model.Category;
import com.saas.evaluation.model.Criteria;
import com.saas.evaluation.utility.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CriteriaMapper {

    private final ValidationUtil validationUtil;

    public Criteria mapToEntity(
            UUID tenantId,
            CriteriaRequest criteriaRequest) {

       Category category=validationUtil.getCategoryById(criteriaRequest.getCategoryId());
        Criteria criteria = new Criteria();
        criteria.setName(criteriaRequest.getName());
        if(criteriaRequest.getWeight()<=100) {
            criteria.setWeight(criteriaRequest.getWeight());
        }
        criteria.setStatus(criteriaRequest.getStatus());
        criteria.setDescription(criteriaRequest.getDescription());
        criteria.setCreatedAt(LocalDateTime.now());
        criteria.setCreatedBy(null);
        criteria.setCategory(category);
        criteria.setTenantId(tenantId);

        return criteria;
    }

    public CriteriaResponse mapToDto(Criteria criteria) {

        CriteriaResponse criteriaResponse = new CriteriaResponse();
        criteriaResponse.setId(criteria.getId());
        criteriaResponse.setName(criteria.getName());
        criteriaResponse.setWeight(criteria.getWeight());
        criteriaResponse.setStatus(criteria.getStatus().name());
        criteriaResponse.setDescription(criteria.getDescription());
        criteriaResponse.setCategoryId(criteria.getCategory().getId());


        criteriaResponse.setCreatedAt(criteria.getCreatedAt());
        criteriaResponse.setCreatedBy(criteria.getCreatedBy());
        criteriaResponse.setUpdatedAt(criteria.getUpdatedAt());
        criteriaResponse.setUpdatedBy(criteria.getUpdatedBy());
        criteria.setTenantId(criteria.getTenantId());
        return criteriaResponse;
    }

    public Criteria updateEntity(
            UUID tenantId,
            Criteria criteria,
            CriteriaRequest criteriaRequest) {

        if (criteriaRequest.getName() != null) {
            criteria.setName(criteriaRequest.getName());
        }
        if (criteriaRequest.getWeight() != null) {
            if(criteriaRequest.getWeight()<=100) {
                criteria.setWeight(criteriaRequest.getWeight());
            }
        }
        if (criteriaRequest.getStatus() != null) {
            criteria.setStatus(criteriaRequest.getStatus());
        }
        if (criteriaRequest.getDescription() != null) {
            criteria.setDescription(criteriaRequest.getDescription());
        }

        // Update Category if provided
        if (criteriaRequest.getCategoryId() != null) {
            Category category=validationUtil.getCategoryById(criteriaRequest.getCategoryId());
            criteria.setCategory(category);

        }
        if(tenantId!=null){
            criteria.setTenantId(tenantId);
        }
        criteria.setUpdatedAt(LocalDateTime.now());
        criteria.setUpdatedBy(null);
        return criteria;
    }
}
