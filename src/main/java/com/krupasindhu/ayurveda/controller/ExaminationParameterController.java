package com.krupasindhu.ayurveda.controller;

import com.krupasindhu.ayurveda.entity.ExaminationParameter;
import com.krupasindhu.ayurveda.exception.ResourceNotFoundException;
import com.krupasindhu.ayurveda.repository.ExaminationParameterRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/examination-parameters")
public class ExaminationParameterController {

    private final ExaminationParameterRepository repository;

    public ExaminationParameterController(ExaminationParameterRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<ExaminationParameter> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ExaminationParameter getById(@PathVariable Integer id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Examination parameter not found: " + id));
    }

    @PostMapping
    public ExaminationParameter create(@Valid @RequestBody ExaminationParameter parameter) {
        return repository.save(parameter);
    }

    @PutMapping("/{id}")
    public ExaminationParameter update(@PathVariable Integer id, @Valid @RequestBody ExaminationParameter updated) {
        ExaminationParameter existing = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Examination parameter not found: " + id));
        existing.setName(updated.getName());
        return repository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Examination parameter not found: " + id);
        }
        repository.deleteById(id);
    }
}
