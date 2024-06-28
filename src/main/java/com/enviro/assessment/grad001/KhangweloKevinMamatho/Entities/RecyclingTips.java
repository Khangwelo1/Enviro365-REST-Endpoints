package com.enviro.assessment.grad001.KhangweloKevinMamatho.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class RecyclingTips {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tip is mandatory")
    private String tip;
    @ManyToOne
    @JoinColumn(name = "waste_category_id")
    private WasteCategory wasteCategory;


    // Setting my Getters and setters for the RecyclingTips Entity
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getTip(){
        return tip;
    }
    public void setTip(String tip){
        this.tip = tip;
    }
    public WasteCategory getWasteCategory() {
        return wasteCategory;
    }

    public void setWasteCategory(WasteCategory wasteCategory) {
        this.wasteCategory = wasteCategory;
    }
}