package com.enviro.assessment.grad001.KhangweloKevinMamatho.Controllers;

import com.enviro.assessment.grad001.KhangweloKevinMamatho.Entities.WasteCategory;
import com.enviro.assessment.grad001.KhangweloKevinMamatho.Repo.WasteCategoryRepo;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mywastecategory")
public class WasteCategoryController {
    @Autowired
    private final WasteCategoryRepo wastecategoryrepo;

 
    public WasteCategoryController(WasteCategoryRepo wastecategoryrepo) {
        this.wastecategoryrepo = wastecategoryrepo;
    }

    @GetMapping("/gettingAllCategories")
    public ResponseEntity<?> getAllCategories() {
        List<WasteCategory> categories = wastecategoryrepo.findAll();
    if (categories.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No category found.");
    } else {
        return ResponseEntity.ok(categories);
    }
    }
    @GetMapping("/gettingCategoryById/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        Optional<WasteCategory> category = wastecategoryrepo.findById(id);
    if (category.isPresent()) {
        return ResponseEntity.ok(category.get());
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No guideline found with ID " + id);
    }
}

    @PostMapping("/creatingNewCategory")
    public ResponseEntity<WasteCategory> createCategory(@Valid @RequestBody WasteCategory category) {
        WasteCategory savedCategory = wastecategoryrepo.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @PutMapping("/updatingCategoryById/{id}")
    public ResponseEntity<WasteCategory> updateCategory(@PathVariable Long id, @Valid @RequestBody WasteCategory updatedCategory) {
        return wastecategoryrepo.findById(id)
                                .map(existingCategory -> {
                                    existingCategory.setName(updatedCategory.getName());
                                    WasteCategory updated = wastecategoryrepo.save(existingCategory);
                                    return ResponseEntity.ok(updated);
                                })
                                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deletingCategoryById/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        Optional<WasteCategory> category = wastecategoryrepo.findById(id);
        if (category.isPresent()) {
            wastecategoryrepo.delete(category.get());
            return ResponseEntity.status(HttpStatus.OK).body("ID " + id + " has been deleted from database.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("ID " + id + " is not found in the database.");
        }
    }
}
