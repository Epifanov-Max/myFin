package com.maximus.revenuesms.services;

import com.maximus.revenuesms.models.RevenueType;
import com.maximus.revenuesms.repositories.RevenueTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RevenueTypeService {

    private final RevenueTypeRepository revenueTypeRepo;

    public List<RevenueType> getAllRevenueTypes(){
        return revenueTypeRepo.findAll();
    }

    public RevenueType getRevenueTypeById(Long id) {
        Optional<RevenueType> optRevenueType = revenueTypeRepo.findById(id);
        return optRevenueType.orElse(null);
    }

    public RevenueType addRevenueType(RevenueType revenueType) {

        if (revenueTypeRepo.findAll().stream()
                .noneMatch(item -> item.getName().equals(revenueType.getName()))) {
            return revenueTypeRepo.save(revenueType);
        }
        return null;
    }

    public RevenueType updateRevenueType(Long id, RevenueType revenueTypeDetails) {
        Optional<RevenueType> optionalRevenueType = revenueTypeRepo.findById(id);
        if (optionalRevenueType.isPresent()) {
            RevenueType revenueType = optionalRevenueType.get();
            revenueType.setName(revenueTypeDetails.getName());
            revenueType.setNote((revenueType.getNote()));
//
//            revenueType.setIdExp(revenueTypeDetails.getIdExp());
            return revenueTypeRepo.save(revenueType);
        } else {
            throw new IllegalArgumentException("Тип доходов с id" + id + "не найден");
        }
    }

    public void deleteRevenueType(Long id) {
        revenueTypeRepo.deleteById(id);
    }



}
