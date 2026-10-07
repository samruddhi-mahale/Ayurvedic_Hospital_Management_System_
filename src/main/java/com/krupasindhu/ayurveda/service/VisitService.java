package com.krupasindhu.ayurveda.service;

import com.krupasindhu.ayurveda.dto.VisitRequest;
import com.krupasindhu.ayurveda.entity.*;
import com.krupasindhu.ayurveda.exception.ResourceNotFoundException;
import com.krupasindhu.ayurveda.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class VisitService {

    private final VisitRepository visitRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final ExaminationParameterRepository parameterRepository;

    public VisitService(VisitRepository visitRepository,
                         PatientRepository patientRepository,
                         DoctorRepository doctorRepository,
                         ExaminationParameterRepository parameterRepository) {
        this.visitRepository = visitRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.parameterRepository = parameterRepository;
    }

    @Transactional
    public Visit createVisit(VisitRequest request) {
        Patient patient = patientRepository.findById(request.getPatientId())
            .orElseThrow(() -> new ResourceNotFoundException("Patient not found: " + request.getPatientId()));

        Doctor doctor = null;
        if (request.getDoctorId() != null) {
            doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found: " + request.getDoctorId()));
        }

        Visit visit = new Visit();
        visit.setPatient(patient);
        visit.setDoctor(doctor);
        visit.setVisitDate(request.getVisitDate());

        List<VisitExamination> examinations = new ArrayList<>();
        if (request.getExaminations() != null) {
            for (VisitRequest.ExaminationRow row : request.getExaminations()) {
                ExaminationParameter parameter = parameterRepository.findById(row.getParameterId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                        "Examination parameter not found: " + row.getParameterId()));

                VisitExamination examination = new VisitExamination();
                examination.setVisit(visit);
                examination.setParameter(parameter);
                examination.setSymptoms(row.getSymptoms());
                examination.setTreatmentNotes(row.getTreatmentNotes());
                examinations.add(examination);
            }
        }
        visit.setExaminations(examinations);

        return visitRepository.save(visit);
    }

    public Visit getVisit(Integer id) {
        return visitRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Visit not found: " + id));
    }

    public List<Visit> getAllVisits() {
        return visitRepository.findAll();
    }

    public List<Visit> getVisitsByPatient(Integer patientId) {
        return visitRepository.findByPatientId(patientId);
    }

    public void deleteVisit(Integer id) {
        if (!visitRepository.existsById(id)) {
            throw new ResourceNotFoundException("Visit not found: " + id);
        }
        visitRepository.deleteById(id);
    }
}
