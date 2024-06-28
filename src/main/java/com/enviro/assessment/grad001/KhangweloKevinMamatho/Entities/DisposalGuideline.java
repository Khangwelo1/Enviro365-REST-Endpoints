package com.enviro.assessment.grad001.KhangweloKevinMamatho.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class DisposalGuideline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Guideline is mandatory")
    private String guideline;

    @ManyToOne
    @JoinColumn(name = "waste_category_id")
    private WasteCategory wasteCategory;

     // Setting my Getters and setters for the DisposalGuideline Entity

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getGuideline(){
        return guideline;
    }
    public void setGuideline(String guideline){
        this.guideline = guideline;
    }
    public WasteCategory getWasteCategory() {
        return wasteCategory;
    }

    public void setWasteCategory(WasteCategory wasteCategory) {
        this.wasteCategory = wasteCategory;
    }
}
