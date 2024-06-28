package com.enviro.assessment.grad001.KhangweloKevinMamatho.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enviro.assessment.grad001.KhangweloKevinMamatho.Entities.WasteCategory;

public interface WasteCategoryRepo extends JpaRepository<WasteCategory, Long> {
    
}