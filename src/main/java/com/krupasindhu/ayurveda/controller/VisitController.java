package com.krupasindhu.ayurveda.controller;

import com.krupasindhu.ayurveda.dto.VisitRequest;
import com.krupasindhu.ayurveda.entity.Visit;
import com.krupasindhu.ayurveda.service.VisitService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visits")
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping
    public List<Visit> getAll() {
        return visitService.getAllVisits();
    }

    @GetMapping("/{id}")
    public Visit getById(@PathVariable Integer id) {
        return visitService.getVisit(id);
    }

    @GetMapping("/patient/{patientId}")
    public List<Visit> getByPatient(@PathVariable Integer patientId) {
        return visitService.getVisitsByPatient(patientId);
    }

    @PostMapping
    public Visit create(@Valid @RequestBody VisitRequest request) {
        return visitService.createVisit(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        visitService.deleteVisit(id);
    }
}
