package com.enviro.assessment.grad001.KhangweloKevinMamatho.Services;

import com.enviro.assessment.grad001.KhangweloKevinMamatho.Entities.RecyclingTips;
import com.enviro.assessment.grad001.KhangweloKevinMamatho.Repo.RecyclingTipsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecyclingTipsService {

    private final RecyclingTipsRepo recyclingTipsRepo;

    @Autowired
    public RecyclingTipsService(RecyclingTipsRepo recyclingTipsRepo) {
        this.recyclingTipsRepo = recyclingTipsRepo;
    }

    public List<RecyclingTips> getAllTips() {
        return recyclingTipsRepo.findAll();
    }

    public Optional<RecyclingTips> getTipById(Long id) {
        return recyclingTipsRepo.findById(id);
    }

    public RecyclingTips createTip(RecyclingTips tip) {
        return recyclingTipsRepo.save(tip);
    }

    public Optional<RecyclingTips> updateTip(Long id, RecyclingTips updatedTip) {
        return recyclingTipsRepo.findById(id)
                .map(existingTip -> {
                    existingTip.setTip(updatedTip.getTip());
                    return recyclingTipsRepo.save(existingTip);
                });
    }

    public void deleteTip(Long id) {
        recyclingTipsRepo.findById(id).ifPresent(recyclingTipsRepo::delete);
    }
}
