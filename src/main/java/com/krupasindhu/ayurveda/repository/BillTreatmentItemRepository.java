package com.krupasindhu.ayurveda.repository;

import com.krupasindhu.ayurveda.entity.BillTreatmentItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillTreatmentItemRepository extends JpaRepository<BillTreatmentItem, Integer> {
    List<BillTreatmentItem> findByBillId(Integer billId);
}
