package com.saas.evaluation.mapper;

import com.saas.evaluation.dto.request.LevelRequest;
import com.saas.evaluation.dto.response.LevelResponse;
import com.saas.evaluation.model.Level;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class LevelMapper {

    public Level mapToEntity(
            UUID tenantId,
            LevelRequest levelRequest){

        Level level=new Level();
        level.setLevel(levelRequest.getLevel());
        level.setMinPoint(levelRequest.getMinPoint());
        level.setMaxValue(levelRequest.getMaxValue());
        level.setDescription(levelRequest.getDescription());
        level.setStatus(levelRequest.getStatus());
        level.setCreatedAt(LocalDateTime.now());
        level.setCreatedBy(null);
        level.setTenantId(tenantId);

        return level;
    }

    public LevelResponse mapToDto(Level level){

        LevelResponse levelResponse = new LevelResponse();
        levelResponse.setId(level.getId());
        levelResponse.setLevel(level.getLevel());
        levelResponse.setMinPoint(level.getMinPoint());
        levelResponse.setMaxValue(level.getMaxValue());
        levelResponse.setDescription(level.getDescription());
        levelResponse.setStatus(level.getStatus().name());
        levelResponse.setCreatedAt(level.getCreatedAt());
        levelResponse.setCreatedBy(level.getCreatedBy());
        levelResponse.setUpdatedAt(level.getUpdatedAt());
        levelResponse.setUpdatedBy(level.getUpdatedBy());
        levelResponse.setTenantId(level.getTenantId());
        return levelResponse;
    }
    public Level updateEntity(
            UUID tenantId,
            Level level,
            LevelRequest levelRequest) {

        if (levelRequest.getLevel() != null) {
            level.setLevel(levelRequest.getLevel());
        }

        if (levelRequest.getMinPoint() != null) {
            level.setMinPoint(levelRequest.getMinPoint());
        }
        if (levelRequest.getMaxValue() != null) {
            level.setMaxValue(levelRequest.getMaxValue());
        }
        if (levelRequest.getDescription() != null) {
            level.setDescription(levelRequest.getDescription());
        }
        if (levelRequest.getStatus() != null) {
            level.setStatus(levelRequest.getStatus());
        }
        if(tenantId != null){
            level.setTenantId(tenantId);
        }

        level.setUpdatedAt(LocalDateTime.now());
        level.setUpdatedBy(null);
        return level;
    }
}
