package com.krupasindhu.ayurveda.controller;

import com.krupasindhu.ayurveda.entity.Doctor;
import com.krupasindhu.ayurveda.exception.ResourceNotFoundException;
import com.krupasindhu.ayurveda.repository.DoctorRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorRepository repository;

    public DoctorController(DoctorRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Doctor> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Doctor getById(@PathVariable Integer id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Doctor not found: " + id));
    }

    @PostMapping
    public Doctor create(@Valid @RequestBody Doctor doctor) {
        return repository.save(doctor);
    }

    @PutMapping("/{id}")
    public Doctor update(@PathVariable Integer id, @Valid @RequestBody Doctor updated) {
        Doctor existing = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Doctor not found: " + id));
        existing.setName(updated.getName());
        existing.setSpecialization(updated.getSpecialization());
        existing.setContactNumber(updated.getContactNumber());
        return repository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Doctor not found: " + id);
        }
        repository.deleteById(id);
    }
}
