package com.saas.evaluation.mapper;

import com.saas.evaluation.client.EmployeeServiceClient;
import com.saas.evaluation.dto.clientDto.EmployeeDto;
import com.saas.evaluation.dto.request.ResultRequest;
import com.saas.evaluation.dto.response.ResultResponse;
import com.saas.evaluation.model.Category;
import com.saas.evaluation.model.Criteria;
import com.saas.evaluation.model.Result;
import com.saas.evaluation.model.Session;
import com.saas.evaluation.utility.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ResultMapper {

    private final ValidationUtil validationUtil;
    private  final EmployeeServiceClient employeeServiceClient;

    public Result mapToEntity(
            UUID tenantId,
            ResultRequest resultRequest) {

        // Ensure employeeDto is correctly retrieved
        EmployeeDto employeeDto = employeeServiceClient.getEmployeeById(tenantId, resultRequest.getEmployeeId());

        // Ensure session, category, and criteria are fetched and valid
        Session session = validationUtil.getSessionById(resultRequest.getSessionId());
        Category category = validationUtil.getCategoryById(resultRequest.getCategoryId());
        Criteria criteria = validationUtil.getCriteriaById(resultRequest.getCriteriaId());

        // Construct and populate Result entity
        Result result = new Result();
        result.setSession(session);
        result.setCategory(category);
        result.setCriteria(criteria);
        result.setEmployeeId(employeeDto.getId());
        result.setResult(resultRequest.getResult());
        result.setReason(resultRequest.getReason());
        result.setCreatedAt(LocalDateTime.now());
        result.setCreatedBy(null); // Adjust as needed
        result.setTenantId(tenantId); // Ensure tenantId is set
        return result;
    }


    public ResultResponse mapToDto(Result result){

            ResultResponse resultResponse = new ResultResponse();

            resultResponse.setId(result.getId());
            resultResponse.setSessionId(result.getSession().getId());
            resultResponse.setEmployeeId(result.getEmployeeId());
            resultResponse.setCategoryId(result.getCategory().getId());
            resultResponse.setCriteriaId(result.getCriteria().getId());
            resultResponse.setResult(result.getResult());
            resultResponse.setReason(result.getReason());
            resultResponse.setCreatedAt(result.getCreatedAt());
            resultResponse.setCreatedBy(result.getCreatedBy());
            resultResponse.setUpdatedAt(result.getUpdatedAt());
            resultResponse.setUpdatedBy(result.getUpdatedBy());
            resultResponse.setTenantId(result.getTenantId());
            return resultResponse;
        }

        public Result updateEntity(
                UUID tenantId,
                Result result,
                ResultRequest resultRequest) {
            if (resultRequest.getSessionId() != null) {
                Session session = new Session();
                session.setId(resultRequest.getSessionId());
                result.setSession(session);
            }

            if (resultRequest.getEmployeeId() != null) {
                EmployeeDto employeeDto=employeeServiceClient.getEmployeeById(tenantId,resultRequest.getEmployeeId());
                result.setEmployeeId(employeeDto.getId());

            }

            if (resultRequest.getCategoryId() != null) {
                Category category = new Category();
                category.setId(resultRequest.getCategoryId());
                result.setCategory(category);
            }

            if (resultRequest.getCriteriaId() != null) {
                Criteria criteria = new Criteria();
                criteria.setId(resultRequest.getCriteriaId());
                result.setCriteria(criteria);
            }

            if (resultRequest.getResult() != null) {
                if(resultRequest.getResult()<=100) {
                    result.setResult(resultRequest.getResult());
                }
            }

            if (resultRequest.getReason() != null) {
                result.setReason(resultRequest.getReason());
            }

            if (resultRequest.getSessionId() != null) {
                Session session =validationUtil.getSessionById(resultRequest.getSessionId());
                result.setSession(session);
            }

            if (resultRequest.getCategoryId() != null) {
                Category category=validationUtil.getCategoryById(resultRequest.getCategoryId());
                result.setCategory(category);
            }

            if (resultRequest.getSessionId() != null) {
                Criteria criteria =validationUtil.getCriteriaById(resultRequest.getCriteriaId());
                result.setCriteria(criteria);
            }

            if(tenantId!=null){
                result.setTenantId(tenantId);
            }

            result.setUpdatedAt(LocalDateTime.now());
            result.setUpdatedBy(null);
            return result;
        }
    }

