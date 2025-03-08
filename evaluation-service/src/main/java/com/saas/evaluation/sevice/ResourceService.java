package com.saas.evaluation.sevice;

import com.saas.evaluation.dto.response.ResourceResponse;
import com.saas.evaluation.enums.ResourceName;
import com.saas.evaluation.enums.ResourceStatus;
import com.saas.evaluation.exception.ResourceNotFoundException;
import com.saas.evaluation.mapper.ResourceMapper;
import com.saas.evaluation.model.Resource;
import com.saas.evaluation.repository.ResourceRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final ResourceMapper resourceMapper;
    private final Keycloak keycloak;

    @Value("${keycloak.realm}")
    private String realm;


    public List<ResourceResponse> getAllResources(UUID tenantId) {

        List<Resource> resources = resourceRepository.findByTenantId(tenantId);
        return resources.stream().map(resourceMapper::mapToDto).toList();
    }

    public ResourceResponse getResourceById(UUID tenantId,
                                            UUID resourceId) {

        Resource resource = resourceRepository
                .findById(resourceId)
                .filter(res -> res.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Resource not found with id '" + resourceId + "'"));
        return resourceMapper.mapToDto(resource);
    }

    public ResourceResponse getResponseByName(UUID tenantId,
                                              String resourceName) {

        Resource resource = resourceRepository
                .findByTenantIdAndResourceName(tenantId, resourceName)
                .filter(res -> res.getTenantId().equals(tenantId))
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Resource not found with name '" + resourceName + "'"));
        return resourceMapper.mapToDto(resource);
    }

    public List<ResourceResponse> getResourcesByRole(UUID tenantId,
                                                     String roleName) {

        List<Resource> resources = resourceRepository.findAll();
        return resources.stream()
                .filter(r -> r.getTenantId().equals(tenantId))
                .filter(r -> r.getRequiredRoles().contains(roleName))
                .map(resourceMapper::mapToDto)
                .toList();
    }

    @Transactional
    public String grantResourceAccessToRole(UUID tenantId,
                                            UUID resourceId,
                                            String roleName) {

        Resource resource = resourceRepository.findById(resourceId)
                .filter(res -> res.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Resource not found with id '" + resourceId + "'"));
        this.getRoleRepresentation(tenantId, roleName);
        Set<String> requiredRoles = resource.getRequiredRoles();
        if (requiredRoles == null) {
            requiredRoles = new HashSet<>();
        }
        requiredRoles.add(roleName);
        resource.setRequiredRoles(requiredRoles);
        resourceRepository.save(resource);
        return "Role '" + roleName + "' assigned successfully";
    }

    @Transactional
    public String revokeResourceAccessFromRole(UUID tenantId,
                                               UUID resourceId,
                                               String roleName) {

        Resource resource = resourceRepository
                .findById(resourceId)
                .filter(res -> res.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Resource not found with id '" + resourceId + "'"));
        resource.getRequiredRoles().remove(roleName);
        resourceRepository.save(resource);
        return "Role '" + roleName + "' revoked successfully";
    }

    @Transactional
    public List<ResourceResponse> changeResourcesStatus(UUID tenantId,
                                                        ResourceStatus status) {

        List<Resource> resources = resourceRepository.findByTenantId(tenantId);
        List<Resource> updatedResources = new ArrayList<>();
        for (Resource resource : resources) {
            resource.setStatus(status);
            updatedResources.add(resource);
        }
        resources = resourceRepository.saveAll(updatedResources);
        return resources.stream().map(resourceMapper::mapToDto).toList();
    }

    public void getRoleRepresentation(UUID tenantId, String roleName) {
        try {

            String resourceName = ResourceName.GRANT_RESOURCE_ACCESS_TO_ROLE.getValue();
            Resource resource = resourceRepository
                    .findByTenantIdAndResourceName(tenantId, resourceName)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Resource not found with name '" + resourceName + "'"));
            RolesResource rolesResource = keycloak.realm(realm).roles();
            String role = resource.getTenantAbbreviatedName().toLowerCase() + "_" + roleName.toLowerCase();
            RoleRepresentation roleRepresentation = rolesResource.get(role).toRepresentation();
            String roleTenantId = roleRepresentation.getAttributes().get("tenantId").get(0);
            if (roleTenantId.isEmpty() || !roleTenantId.equals(resource.getTenantId().toString())) {
                throw new ResourceNotFoundException("Role not found with name '" + roleName + "'");
            }
        } catch (NotFoundException e) {
            throw new ResourceNotFoundException(
                    "Role not found with name '" + roleName + "' ------" + e.getMessage());
        }
    }
}