package com.krupasindhu.ayurveda.repository;

import com.krupasindhu.ayurveda.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
