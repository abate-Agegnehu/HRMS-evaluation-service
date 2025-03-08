package com.saas.discipline.controllers;

import com.saas.discipline.dto.request.PenaltyRequest;
import com.saas.discipline.dto.response.PenaltyResponse;
import com.saas.discipline.service.PenaltyService;
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
@RequestMapping("/api/discipline/penalty/{tenantId}")
@RequiredArgsConstructor
@Tag(name = "Penalty")
public class PenaltyController {

    private final PenaltyService penaltyService;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping("/add")
    public ResponseEntity<?> createPenalty(
            @PathVariable UUID tenantId,
            @Valid @RequestBody PenaltyRequest penaltyRequest){

        permissionEvaluator.addDisciplinePenaltyPermission(tenantId);
        PenaltyResponse penaltyResponse = penaltyService.createPenalty(tenantId, penaltyRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(penaltyResponse);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllPenalty(
            @PathVariable UUID tenantId){

        permissionEvaluator.getAllDisciplinePenaltyPermission(tenantId);
        List<?> penalty=penaltyService.getAllPenalty(tenantId);
        return ResponseEntity.status(HttpStatus.OK).body(penalty);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPenaltyById(
            @PathVariable UUID tenantId,
            @PathVariable  UUID id){

        permissionEvaluator.getDisciplinePenaltyByIdPermission(tenantId);
        PenaltyResponse penalty=penaltyService.getPenaltyById(tenantId, id);
        return ResponseEntity.status(HttpStatus.OK).body(penalty);

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePenalty(
            @PathVariable UUID tenantId,
            @PathVariable UUID id,
            @RequestBody PenaltyRequest penaltyRequest){

        permissionEvaluator.updateDisciplinePenaltyPermission(tenantId);
        PenaltyResponse penaltyResponse=penaltyService.updatePenalty(tenantId,id,penaltyRequest);
        return ResponseEntity.status(HttpStatus.OK).body(penaltyResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePenalty(
            @PathVariable UUID tenantId,
            @PathVariable UUID id){

        permissionEvaluator.deleteDisciplinePenaltyPermission(tenantId);
        String message=penaltyService.deletePenalty(tenantId,id);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

}