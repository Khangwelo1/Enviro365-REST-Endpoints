package com.enviro.assessment.grad001.KhangweloKevinMamatho.Controllers;

import com.enviro.assessment.grad001.KhangweloKevinMamatho.Entities.RecyclingTips;
import com.enviro.assessment.grad001.KhangweloKevinMamatho.Repo.RecyclingTipsRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recycletips")
public class RecyclingTipsController {
    @Autowired
    private final RecyclingTipsRepo recyclingtiprepo;

   
    public RecyclingTipsController(RecyclingTipsRepo recyclingtiprepo) {
        this.recyclingtiprepo = recyclingtiprepo;
    }

    @GetMapping("/gettingAllTips")
    public ResponseEntity<?> getAllTips() {
        List<RecyclingTips> tip = recyclingtiprepo.findAll();
        if(tip.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Tips found.");
        }else {
            return ResponseEntity.ok(tip);
        }
    }

    @GetMapping("/gettingTipById/{id}")
    public ResponseEntity<?> getTipById(@PathVariable Long id) {
        Optional<RecyclingTips> tip = recyclingtiprepo.findById(id);
    if(tip.isPresent()){
        return ResponseEntity.ok(tip.get());
    }else{
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Tip found with ID " + id);
    }
}

    @PostMapping("/creatingNewTip")
    public ResponseEntity<RecyclingTips> createTip(@Valid @RequestBody RecyclingTips tip) {
        RecyclingTips savedTip = recyclingtiprepo.save(tip);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTip);
    }

    @PutMapping("/updatingTipById/{id}")
    public ResponseEntity<RecyclingTips> updateTip(@PathVariable Long id, @Valid @RequestBody RecyclingTips updatedTip) {
        return recyclingtiprepo.findById(id)
                              .map(existingTip -> {
                                  existingTip.setTip(updatedTip.getTip());
                                  RecyclingTips updated = recyclingtiprepo.save(existingTip);
                                  return ResponseEntity.ok(updated);
                              })
                              .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deletingTipById/{id}")
    public ResponseEntity<String> deleteTip(@PathVariable Long id) {
        Optional<RecyclingTips> existingTip = recyclingtiprepo.findById(id);
        if (existingTip.isPresent()) {
            recyclingtiprepo.delete(existingTip.get());
            return ResponseEntity.status(HttpStatus.OK).body("ID " + id + " has been deleted from database.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID " + id + " is not found in the database.");
        }
    }
    }
