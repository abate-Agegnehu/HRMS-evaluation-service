package com.saas.evaluation.controllers;
import com.saas.evaluation.dto.request.CategoryRequest;
import com.saas.evaluation.dto.response.CategoryResponse;
import com.saas.evaluation.sevice.CategoryService;
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
@RequestMapping("/api/evaluation/categories/{tenantId}")
@RequiredArgsConstructor
@Tag(name = "Category")
public class CategoryController {

    private final CategoryService categoryService;
    private final PermissionEvaluator permissionEvaluator;

    @PostMapping("/add")
    public ResponseEntity<?> createCategory(
            @PathVariable UUID tenantId,
            @Valid @RequestBody CategoryRequest categoryRequest) {

        permissionEvaluator.addEvaluationCategoryPermission(tenantId);
        CategoryResponse categoryResponse = categoryService
                .createCategory(tenantId, categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponse);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllCategories(
            @PathVariable UUID tenantId) {

        permissionEvaluator.getAllEvaluationCategoryPermission(tenantId);
        List<CategoryResponse> categories = categoryService.getAllCategories(tenantId);
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCategoryById(
            @PathVariable UUID tenantId,
            @PathVariable UUID id) {

        permissionEvaluator.getEvaluationCategoryByIdPermission(tenantId);
        CategoryResponse categoryResponse = categoryService.getCategoryById(tenantId, id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(
            @PathVariable UUID tenantId,
            @PathVariable UUID id,
            @Valid  @RequestBody CategoryRequest categoryRequest) {

        permissionEvaluator.updateEvaluationCategoryPermission(tenantId);
        CategoryResponse categoryResponse = categoryService
                .updateCategory(tenantId,id, categoryRequest);
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(
            @PathVariable UUID tenantId,
            @PathVariable UUID id) {

        permissionEvaluator.deleteEvaluationCategoryPermission(tenantId);
        String message = categoryService.deleteCategory(tenantId, id);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
