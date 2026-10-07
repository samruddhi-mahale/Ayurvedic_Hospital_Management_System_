package com.krupasindhu.ayurveda.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

// Accepts the whole case paper in one request: header fields
// plus all 14 examination rows in a single payload.
public class VisitRequest {

    @NotNull
    private Integer patientId;

    private Integer doctorId;

    @NotNull
    private LocalDate visitDate;

    @Valid
    private List<ExaminationRow> examinations;

    public static class ExaminationRow {
        @NotNull
        private Integer parameterId;
        private String symptoms;
        private String treatmentNotes;

        public Integer getParameterId() { return parameterId; }
        public void setParameterId(Integer parameterId) { this.parameterId = parameterId; }

        public String getSymptoms() { return symptoms; }
        public void setSymptoms(String symptoms) { this.symptoms = symptoms; }

        public String getTreatmentNotes() { return treatmentNotes; }
        public void setTreatmentNotes(String treatmentNotes) { this.treatmentNotes = treatmentNotes; }
    }

    public Integer getPatientId() { return patientId; }
    public void setPatientId(Integer patientId) { this.patientId = patientId; }

    public Integer getDoctorId() { return doctorId; }
    public void setDoctorId(Integer doctorId) { this.doctorId = doctorId; }

    public LocalDate getVisitDate() { return visitDate; }
    public void setVisitDate(LocalDate visitDate) { this.visitDate = visitDate; }

    public List<ExaminationRow> getExaminations() { return examinations; }
    public void setExaminations(List<ExaminationRow> examinations) { this.examinations = examinations; }
}
