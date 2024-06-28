package com.enviro.assessment.grad001.KhangweloKevinMamatho.Services;

import com.enviro.assessment.grad001.KhangweloKevinMamatho.Entities.DisposalGuideline;
import com.enviro.assessment.grad001.KhangweloKevinMamatho.Repo.DisposalGuidelineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisposalGuidelineService {

    private final DisposalGuidelineRepo disposalGuidelineRepo;

    @Autowired
    public DisposalGuidelineService(DisposalGuidelineRepo disposalGuidelineRepo) {
        this.disposalGuidelineRepo = disposalGuidelineRepo;
    }

    public List<DisposalGuideline> getAllGuidelines() {
        return disposalGuidelineRepo.findAll();
    }

    public Optional<DisposalGuideline> getGuidelineById(Long id) {
        return disposalGuidelineRepo.findById(id);
    }

    public DisposalGuideline createGuideline(DisposalGuideline guideline) {
        return disposalGuidelineRepo.save(guideline);
    }

    public Optional<DisposalGuideline> updateGuideline(Long id, DisposalGuideline updatedGuideline) {
        return disposalGuidelineRepo.findById(id)
                .map(existingGuideline -> {
                    existingGuideline.setGuideline(updatedGuideline.getGuideline());
                    existingGuideline.setWasteCategory(updatedGuideline.getWasteCategory());
                    return disposalGuidelineRepo.save(existingGuideline);
                });
    }

    public void deleteGuideline(Long id) {
        disposalGuidelineRepo.findById(id).ifPresent(disposalGuidelineRepo::delete);
    }
}
