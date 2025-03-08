package com.saas.evaluation.controllers;

import com.saas.evaluation.dto.request.LevelRequest;
import com.saas.evaluation.dto.response.LevelResponse;
import com.saas.evaluation.sevice.LevelService;
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
@RequestMapping("/api/evaluation/levels/{tenantId}")
@RequiredArgsConstructor
@Tag(name = "Level")
public class LevelController {

    private final LevelService levelService;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping("/add")
    public  ResponseEntity<?> createLevel(
            @PathVariable UUID tenantId,
            @Valid @RequestBody LevelRequest levelRequest){

        permissionEvaluator.addEvaluationLevelPermission(tenantId);
        LevelResponse levelResponse=levelService.createLevel(tenantId, levelRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(levelResponse);

    }

    @GetMapping("/get-all")
    public ResponseEntity<?>getAllLevel(
            @PathVariable UUID tenantId){

        permissionEvaluator.getAllEvaluationLevelPermission(tenantId);
        List<LevelResponse> level=levelService.getAllLevel(tenantId);
        return ResponseEntity.status(HttpStatus.OK).body(level);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getLevelById(
            @PathVariable UUID tenantId,
            @PathVariable UUID id){

        permissionEvaluator.getEvaluationLevelByIdPermission(tenantId);
        LevelResponse levelResponse=levelService.getLevelById(tenantId,id);
        return ResponseEntity.status(HttpStatus.OK).body(levelResponse);
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateLevel(
            @PathVariable UUID tenantId,
            @PathVariable UUID id,
            @Valid @RequestBody LevelRequest levelRequest){

        permissionEvaluator.updateEvaluationLevelPermission(tenantId);
        LevelResponse levelResponse=levelService.updateLevel(tenantId, id,levelRequest);
        return ResponseEntity.status(HttpStatus.OK).body(levelResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLevel(
            @PathVariable UUID tenantId,
            @PathVariable UUID id){
        permissionEvaluator.deleteEvaluationLevelPermission(tenantId);
        String message=levelService.deleteLevel(tenantId, id);
       return ResponseEntity.status(HttpStatus.OK).body(message);

    }

}
