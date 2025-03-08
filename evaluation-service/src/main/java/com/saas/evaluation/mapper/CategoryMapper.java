package com.saas.evaluation.mapper;

import com.saas.evaluation.dto.request.CategoryRequest;
import com.saas.evaluation.dto.response.CategoryResponse;

import com.saas.evaluation.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class CategoryMapper {

//    private final EmployeeServiceClient employeeServiceClient;

    public Category mapToEntity(UUID tenantId,
                                CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setTenantId(tenantId);
        if(categoryRequest.getWeight()<=100){
            category.setWeight(categoryRequest.getWeight());
        }

        category.setDescription(categoryRequest.getDescription());
        category.setCreatedAt(LocalDateTime.now());
        category.setCreatedBy(null);
        return category;
    }

    public CategoryResponse mapToDto(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        categoryResponse.setWeight(category.getWeight());
        categoryResponse.setDescription(category.getDescription());
        categoryResponse.setCreatedAt(LocalDateTime.now());
        categoryResponse.setCreatedAt(category.getCreatedAt());
        categoryResponse.setCreatedBy(category.getCreatedBy());
        categoryResponse.setUpdatedAt(category.getUpdatedAt());
        categoryResponse.setUpdatedBy(category.getUpdatedBy());
        categoryResponse.setTenantId(category.getTenantId());

        return categoryResponse;
    }

    public void updateEntity(
            UUID tenantId,
            Category category,
            CategoryRequest categoryRequest) {

        if (categoryRequest.getName() != null) {
            category.setName(categoryRequest.getName());
        }
        if (categoryRequest.getWeight() != null) {
            if(categoryRequest.getWeight()<=100) {
                category.setWeight(categoryRequest.getWeight());
            }
        }
        if (categoryRequest.getDescription() != null) {
            category.setDescription(categoryRequest.getDescription());
        }
        if (categoryRequest.getDescription() != null) {
            category.setDescription(categoryRequest.getDescription());
        }
        if(tenantId!=null){
            category.setTenantId(tenantId);
        }

        category.setUpdatedAt(LocalDateTime.now());
        category.setUpdatedBy(null);
    }
}
