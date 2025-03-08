package com.saas.evaluation.utility;

import com.saas.evaluation.exception.ResourceNotFoundException;
import com.saas.evaluation.model.Category;
import com.saas.evaluation.model.Criteria;
import com.saas.evaluation.model.Session;
import com.saas.evaluation.repository.CategoryRepository;
import com.saas.evaluation.repository.CriteriaRepository;
import com.saas.evaluation.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ValidationUtil {
    private  final CategoryRepository categoryRepository;
    private  final CriteriaRepository criteriaRepository;
    private  final SessionRepository sessionRepository;

    public Category getCategoryById(UUID id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category not found with id '" +id+"'"));

        return category;
    }


    public Session getSessionById(UUID id){
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Session not found with id '" +id+"'"));

        return session;
    }
    public Criteria getCriteriaById(UUID id){
        Criteria criteria = criteriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Criteria not found with id '" +id+"'"));

        return criteria;
    }

}
