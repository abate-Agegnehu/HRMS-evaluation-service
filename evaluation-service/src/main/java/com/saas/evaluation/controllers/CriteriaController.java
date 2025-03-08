package com.saas.evaluation.controllers;

import com.saas.evaluation.dto.request.CriteriaRequest;
import com.saas.evaluation.dto.response.CriteriaResponse;
import com.saas.evaluation.sevice.CriteriaService;
import com.saas.evaluation.utility.PermissionEvaluator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/evaluation/criteria/{tenantId}")
@RequiredArgsConstructor
@Tag(name = "Criteria")
public class CriteriaController {

    private final CriteriaService criteriaService;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping("/add")
    public ResponseEntity<?> createCriteria(
            @PathVariable UUID tenantId,
            @Valid @RequestBody CriteriaRequest criteriaRequest){

        permissionEvaluator.addEvaluationCriteriaPermission(tenantId);
        CriteriaResponse criteriaResponse = criteriaService.createCriteria(tenantId, criteriaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(criteriaResponse);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllCriteria(
            @PathVariable UUID tenantId){

        permissionEvaluator.getAllEvaluationCriteriaPermission(tenantId);
        List<CriteriaResponse> criteria=criteriaService.getAllCriteria(tenantId);
        return ResponseEntity.status(HttpStatus.OK).body(criteria);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCriteriaById(
            @PathVariable UUID tenantId,
            @PathVariable  UUID id){

        permissionEvaluator.getEvaluationCriteriaByIdPermission(tenantId);
        CriteriaResponse criteria=criteriaService.getCriteriaById(tenantId, id);
        return ResponseEntity.status(HttpStatus.OK).body(criteria);

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCriteria(
            @PathVariable UUID tenantId,
            @PathVariable UUID id,
            @RequestBody CriteriaRequest criteriaRequest){

        permissionEvaluator.updateEvaluationCriteriaPermission(tenantId);
        CriteriaResponse criteriaResponse=criteriaService.updateCriteria(tenantId,id,criteriaRequest);
        return ResponseEntity.status(HttpStatus.OK).body(criteriaResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCriteria(
            @PathVariable UUID tenantId,
            @PathVariable UUID id){

        permissionEvaluator.deleteEvaluationCriteriaPermission(tenantId);
        String message=criteriaService.deleteCriteria(tenantId,id);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

}
