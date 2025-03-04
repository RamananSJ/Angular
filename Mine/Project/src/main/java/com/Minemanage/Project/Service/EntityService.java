package com.Minemanage.Project.Service;


import com.Minemanage.Project.Entity.Entity;
import com.Minemanage.Project.Repository.EntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntityService {
    private final EntityRepository entityRepository;

    private final SimpMessagingTemplate messagingTemplate;

    public EntityService(EntityRepository entityRepository, SimpMessagingTemplate messagingTemplate) {
        this.entityRepository = entityRepository;
        this.messagingTemplate = messagingTemplate;
    }

    public List<Entity> getAllEntities() {
        return entityRepository.findAll();
    }

    public Entity createEntity(Entity entity) {
        Entity savedEntity = entityRepository.save(entity);
        messagingTemplate.convertAndSend("/topic/entities", getAllEntities());
        return savedEntity;
    }

    public Entity updateEntity(Long id, Entity updatedEntity) {
        Entity entity = entityRepository.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
        entity.setName(updatedEntity.getName());
        entity.setType(updatedEntity.getType());
        entity.setStatus(updatedEntity.getStatus());
        entityRepository.save(entity);
        messagingTemplate.convertAndSend("/topic/entities", getAllEntities());
        return entity;
    }

    public void deleteEntity(Long id) {
        entityRepository.deleteById(id);
        messagingTemplate.convertAndSend("/topic/entities", getAllEntities());
    }
}
