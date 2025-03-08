package com.saas.evaluation.controllers;

import com.saas.evaluation.dto.request.ResultRequest;
import com.saas.evaluation.dto.response.ResultResponse;
import com.saas.evaluation.sevice.ResultService;
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
@RequestMapping("/api/evaluation/results/{tenantId}")
@RequiredArgsConstructor
@Tag(name = "Result")
public class ResultController {

    private final ResultService resultService;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping("/add")
    public  ResponseEntity<?> createResult(
            @PathVariable UUID tenantId,
            @Valid @RequestBody ResultRequest resultRequest){

        permissionEvaluator.addEvaluationResultPermission(tenantId);
        ResultResponse resultResponse= resultService.createResult(tenantId,resultRequest);
        return  ResponseEntity.status(HttpStatus.CREATED).body(resultResponse);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllResult(
            @PathVariable UUID tenantId){

        permissionEvaluator.getAllEvaluationResultPermission(tenantId);
        List<ResultResponse>result= resultService.getAllResult(tenantId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getResultById(
            @PathVariable UUID tenantId,
            @PathVariable UUID id){

        permissionEvaluator.getEvaluationResultByIdPermission(tenantId);
        ResultResponse resultResponse=resultService.getResultById(tenantId,id);
        return ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }

    @PutMapping("/update/{id}")
    public  ResponseEntity<?> updateResultById(
            @PathVariable UUID tenantId,
            @PathVariable UUID id,
            @Valid @RequestBody ResultRequest resultRequest){

        permissionEvaluator.updateEvaluationLevelPermission(tenantId);
        ResultResponse resultResponse=resultService.updateResultById(tenantId,id,resultRequest);
        return  ResponseEntity.status(HttpStatus.OK).body(resultResponse);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteResult(
            @PathVariable UUID tenantId,
            @PathVariable UUID id){

        permissionEvaluator.deleteEvaluationLevelPermission(tenantId);
        String message= resultService.deleteResult(tenantId,id);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

}
