package com.idea.generator.controller;

import com.idea.generator.entity.Idea;
import com.idea.generator.service.IdeaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ideas")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://randomideagenerator-frontend.onrender.com")
public class IdeaController {

    private final IdeaService ideaService;

    @GetMapping
    public ResponseEntity<List<Idea>> getAllIdeas() {
        return ResponseEntity.ok(ideaService.getAllIdeas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Idea> getIdeaById(@PathVariable Long id) {
        return ideaService.getIdeaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/random")
    public ResponseEntity<Idea> getRandomIdea() {
        return ideaService.getRandomIdea()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Idea> createIdea(@RequestBody Idea idea) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ideaService.createIdea(idea));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Idea> updateIdea(@PathVariable Long id, @RequestBody Idea idea) {
        return ideaService.updateIdea(id, idea)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteIdea(@PathVariable Long id) {
        if (ideaService.deleteIdea(id)) {
            return ResponseEntity.ok(Map.of("message", "Idea deleted successfully"));
        }
        return ResponseEntity.notFound().build();
    }
}