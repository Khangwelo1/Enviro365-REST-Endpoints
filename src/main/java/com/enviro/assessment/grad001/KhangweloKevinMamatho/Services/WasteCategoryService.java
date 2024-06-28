package com.enviro.assessment.grad001.KhangweloKevinMamatho.Services;

import com.enviro.assessment.grad001.KhangweloKevinMamatho.Entities.WasteCategory;
import com.enviro.assessment.grad001.KhangweloKevinMamatho.Repo.WasteCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WasteCategoryService {

    private final WasteCategoryRepo wasteCategoryRepo;

    @Autowired
    public WasteCategoryService(WasteCategoryRepo wasteCategoryRepo) {
        this.wasteCategoryRepo = wasteCategoryRepo;
    }

    public List<WasteCategory> getAllCategories() {
        return wasteCategoryRepo.findAll();
    }

    public Optional<WasteCategory> getCategoryById(Long id) {
        return wasteCategoryRepo.findById(id);
    }

    public WasteCategory createCategory(WasteCategory category) {
        return wasteCategoryRepo.save(category);
    }

    public Optional<WasteCategory> updateCategory(Long id, WasteCategory updatedCategory) {
        return wasteCategoryRepo.findById(id)
                .map(existingCategory -> {
                    existingCategory.setName(updatedCategory.getName());
                    return wasteCategoryRepo.save(existingCategory);
                });
    }

    public void deleteCategory(Long id) {
        wasteCategoryRepo.findById(id).ifPresent(wasteCategoryRepo::delete);
    }
}
