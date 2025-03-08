package com.saas.discipline.controllers;

import com.saas.discipline.dto.request.OffenseRequest;
import com.saas.discipline.dto.response.OffenseResponse;
import com.saas.discipline.service.OffenseService;
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
@RequestMapping("/api/discipline/offense/{tenantId}")
@RequiredArgsConstructor
@Tag(name = "Offense")
public class OffenseController {

    private final OffenseService offenseService;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping("/add")
    public ResponseEntity<?> createOffense(
            @PathVariable UUID tenantId,
            @Valid @RequestBody OffenseRequest offenseRequest){

        permissionEvaluator.addOffensePermission(tenantId);
        OffenseResponse offenseResponse = offenseService.createOffense(tenantId, offenseRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(offenseResponse);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllOffense(
            @PathVariable UUID tenantId){

        permissionEvaluator.getAllOffensePermission(tenantId);
        List<?> offense=offenseService.getAllOffense(tenantId);
        return ResponseEntity.status(HttpStatus.OK).body(offense);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOffenseById(
            @PathVariable UUID tenantId,
            @PathVariable  UUID id){

        permissionEvaluator.getOffenseByIdPermission(tenantId);
        OffenseResponse offense=offenseService.getOffenseById(tenantId, id);
        return ResponseEntity.status(HttpStatus.OK).body(offense);

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOffense(
            @PathVariable UUID tenantId,
            @PathVariable UUID id,
            @RequestBody OffenseRequest offenseRequest){

        permissionEvaluator.updateOffensePermission(tenantId);
        OffenseResponse offenseResponse=offenseService.updateOffense(tenantId,id,offenseRequest);
        return ResponseEntity.status(HttpStatus.OK).body(offenseResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOffense(
            @PathVariable UUID tenantId,
            @PathVariable UUID id){

        permissionEvaluator.deleteOffensePermission(tenantId);
        String message=offenseService.deleteOffense(tenantId,id);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

}