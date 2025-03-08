package com.saas.evaluation.utility;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class SecurityUtil {

    public Jwt getUserJwt() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (Jwt) authentication.getPrincipal();
    }

    public String getUserToken() {
        return String.format("Bearer %s", getUserJwt().getTokenValue());
    }

    public String getUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        return jwt.getSubject();
    }

    public String getClaim(String claim) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        return jwt.hasClaim(claim) ? jwt.getClaim(claim).toString() : null;
    }

    public String getTenantId() {
        return getClaim("tenantId");
    }

    public String getName() {
        return getClaim("name");
    }
}
