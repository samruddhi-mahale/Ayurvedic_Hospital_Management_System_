package com.krupasindhu.ayurveda.repository;

import com.krupasindhu.ayurveda.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
