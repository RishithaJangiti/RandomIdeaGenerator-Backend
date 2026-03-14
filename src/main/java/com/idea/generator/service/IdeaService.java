package com.idea.generator.service;

import com.idea.generator.entity.Idea;
import com.idea.generator.repository.IdeaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class IdeaService {

    private final IdeaRepository ideaRepository;

    public List<Idea> getAllIdeas() {
        return ideaRepository.findAll();
    }

    public Optional<Idea> getIdeaById(Long id) {
        return ideaRepository.findById(id);
    }

    public Optional<Idea> getRandomIdea() {
        List<Idea> all = ideaRepository.findAll();
        if (all.isEmpty()) return Optional.empty();
        return Optional.of(all.get(new Random().nextInt(all.size())));
    }

    public Idea createIdea(Idea idea) {
        return ideaRepository.save(idea);
    }

    public Optional<Idea> updateIdea(Long id, Idea updated) {
        return ideaRepository.findById(id).map(existing -> {
            existing.setTitle(updated.getTitle());
            existing.setDescription(updated.getDescription());
            existing.setCategory(updated.getCategory());
            return ideaRepository.save(existing);
        });
    }

    public boolean deleteIdea(Long id) {
        if (ideaRepository.existsById(id)) {
            ideaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}