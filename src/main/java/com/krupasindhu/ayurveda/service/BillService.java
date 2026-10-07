package com.krupasindhu.ayurveda.service;

import com.krupasindhu.ayurveda.dto.BillRequest;
import com.krupasindhu.ayurveda.entity.*;
import com.krupasindhu.ayurveda.exception.ResourceNotFoundException;
import com.krupasindhu.ayurveda.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillService {

    private final BillRepository billRepository;
    private final PatientRepository patientRepository;
    private final VisitRepository visitRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final TreatmentTypeRepository treatmentTypeRepository;

    public BillService(BillRepository billRepository,
                        PatientRepository patientRepository,
                        VisitRepository visitRepository,
                        RoomTypeRepository roomTypeRepository,
                        TreatmentTypeRepository treatmentTypeRepository) {
        this.billRepository = billRepository;
        this.patientRepository = patientRepository;
        this.visitRepository = visitRepository;
        this.roomTypeRepository = roomTypeRepository;
        this.treatmentTypeRepository = treatmentTypeRepository;
    }

    @Transactional
    public Bill createBill(BillRequest request) {
        Patient patient = patientRepository.findById(request.getPatientId())
            .orElseThrow(() -> new ResourceNotFoundException("Patient not found: " + request.getPatientId()));

        Visit visit = null;
        if (request.getVisitId() != null) {
            visit = visitRepository.findById(request.getVisitId())
                .orElseThrow(() -> new ResourceNotFoundException("Visit not found: " + request.getVisitId()));
        }

        RoomType roomType = null;
        if (request.getRoomTypeId() != null) {
            roomType = roomTypeRepository.findById(request.getRoomTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Room type not found: " + request.getRoomTypeId()));
        }

        Bill bill = new Bill();
        bill.setPatient(patient);
        bill.setVisit(visit);
        bill.setRoomType(roomType);
        bill.setBillDate(request.getBillDate());
        bill.setFoodCharge(request.getFoodCharge() != null ? request.getFoodCharge() : 0.0);

        List<BillTreatmentItem> items = new ArrayList<>();
        double itemsTotal = 0.0;

        if (request.getItems() != null) {
            for (BillRequest.TreatmentItem row : request.getItems()) {
                TreatmentType treatmentType = treatmentTypeRepository.findById(row.getTreatmentTypeId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                        "Treatment type not found: " + row.getTreatmentTypeId()));

                BillTreatmentItem item = new BillTreatmentItem();
                item.setBill(bill);
                item.setTreatmentType(treatmentType);
                item.setDays(row.getDays() != null ? row.getDays() : 0);
                item.setAmount(row.getAmount() != null ? row.getAmount() : 0.0);
                items.add(item);

                itemsTotal += item.getAmount();
            }
        }
        bill.setTreatmentItems(items);

        // Business rule: total = sum of treatment line items + food charge.
        // (Room charge is tracked via roomType for reference; add a stay-days
        // field to Bill if you need to bill room nights separately.)
        bill.setTotalAmount(itemsTotal + bill.getFoodCharge());

        return billRepository.save(bill);
    }

    public Bill getBill(Integer id) {
        return billRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Bill not found: " + id));
    }

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    public List<Bill> getBillsByPatient(Integer patientId) {
        return billRepository.findByPatientId(patientId);
    }

    public void deleteBill(Integer id) {
        if (!billRepository.existsById(id)) {
            throw new ResourceNotFoundException("Bill not found: " + id);
        }
        billRepository.deleteById(id);
    }
}
