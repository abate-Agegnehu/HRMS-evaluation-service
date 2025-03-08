package com.saas.evaluation.data;

import com.saas.evaluation.dto.eventDto.ResourceEvent;
import com.saas.evaluation.enums.ResourceName;
import com.saas.evaluation.mapper.ResourceMapper;
import com.saas.evaluation.model.Resource;
import com.saas.evaluation.repository.ResourceRepository;
import java.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResourceData {

    private final ResourceRepository resourceRepository;
    private final ResourceMapper resourceMapper;

    public void saveResource(ResourceEvent eventResponse) {

        Set<Resource> resources = new HashSet<>();

        String defaultRole = "default_role";

        /* Resource */
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_ALL_RESOURCES.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_RESOURCES_BY_ROLE_NAME.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_RESOURCE_BY_ID.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_RESOURCE_BY_NAME.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GRANT_RESOURCE_ACCESS_TO_ROLE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .REVOKE_RESOURCE_ACCESS_FROM_ROLE.getValue(), null, eventResponse));

        /* Evaluation Criteria  */
        resources.add(resourceMapper.mapToEntity(ResourceName
                .ADD_EVALUATION_CRITERIA.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_ALL_EVALUATION_CRITERIA.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_EVALUATION_CRITERIA_BY_ID.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .UPDATE_EVALUATION_CRITERIA.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .DELETE_EVALUATION_CRITERIA.getValue(), null, eventResponse));

        /* Evaluation Category */
        resources.add(resourceMapper.mapToEntity(ResourceName
                .ADD_EVALUATION_CATEGORY.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_ALL_EVALUATION_CATEGORY.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_EVALUATION_CATEGORY_BY_ID.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .UPDATE_EVALUATION_CATEGORY.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .DELETE_EVALUATION_CATEGORY.getValue(), null, eventResponse));

        /* Evaluation  Session */
        resources.add(resourceMapper.mapToEntity(ResourceName
                .ADD_EVALUATION_SESSION.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_ALL_EVALUATION_SESSION.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_EVALUATION_SESSION_BY_ID.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .UPDATE_EVALUATION_SESSION.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .DELETE_EVALUATION_SESSION.getValue(), null, eventResponse));

        /* Candidate Evaluation*/
        resources.add(resourceMapper.mapToEntity(ResourceName
                .ADD_EVALUATION_CATEGORY.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_ALL_EVALUATION_CATEGORY.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_EVALUATION_CATEGORY_BY_ID.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .UPDATE_EVALUATION_CATEGORY.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .DELETE_EVALUATION_CATEGORY.getValue(), null, eventResponse));

        /* Evaluation Result */
        resources.add(resourceMapper.mapToEntity(ResourceName
                .ADD_EVALUATION_RESULT.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_ALL_EVALUATION_RESULT.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_EVALUATION_RESULT_BY_ID.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .UPDATE_EVALUATION_RESULT.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .DELETE_EVALUATION_RESULT.getValue(), null, eventResponse));

        /* Evaluation Level */
        resources.add(resourceMapper.mapToEntity(ResourceName
                .ADD_EVALUATION_LEVEL.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_ALL_EVALUATION_LEVEL.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_EVALUATION_LEVEL_BY_ID.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .UPDATE_EVALUATION_LEVEL.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .DELETE_EVALUATION_LEVEL.getValue(), null, eventResponse));


        List<Resource> existedResources = resourceRepository.findByTenantId(eventResponse.getTenantId());
        resourceRepository.deleteAll(existedResources);
        resourceRepository.saveAll(resources);
    }
}
