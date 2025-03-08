package com.saas.evaluation.event;

import com.saas.evaluation.data.ResourceData;
import com.saas.evaluation.dto.eventDto.ResourceEvent;
import com.saas.evaluation.dto.eventDto.ResourceStatusEvent;
import com.saas.evaluation.model.Resource;
import com.saas.evaluation.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class ResourceEventConsumer {

    private final ResourceData resourceData;
    private final ResourceRepository resourceRepository;

    @RabbitListener(queues = "${rabbitmq.create-evaluation-resource-queue}")
    public void consumeCreateResourceEvent(ResourceEvent event) {
        log.info("Received create resource event: {}", event);
        resourceData.saveResource(event);
    }

    @RabbitListener(queues = "${rabbitmq.delete-evaluation-resource-queue}")
    public void consumeDeleteTenantEvent(UUID tenantId) {
        log.info("Received delete resource event: {}", tenantId);
        List<Resource> resources = resourceRepository.findByTenantId(tenantId);
        resourceRepository.deleteAll(resources);
    }

    @RabbitListener(queues = "${rabbitmq.evaluation-resource-status-queue}")
    public void consumeResourceStatusEvent(ResourceStatusEvent event) {
        log.info("Received resource status event: {}", event);
        List<Resource> resources = resourceRepository.findByTenantId(event.getTenantId());
        List<Resource> updatedResources = new ArrayList<>();
        for (Resource resource : resources) {
            resource.setStatus(event.getStatus());
            updatedResources.add(resource);
        }
        resourceRepository.saveAll(updatedResources);
    }
}
