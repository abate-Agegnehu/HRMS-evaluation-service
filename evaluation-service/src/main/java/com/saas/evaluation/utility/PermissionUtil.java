package com.saas.evaluation.utility;

import com.saas.evaluation.config.RoleConverter;
import com.saas.evaluation.enums.ResourceStatus;
import com.saas.evaluation.model.Resource;
import com.saas.evaluation.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PermissionUtil {

    private final RoleConverter roleConverter;
    private final SecurityUtil securityUtil;
    private final ResourceRepository resourceRepository;

    public boolean hasPermission(UUID tenantId, String resourceName) {

        Jwt jwt = securityUtil.getUserJwt();
        Collection<GrantedAuthority> userRoles = roleConverter.extractAuthorities(jwt);
        Resource resource = getResourceByName(tenantId, resourceName);
        Set<String> requiredRoles = resource.getRequiredRoles();
        if (requiredRoles == null || requiredRoles.isEmpty()) {
            throw new AccessDeniedException(
                    "Access Denied - No roles are assigned to access this resource.");
        }
        for (GrantedAuthority authority : userRoles) {
            String prefix = resource.getTenantAbbreviatedName().toLowerCase() + "_";
            Set<String> roles = new HashSet<>();
            for (String requiredRole : requiredRoles) {
                String role = prefix + requiredRole;
                requiredRole = requiredRole.replace(requiredRole, role);
                roles.add(requiredRole);
            }
            if (roles.contains(authority.getAuthority())) {
                return true;
            }
        }
        return false;
    }

    public boolean isAdmin() {

        Jwt jwt = securityUtil.getUserJwt();
        Collection<GrantedAuthority> userRoles = roleConverter.extractAuthorities(jwt);
        for (GrantedAuthority authority : userRoles) {
            if (authority.getAuthority().equals("admin")) {
                return true;
            }
        }
        return false;
    }

    private Resource getResourceByName(UUID tenantId, String resourceName) {

        this.isTenantUser(tenantId);
        Resource resource =  resourceRepository
                .findByTenantIdAndResourceName(tenantId, resourceName)
                .orElseThrow(() -> new AccessDeniedException("Access Denied - Resource not found"));
        if (resource.getStatus().equals(ResourceStatus.ACTIVE)) {
            return resource;
        } else {
            throw new AccessDeniedException("Access Denied - Inactive Resource");
        }
    }

    public void isTenantUser(UUID tenantId) {

        String userTenantId = securityUtil.getTenantId();
        if (!userTenantId.isEmpty() || userTenantId.equals(tenantId.toString())) {
            return;
        }
        throw new AccessDeniedException(
                "Access Denied - You are not associated with the specified tenant");
    }
}
