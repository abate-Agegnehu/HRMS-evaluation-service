package com.saas.evaluation.controllers;

import com.saas.evaluation.dto.request.SessionRequest;
import com.saas.evaluation.dto.response.SessionResponse;
import com.saas.evaluation.sevice.SessionService;
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
@RequestMapping("/api/evaluation/sessions/{tenantId}")
@RequiredArgsConstructor
@Tag(name = "Session")
public class SessionController {

    private final SessionService sessionService;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping("/add")
    public ResponseEntity<?> createSession(
            @PathVariable UUID tenantId,
            @Valid @RequestBody SessionRequest sessionRequest){

        permissionEvaluator.addEvaluationSessionPermission(tenantId);
        SessionResponse sessionResponse=sessionService.createSession(tenantId,sessionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(sessionResponse);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?>getAllSession(
            @PathVariable UUID tenantId){

        permissionEvaluator.getAllEvaluationSessionPermission(tenantId);
        List<SessionResponse> session=sessionService.getAllSession(tenantId);
        return  ResponseEntity.status(HttpStatus.OK).body(session);
    }

    @GetMapping("/get/{id}")
    public  ResponseEntity<?> getSessionById(
            @PathVariable UUID tenantId,
            @PathVariable UUID id){

        permissionEvaluator.getEvaluationSessionByIdPermission(tenantId);
        SessionResponse sessionResponse= sessionService.getSessionById(tenantId, id);
        return  ResponseEntity.status(HttpStatus.OK).body(sessionResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSessionById(
            @PathVariable UUID tenantId,
            @PathVariable UUID id,
            @RequestBody SessionRequest sessionRequest){

        permissionEvaluator.updateEvaluationSessionPermission(tenantId);
        SessionResponse sessionResponse= sessionService.updateSession(tenantId, id,sessionRequest);
        return ResponseEntity.status(HttpStatus.OK).body(sessionResponse);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSession(
            @PathVariable UUID tenantId,
            @PathVariable UUID id){

        permissionEvaluator.deleteEvaluationSessionPermission(tenantId);
        String message= sessionService.deleteSession(tenantId, id);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
