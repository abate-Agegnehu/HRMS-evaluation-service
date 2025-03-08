package com.saas.discipline.utility;

import com.saas.discipline.enums.ResourceName;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PermissionEvaluator {

    private final PermissionUtil permissionUtil;

    /* Resource Permission */
    public void getAllResourcesPermission(UUID tenantId) {
        boolean isAdmin = permissionUtil.isAdmin();
        if (!isAdmin) {
            checkPermission(tenantId, ResourceName.GET_ALL_RESOURCES);
        }
    }

    public void getResourcesByRoleNamePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_RESOURCES_BY_ROLE_NAME);
    }

    public void getResourceByIdPermission(UUID tenantId) {
        boolean isAdmin = permissionUtil.isAdmin();
        if (!isAdmin) {
            checkPermission(tenantId, ResourceName.GET_RESOURCE_BY_ID);
        }
    }

    public void getResourceByNamePermission(UUID tenantId) {
        boolean isAdmin = permissionUtil.isAdmin();
        if (!isAdmin) {
            permissionUtil.isTenantUser(tenantId);
        }
    }

    public void grantResourceAccessToRolePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GRANT_RESOURCE_ACCESS_TO_ROLE);
    }

    public void revokeResourceAccessFromRolePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.REVOKE_RESOURCE_ACCESS_FROM_ROLE);
    }

    /* Discipline Appeal Permission*/
    public void addAppealPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_DISCIPLINE_APPEAL);
    }

    public void getAllAppealPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_DISCIPLINE_APPEAL);
    }

    public void getAppealByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_DISCIPLINE_APPEAL_BY_ID);
    }

    public void updateAppealPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_DISCIPLINE_APPEAL);
    }

    public void deleteAppealPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_DISCIPLINE_APPEAL);
    }

    /*  Discipline Offense Permission */
    public void addOffensePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_DISCIPLINE_OFFENSE);
    }

    public void getAllOffensePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_DISCIPLINE_OFFENSE);
    }

    public void getOffenseByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_DISCIPLINE_OFFENSE_BY_ID);
    }

    public void updateOffensePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_DISCIPLINE_OFFENSE);
    }

    public void deleteOffensePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_DISCIPLINE_OFFENSE);
    }

    /* Discipline Penalty  Permission */
    public void addDisciplinePenaltyPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_DISCIPLINE_PENALTY);
    }

    public void getAllDisciplinePenaltyPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_DISCIPLINE_PENALTY);
    }

    public void getDisciplinePenaltyByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_DISCIPLINE_PENALTY_BY_ID);
    }

    public void updateDisciplinePenaltyPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_DISCIPLINE_PENALTY);
    }

    public void deleteDisciplinePenaltyPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_DISCIPLINE_PENALTY);
    }

    /* DisciplinePermission */
    public void addDisciplinePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_DISCIPLINE);
    }

    public void getAllDisciplinePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_DISCIPLINE);
    }

    public void getDisciplineByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_DISCIPLINE_BY_ID);
    }

    public void updateDisciplinePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_DISCIPLINE);
    }

    public void deleteDisciplinePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_DISCIPLINE);
    }
    public void approveDisciplinePermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.APPROVE_DISCIPLINE);
    }



    private void checkPermission(UUID tenantId, ResourceName resourceName) {
        boolean hasPermission = permissionUtil.hasPermission(tenantId, resourceName.getValue());
        if (!hasPermission) {
            throw new AccessDeniedException("Access Denied");
        }
    }
}
