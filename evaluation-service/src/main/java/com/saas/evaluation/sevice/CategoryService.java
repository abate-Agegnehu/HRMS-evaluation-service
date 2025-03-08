package com.saas.evaluation.sevice;

import com.saas.evaluation.dto.request.CategoryRequest;
import com.saas.evaluation.dto.response.CategoryResponse;
import com.saas.evaluation.exception.ResourceNotFoundException;
import com.saas.evaluation.mapper.CategoryMapper;
import com.saas.evaluation.model.Category;
import com.saas.evaluation.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryResponse createCategory(UUID tenantId,
                                           CategoryRequest categoryRequest) {

        Category category = categoryMapper.mapToEntity(tenantId, categoryRequest);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.mapToDto(savedCategory);
    }

    public List<CategoryResponse> getAllCategories(UUID tenantId) {

          List<Category>categories=categoryRepository.findByTenantId(tenantId);
        return  categories.stream()
                .map(categoryMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public CategoryResponse getCategoryById(UUID tenantId ,
                                            UUID id) {

        Category category = this.getCategory(tenantId,id);
        return categoryMapper.mapToDto(category);
    }

    public CategoryResponse updateCategory(UUID tenantId,
                                           UUID id,
                                           CategoryRequest categoryRequest) {

        Category category = this.getCategory(tenantId,id);
        categoryMapper.updateEntity(tenantId,category, categoryRequest);
        categoryRepository.save(category);
        return categoryMapper.mapToDto(category);
    }

    public String deleteCategory(UUID tenantId,
                                 UUID id) {

        Category category = this.getCategory(tenantId,id);
        categoryRepository.delete(category);
        return "Category deleted successfully";
    }

    private Category getCategory(UUID tenantId,
                                 UUID categoryId){

        return categoryRepository.findById(categoryId)
                        .filter(c -> c.getTenantId().equals(tenantId))
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Category not found with id " + categoryId));
    }
}
