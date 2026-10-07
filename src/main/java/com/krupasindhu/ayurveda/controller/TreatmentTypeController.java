package com.krupasindhu.ayurveda.controller;

import com.krupasindhu.ayurveda.entity.TreatmentType;
import com.krupasindhu.ayurveda.exception.ResourceNotFoundException;
import com.krupasindhu.ayurveda.repository.TreatmentTypeRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/treatment-types")
public class TreatmentTypeController {

    private final TreatmentTypeRepository repository;

    public TreatmentTypeController(TreatmentTypeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<TreatmentType> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public TreatmentType getById(@PathVariable Integer id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Treatment type not found: " + id));
    }

    @PostMapping
    public TreatmentType create(@Valid @RequestBody TreatmentType treatmentType) {
        return repository.save(treatmentType);
    }

    @PutMapping("/{id}")
    public TreatmentType update(@PathVariable Integer id, @Valid @RequestBody TreatmentType updated) {
        TreatmentType existing = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Treatment type not found: " + id));
        existing.setName(updated.getName());
        existing.setDefaultRate(updated.getDefaultRate());
        return repository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Treatment type not found: " + id);
        }
        repository.deleteById(id);
    }
}
