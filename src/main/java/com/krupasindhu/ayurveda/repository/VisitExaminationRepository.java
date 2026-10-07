package com.krupasindhu.ayurveda.repository;

import com.krupasindhu.ayurveda.entity.VisitExamination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitExaminationRepository extends JpaRepository<VisitExamination, Integer> {
    List<VisitExamination> findByVisitId(Integer visitId);
}
