package com.saas.evaluation.sevice;

import com.saas.evaluation.dto.request.LevelRequest;
import com.saas.evaluation.dto.response.LevelResponse;
import com.saas.evaluation.exception.ResourceNotFoundException;
import com.saas.evaluation.mapper.LevelMapper;
import com.saas.evaluation.model.Level;
import com.saas.evaluation.repository.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LevelService {
    private final LevelRepository levelRepository;
    private final LevelMapper levelMapper;

    public LevelResponse createLevel(UUID tenantId,
                                     LevelRequest levelRequest){

        Level level=levelMapper.mapToEntity(tenantId,levelRequest);
        Level savedLevel=levelRepository.save(level);
        return levelMapper.mapToDto(savedLevel);

    }

    public List<LevelResponse> getAllLevel(UUID tenantId){

           List<Level>levels=levelRepository.findByTenantId(tenantId);
        return  levels.stream()
                .map(levelMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public LevelResponse getLevelById(UUID tenantId,
                                      UUID id){

        Level level=this.getLevel(tenantId,id);
        return levelMapper.mapToDto(level);
    }

    public LevelResponse updateLevel(UUID tenantId,
                                     UUID id,
                                     LevelRequest levelRequest){

        Level level=this.getLevel(tenantId,id);
        Level mappedLevel=levelMapper.updateEntity(tenantId,level,levelRequest);
        Level savedLevel=levelRepository.save(mappedLevel);
        return levelMapper.mapToDto(savedLevel);
    }

    public String deleteLevel(UUID tenantId,
                              UUID id){

        Level level=this.getLevel(tenantId,id);
        levelRepository.delete(level);
        return "Level Deleted Successfully";
    }

    private Level getLevel(UUID tenantId,
                           UUID levelId){

        return levelRepository.findById(levelId)
                .filter(c -> c.getTenantId().equals(tenantId))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Level not found with id " + levelId));
    }
}
