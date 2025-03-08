package com.saas.discipline.controllers;

import com.saas.discipline.dto.request.ApproveRequest;
import com.saas.discipline.dto.request.DisciplineRequest;
import com.saas.discipline.dto.response.DisciplineResponse;
import com.saas.discipline.enums.DecisionStatus;
import com.saas.discipline.service.DisciplineService;
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
@RequestMapping("/api/discipline/discipline/{tenantId}")
@RequiredArgsConstructor
@Tag(name = "Discipline")
public class DisciplineController {

    private final DisciplineService disciplineService;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping("/add")
    public ResponseEntity<?> createRequest(
            @PathVariable UUID tenantId,
            @Valid @RequestBody DisciplineRequest disciplineRequest){

        permissionEvaluator.addDisciplinePermission(tenantId);
        DisciplineResponse disciplineResponse = disciplineService.createDiscipline(tenantId, disciplineRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(disciplineResponse);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllRequest(
            @PathVariable UUID tenantId){

        permissionEvaluator.getAllDisciplinePermission(tenantId);
        List<?> discipline=disciplineService.getAllDiscipline(tenantId);
        return ResponseEntity.status(HttpStatus.OK).body(discipline);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getRequestById(
            @PathVariable UUID tenantId,
            @PathVariable  UUID id){

        permissionEvaluator.getDisciplineByIdPermission(tenantId);
        DisciplineResponse discipline=disciplineService.getDisciplineById(tenantId, id);
        return ResponseEntity.status(HttpStatus.OK).body(discipline);

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRequest(
            @PathVariable UUID tenantId,
            @PathVariable UUID id,
            @RequestBody DisciplineRequest disciplineRequest){

        permissionEvaluator.updateDisciplinePermission(tenantId);
        DisciplineResponse disciplineResponse=disciplineService.updateDiscipline(tenantId,id,disciplineRequest);
        return ResponseEntity.status(HttpStatus.OK).body(disciplineResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRequest(
            @PathVariable UUID tenantId,
            @PathVariable UUID id){

        permissionEvaluator.deleteDisciplinePermission(tenantId);
        String message=disciplineService.deleteDiscipline(tenantId,id);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
    @PutMapping("/approve/{disciplineId}")
    public ResponseEntity<?> approveDiscipline(
            @PathVariable UUID tenantId,
            @PathVariable UUID disciplineId,
            @RequestBody ApproveRequest approveRequest){
        permissionEvaluator.approveDisciplinePermission(tenantId);
        DisciplineResponse disciplineResponse=disciplineService.approveDiscipline(tenantId,disciplineId,approveRequest);
        return ResponseEntity.status(HttpStatus.OK).body(disciplineResponse);

    }

}