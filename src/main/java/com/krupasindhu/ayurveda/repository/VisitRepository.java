package com.krupasindhu.ayurveda.repository;

import com.krupasindhu.ayurveda.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Integer> {
    List<Visit> findByPatientId(Integer patientId);
}
