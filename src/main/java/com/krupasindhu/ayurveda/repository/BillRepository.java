package com.krupasindhu.ayurveda.repository;

import com.krupasindhu.ayurveda.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Integer> {
    List<Bill> findByPatientId(Integer patientId);
}
