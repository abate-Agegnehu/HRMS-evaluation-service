package com.saas.evaluation.utility;

import com.saas.evaluation.enums.ResourceName;
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

    /* Evaluation Criteria Permission*/
    public void addEvaluationCriteriaPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_EVALUATION_CRITERIA);
    }

    public void getAllEvaluationCriteriaPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_EVALUATION_CRITERIA);
    }

    public void getEvaluationCriteriaByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_EVALUATION_CRITERIA_BY_ID);
    }

    public void updateEvaluationCriteriaPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_EVALUATION_CRITERIA);
    }

    public void deleteEvaluationCriteriaPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_EVALUATION_CRITERIA);
    }

    /* Evaluation Category Permission*/

    public void addEvaluationCategoryPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_EVALUATION_CATEGORY);
    }

    public void getAllEvaluationCategoryPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_EVALUATION_CATEGORY);
    }

    public void getEvaluationCategoryByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_EVALUATION_CATEGORY_BY_ID);
    }

    public void updateEvaluationCategoryPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_EVALUATION_CATEGORY);
    }

    public void deleteEvaluationCategoryPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_EVALUATION_CATEGORY);
    }

    /* Evaluation Session  Permission */
    public void addEvaluationSessionPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_EVALUATION_SESSION);
    }

    public void getAllEvaluationSessionPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_EVALUATION_SESSION);
    }

    public void getEvaluationSessionByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_EVALUATION_SESSION_BY_ID);
    }

    public void updateEvaluationSessionPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_EVALUATION_SESSION);
    }

    public void deleteEvaluationSessionPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_EVALUATION_SESSION);
    }

    /*  Evaluation Result Permission */
    public void addEvaluationResultPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_EVALUATION_RESULT);
    }

    public void getAllEvaluationResultPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_EVALUATION_RESULT);
    }

    public void getEvaluationResultByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_EVALUATION_RESULT_BY_ID);
    }

    public void updateEvaluationResultPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_EVALUATION_RESULT);
    }

    public void deleteEvaluationResultPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_EVALUATION_RESULT);
    }

    /* Evaluation Level  Permission */
    public void addEvaluationLevelPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.ADD_EVALUATION_LEVEL);
    }

    public void getAllEvaluationLevelPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_ALL_EVALUATION_LEVEL);
    }

    public void getEvaluationLevelByIdPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.GET_EVALUATION_LEVEL_BY_ID);
    }

    public void updateEvaluationLevelPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.UPDATE_EVALUATION_LEVEL);
    }

    public void deleteEvaluationLevelPermission(UUID tenantId) {
        checkPermission(tenantId, ResourceName.DELETE_EVALUATION_LEVEL);
    }

    private void checkPermission(UUID tenantId, ResourceName resourceName) {
        boolean hasPermission = permissionUtil.hasPermission(tenantId, resourceName.getValue());
        if (!hasPermission) {
            throw new AccessDeniedException("Access Denied");
        }
    }
}
