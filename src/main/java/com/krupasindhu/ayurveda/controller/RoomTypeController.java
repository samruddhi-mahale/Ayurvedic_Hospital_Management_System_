package com.krupasindhu.ayurveda.controller;

import com.krupasindhu.ayurveda.entity.RoomType;
import com.krupasindhu.ayurveda.exception.ResourceNotFoundException;
import com.krupasindhu.ayurveda.repository.RoomTypeRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room-types")
public class RoomTypeController {

    private final RoomTypeRepository repository;

    public RoomTypeController(RoomTypeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<RoomType> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public RoomType getById(@PathVariable Integer id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Room type not found: " + id));
    }

    @PostMapping
    public RoomType create(@Valid @RequestBody RoomType roomType) {
        return repository.save(roomType);
    }

    @PutMapping("/{id}")
    public RoomType update(@PathVariable Integer id, @Valid @RequestBody RoomType updated) {
        RoomType existing = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Room type not found: " + id));
        existing.setName(updated.getName());
        existing.setPerDayRate(updated.getPerDayRate());
        return repository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Room type not found: " + id);
        }
        repository.deleteById(id);
    }
}
