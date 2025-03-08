package com.saas.discipline.data;

import com.saas.discipline.dto.eventDto.ResourceEvent;
import com.saas.discipline.enums.ResourceName;
import com.saas.discipline.mapper.ResourceMapper;
import com.saas.discipline.model.Resource;
import com.saas.discipline.repository.ResourceRepository;
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

        /* Discipline Appeal  */
        resources.add(resourceMapper.mapToEntity(ResourceName
                .ADD_DISCIPLINE_APPEAL.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_ALL_DISCIPLINE_APPEAL.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_DISCIPLINE_APPEAL_BY_ID.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .UPDATE_DISCIPLINE_APPEAL.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .DELETE_DISCIPLINE_APPEAL.getValue(), null, eventResponse));

        /* Discipline  Offense */
        resources.add(resourceMapper.mapToEntity(ResourceName
                .ADD_DISCIPLINE_OFFENSE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_ALL_DISCIPLINE_OFFENSE.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_DISCIPLINE_OFFENSE_BY_ID.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .UPDATE_DISCIPLINE_OFFENSE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .DELETE_DISCIPLINE_OFFENSE.getValue(), null, eventResponse));

        /* Discipline Penalty */
        resources.add(resourceMapper.mapToEntity(ResourceName
                .ADD_DISCIPLINE_PENALTY.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_ALL_DISCIPLINE_PENALTY.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_DISCIPLINE_PENALTY_BY_ID.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .UPDATE_DISCIPLINE_PENALTY.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .DELETE_DISCIPLINE_PENALTY.getValue(), null, eventResponse));

        /* Discipline Request */
        resources.add(resourceMapper.mapToEntity(ResourceName
                .ADD_DISCIPLINE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_ALL_DISCIPLINE.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .GET_DISCIPLINE_BY_ID.getValue(), defaultRole, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .UPDATE_DISCIPLINE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .DELETE_DISCIPLINE.getValue(), null, eventResponse));
        resources.add(resourceMapper.mapToEntity(ResourceName
                .APPROVE_DISCIPLINE.getValue(), null, eventResponse));


        List<Resource> existedResources = resourceRepository.findByTenantId(eventResponse.getTenantId());
        resourceRepository.deleteAll(existedResources);
        resourceRepository.saveAll(resources);
    }
}
