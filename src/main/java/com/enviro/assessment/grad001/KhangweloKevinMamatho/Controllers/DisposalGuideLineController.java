package com.enviro.assessment.grad001.KhangweloKevinMamatho.Controllers;

import com.enviro.assessment.grad001.KhangweloKevinMamatho.Entities.DisposalGuideline;
import com.enviro.assessment.grad001.KhangweloKevinMamatho.Repo.DisposalGuidelineRepo;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/guideline")
public class DisposalGuideLineController {
    @Autowired
    private final DisposalGuidelineRepo disposalGuidelineRepo;

    
    public DisposalGuideLineController(DisposalGuidelineRepo disposalGuidelineRepo) {
        this.disposalGuidelineRepo = disposalGuidelineRepo;
    }

    @GetMapping("/getAllGuidelines")
public ResponseEntity<?> getAllGuidelines() {
    List<DisposalGuideline> guidelines = disposalGuidelineRepo.findAll();
    if (guidelines.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No guidelines found.");
    } else {
        return ResponseEntity.ok(guidelines);
    }
}


@GetMapping("/getGuidelineById/{id}")
public ResponseEntity<?> getGuidelineById(@PathVariable Long id) {
    Optional<DisposalGuideline> guideline = disposalGuidelineRepo.findById(id);
    if (guideline.isPresent()) {
        return ResponseEntity.ok(guideline.get());
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No guideline found with ID " + id + ".");
    }
}


    @PostMapping("/createNewGuideline")
    public ResponseEntity<DisposalGuideline> createGuideline(@Valid @RequestBody DisposalGuideline guideline) {
        DisposalGuideline savedGuideline = disposalGuidelineRepo.save(guideline);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGuideline);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisposalGuideline> updateGuideline(@PathVariable Long id, @Valid @RequestBody DisposalGuideline updatedGuideline) {
        return disposalGuidelineRepo.findById(id)
                                    .map(existingGuideline -> {
                                        existingGuideline.setGuideline(updatedGuideline.getGuideline());
                                        existingGuideline.setWasteCategory(updatedGuideline.getWasteCategory());
                                        DisposalGuideline updated = disposalGuidelineRepo.save(existingGuideline);
                                        return ResponseEntity.ok(updated);
                                    })
                                    .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGuideline(@PathVariable Long id) {
        Optional<DisposalGuideline> guideline = disposalGuidelineRepo.findById(id);
        if (guideline.isPresent()) {
            disposalGuidelineRepo.delete(guideline.get());
            return ResponseEntity.status(HttpStatus.OK).body("ID " + id + " has been deleted from database.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID " + id + " is not found in the database.");
        }
    }
}
