package com.saas.evaluation.sevice;

import com.saas.evaluation.dto.request.ResultRequest;
import com.saas.evaluation.dto.response.ResultResponse;
import com.saas.evaluation.exception.ResourceNotFoundException;
import com.saas.evaluation.mapper.ResultMapper;
import com.saas.evaluation.model.Result;
import com.saas.evaluation.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResultService {
    private  final ResultRepository resultRepository;
    private final ResultMapper resultMapper;

    public ResultResponse createResult(UUID tenantId,
                                       ResultRequest resultRequest){

        Result result=resultMapper.mapToEntity(tenantId, resultRequest);
        Result savedResult=resultRepository.save(result);
        return  resultMapper.mapToDto(savedResult);
    }

    public List<ResultResponse> getAllResult(UUID tenantId){

          List<Result>results=resultRepository.findByTenantId(tenantId);
        return results.stream()
                .map(resultMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public ResultResponse getResultById(UUID tenantId,
                                        UUID id){

        Result result=this.getResult(tenantId,id);
        return resultMapper.mapToDto(result);
    }

    public ResultResponse updateResultById(UUID tenantId,
                                           UUID id,
                                           ResultRequest resultRequest){

        Result result = this.getResult(tenantId,id);
        Result mappedResult= resultMapper.updateEntity(tenantId,result,resultRequest);
        Result savedResult=resultRepository.save(mappedResult);
        return resultMapper.mapToDto(savedResult);
    }

    public String deleteResult(UUID tenantId,
                               UUID id){

        Result result=this.getResult(tenantId,id);
        resultRepository.delete(result);
        return "Result Deleted Successfully";
    }

    private Result getResult(UUID tenantId,
                             UUID resultId){

        return resultRepository.findById(resultId)
                .filter(c -> c.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Result not found with id " + resultId));
    }

}
