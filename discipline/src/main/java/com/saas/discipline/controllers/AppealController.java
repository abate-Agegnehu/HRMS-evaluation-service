package com.saas.discipline.controllers;

import com.saas.discipline.dto.request.AppealRequest;
import com.saas.discipline.dto.response.AppealResponse;
import com.saas.discipline.service.AppealService;
import com.saas.discipline.utility.PermissionEvaluator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/discipline/appeal/{tenantId}")
@RequiredArgsConstructor
@Tag(name = "Appeal")
public class AppealController {

    private final AppealService appealService;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping("/add")
    public ResponseEntity<?> createAppeal(
            @PathVariable UUID tenantId,
            @Valid @RequestBody AppealRequest appealRequest){

        try {
        permissionEvaluator.addAppealPermission(tenantId);
        AppealResponse appealResponse = appealService.createAppeal(tenantId, appealRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(appealResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllAppeal(
            @PathVariable UUID tenantId){

        permissionEvaluator.getAllAppealPermission(tenantId);
        List<?> appeal=appealService.getAllAppeal(tenantId);
        return ResponseEntity.status(HttpStatus.OK).body(appeal);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAppealById(
            @PathVariable UUID tenantId,
            @PathVariable  UUID id){

        permissionEvaluator.getAppealByIdPermission(tenantId);
        AppealResponse appeal=appealService.getAppealById(tenantId, id);
        return ResponseEntity.status(HttpStatus.OK).body(appeal);

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAppeal(
            @PathVariable UUID tenantId,
            @PathVariable UUID id,
            @RequestBody AppealRequest appealRequest){

        try {
        permissionEvaluator.updateAppealPermission(tenantId);
        AppealResponse appealResponse=appealService.updateAppeal(tenantId,id,appealRequest);
        return ResponseEntity.status(HttpStatus.OK).body(appealResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAppeal(
            @PathVariable UUID tenantId,
            @PathVariable UUID id){

        permissionEvaluator.deleteAppealPermission(tenantId);
        String message=appealService.deleteAppeal(tenantId,id);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

}