package com.krupasindhu.ayurveda.controller;

import com.krupasindhu.ayurveda.entity.Patient;
import com.krupasindhu.ayurveda.exception.ResourceNotFoundException;
import com.krupasindhu.ayurveda.repository.PatientRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientRepository repository;

    public PatientController(PatientRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Patient> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Patient getById(@PathVariable Integer id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Patient not found: " + id));
    }

    @PostMapping
    public Patient create(@Valid @RequestBody Patient patient) {
        return repository.save(patient);
    }

    @PutMapping("/{id}")
    public Patient update(@PathVariable Integer id, @Valid @RequestBody Patient updated) {
        Patient existing = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Patient not found: " + id));
        existing.setName(updated.getName());
        existing.setGender(updated.getGender());
        existing.setAge(updated.getAge());
        existing.setWeightKg(updated.getWeightKg());
        existing.setHeightCm(updated.getHeightCm());
        existing.setPhone(updated.getPhone());
        existing.setAddress(updated.getAddress());
        return repository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Patient not found: " + id);
        }
        repository.deleteById(id);
    }
}
