package com.krupasindhu.ayurveda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "treatment_types")
public class TreatmentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "default_rate")
    private Double defaultRate = 0.0;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getDefaultRate() { return defaultRate; }
    public void setDefaultRate(Double defaultRate) { this.defaultRate = defaultRate; }
}
