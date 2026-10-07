package com.krupasindhu.ayurveda.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

// Accepts the whole billing slip in one request: header fields
// (room, food charge) plus all treatment line items.
public class BillRequest {

    @NotNull
    private Integer patientId;

    private Integer visitId;

    private Integer roomTypeId;

    @NotNull
    private LocalDate billDate;

    private Double foodCharge = 0.0;

    @Valid
    private List<TreatmentItem> items;

    public static class TreatmentItem {
        @NotNull
        private Integer treatmentTypeId;
        private Integer days = 0;
        private Double amount = 0.0;

        public Integer getTreatmentTypeId() { return treatmentTypeId; }
        public void setTreatmentTypeId(Integer treatmentTypeId) { this.treatmentTypeId = treatmentTypeId; }

        public Integer getDays() { return days; }
        public void setDays(Integer days) { this.days = days; }

        public Double getAmount() { return amount; }
        public void setAmount(Double amount) { this.amount = amount; }
    }

    public Integer getPatientId() { return patientId; }
    public void setPatientId(Integer patientId) { this.patientId = patientId; }

    public Integer getVisitId() { return visitId; }
    public void setVisitId(Integer visitId) { this.visitId = visitId; }

    public Integer getRoomTypeId() { return roomTypeId; }
    public void setRoomTypeId(Integer roomTypeId) { this.roomTypeId = roomTypeId; }

    public LocalDate getBillDate() { return billDate; }
    public void setBillDate(LocalDate billDate) { this.billDate = billDate; }

    public Double getFoodCharge() { return foodCharge; }
    public void setFoodCharge(Double foodCharge) { this.foodCharge = foodCharge; }

    public List<TreatmentItem> getItems() { return items; }
    public void setItems(List<TreatmentItem> items) { this.items = items; }
}
