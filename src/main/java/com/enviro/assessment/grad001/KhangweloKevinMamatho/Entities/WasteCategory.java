package com.enviro.assessment.grad001.KhangweloKevinMamatho.Entities;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class WasteCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;

    @OneToMany(mappedBy = "wasteCategory", cascade = CascadeType.ALL)
    private List<DisposalGuideline> disposalGuidelines;

    @OneToMany(mappedBy = "wasteCategory", cascade = CascadeType.ALL)
    private List<RecyclingTips> recyclingTips;
    
    // Setting my Getters and setters for the WasteCategory Entity
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id =id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public List<DisposalGuideline> getDisposalGuidelines() {
        return disposalGuidelines;
    }

    public void setDisposalGuidelines(List<DisposalGuideline> disposalGuidelines) {
        this.disposalGuidelines = disposalGuidelines;
    }

    public List<RecyclingTips> getRecyclingTips() {
        return recyclingTips;
    }

    public void setRecyclingTips(List<RecyclingTips> recyclingTips) {
        this.recyclingTips = recyclingTips;
    }
   
}
