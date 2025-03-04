package com.Minemanage.Project.Controller;

import com.Minemanage.Project.Entity.Entity;
import com.Minemanage.Project.Service.EntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entities")
public class EntityController {

    private final EntityService entityService;

    public EntityController(EntityService entityService){
        this.entityService = entityService;
    }
    @GetMapping
    public ResponseEntity<List<Entity>> getAllEntities() {
        return ResponseEntity.ok(entityService.getAllEntities());
    }

    @PostMapping
    public ResponseEntity<Entity> createEntity(@RequestBody Entity entity) {
        return ResponseEntity.ok(entityService.createEntity(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entity> updateEntity(@PathVariable Long id, @RequestBody Entity entity) {
        return ResponseEntity.ok(entityService.updateEntity(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable Long id) {
        entityService.deleteEntity(id);
        return ResponseEntity.noContent().build();
    }
}
