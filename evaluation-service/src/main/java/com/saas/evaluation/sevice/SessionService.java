package com.saas.evaluation.sevice;

import com.saas.evaluation.dto.request.SessionRequest;
import com.saas.evaluation.dto.response.SessionResponse;
import com.saas.evaluation.exception.ResourceNotFoundException;
import com.saas.evaluation.mapper.SessionMapper;
import com.saas.evaluation.model.Session;
import com.saas.evaluation.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;

    public SessionResponse createSession(UUID tenantId,
                                         SessionRequest sessionRequest){

        Session session= sessionMapper.mapToEntity(tenantId,sessionRequest);
        Session savedSession =sessionRepository.save(session);
        return sessionMapper.mapToDto(savedSession);
    }

    public List<SessionResponse> getAllSession(UUID tenantId){

        List<Session> sessions= sessionRepository.findByTenantId(tenantId);
        return  sessions.stream()
                        .map(sessionMapper::mapToDto)
                        .collect(Collectors.toList());

    }

    public SessionResponse getSessionById(UUID tenantId,
                                          UUID id){
        Session session=this.getSession(tenantId,id);
        return sessionMapper.mapToDto(session);
    }


    public SessionResponse updateSession(UUID tenantId,
                                         UUID id,
                                         SessionRequest sessionRequest){

        Session session=this.getSession(tenantId,id);
        Session mappedSession=sessionMapper.updateEntity(tenantId,session,sessionRequest);
        Session savedSession=sessionRepository.save(mappedSession);
        return  sessionMapper.mapToDto(savedSession);
    }

    public  String deleteSession(UUID tenantId,UUID id){
        Session session=this.getSession(tenantId,id);
        sessionRepository.delete(session);
        return "Session Deleted Successfully";
    }

    private Session getSession(UUID tenantId,
                               UUID sessionId){

        return sessionRepository.findById(sessionId)
                .filter(c -> c.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Session not found with id " + sessionId));
    }
}
