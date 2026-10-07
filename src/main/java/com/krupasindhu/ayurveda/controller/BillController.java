package com.krupasindhu.ayurveda.controller;

import com.krupasindhu.ayurveda.dto.BillRequest;
import com.krupasindhu.ayurveda.entity.Bill;
import com.krupasindhu.ayurveda.service.BillService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping
    public List<Bill> getAll() {
        return billService.getAllBills();
    }

    @GetMapping("/{id}")
    public Bill getById(@PathVariable Integer id) {
        return billService.getBill(id);
    }

    @GetMapping("/patient/{patientId}")
    public List<Bill> getByPatient(@PathVariable Integer patientId) {
        return billService.getBillsByPatient(patientId);
    }

    @PostMapping
    public Bill create(@Valid @RequestBody BillRequest request) {
        return billService.createBill(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        billService.deleteBill(id);
    }
}
