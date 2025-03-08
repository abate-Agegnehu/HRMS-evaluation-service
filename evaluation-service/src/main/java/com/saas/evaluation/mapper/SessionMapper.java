package com.saas.evaluation.mapper;

import com.saas.evaluation.client.LeaveServiceClient;
import com.saas.evaluation.dto.clientDto.BudgetYearDto;
import com.saas.evaluation.dto.request.SessionRequest;
import com.saas.evaluation.dto.response.SessionResponse;
import com.saas.evaluation.model.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SessionMapper {

  private final LeaveServiceClient leaveServiceClient;

    public Session mapToEntity(
            UUID tenantId,
            SessionRequest sessionRequest){

        BudgetYearDto budgetYearDto=leaveServiceClient.getBudgetYearById(tenantId,sessionRequest.getBudgetYearId());

        Session session=new Session();
        session.setBudgetYearId(budgetYearDto.getId());

        session.setTerm(sessionRequest.getTerm());
        session.setStartDate(sessionRequest.getStartDate());
        session.setEndDate(sessionRequest.getEndDate());
        session.setRemark(sessionRequest.getRemark());
        session.setCreatedAt(LocalDateTime.now());
        session.setCreatedBy(null);
        session.setTenantId(tenantId);
        return session;
    }

    public SessionResponse mapToDto(Session session){

        SessionResponse sessionResponse = new SessionResponse();

        sessionResponse.setId(session.getId());
        sessionResponse.setBudgetYearId(session.getBudgetYearId());
        sessionResponse.setTerm(session.getTerm().name());
        sessionResponse.setStartDate(session.getStartDate());
        sessionResponse.setEndDate(session.getEndDate());
        sessionResponse.setRemark(session.getRemark());

        sessionResponse.setCreatedAt(session.getCreatedAt());
        sessionResponse.setCreatedBy(session.getCreatedBy());
        sessionResponse.setUpdatedAt(session.getUpdatedAt());
        sessionResponse.setUpdatedBy(session.getUpdatedBy());
        sessionResponse.setTenantId(session.getTenantId());

        return sessionResponse;
    }
    public Session updateEntity(
            UUID tenantId,
            Session session,
            SessionRequest sessionRequest) {

        if (sessionRequest.getBudgetYearId() != null) {

            BudgetYearDto budgetYearDto=leaveServiceClient.getBudgetYearById(tenantId,sessionRequest.getBudgetYearId());
            session.setBudgetYearId(budgetYearDto.getId());
        }

        if (sessionRequest.getTerm() != null) {
            session.setTerm(sessionRequest.getTerm());
        }
        if (sessionRequest.getStartDate() != null) {
            session.setStartDate(sessionRequest.getStartDate());
        }
        if (sessionRequest.getEndDate() != null) {
            session.setEndDate(sessionRequest.getEndDate());
        }
        if (sessionRequest.getRemark() != null) {
            session.setRemark(sessionRequest.getRemark());
        }
        if(tenantId!=null){
            session.setTenantId(tenantId);
        }
        session.setUpdatedAt(LocalDateTime.now());
        session.setUpdatedBy(null);
        return session;
    }
}
