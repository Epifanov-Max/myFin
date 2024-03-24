package com.maximus.revenuesms.controllers;

import com.maximus.revenuesms.models.RevenueType;
import com.maximus.revenuesms.services.RevenueTypeService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/revenue-types")
public class RevenueTypeController {

    private final RevenueTypeService revenueTypeService;

    @GetMapping
    public List<RevenueType> getAllRevenueTypes() {
        return revenueTypeService.getAllRevenueTypes();
    }

    @GetMapping("/{id}")
    public RevenueType getRevenueTypeByID(@PathVariable("id") Long id) {
        return revenueTypeService.getRevenueTypeById(id);
    }

    @PostMapping
    public RevenueType addRevenueType(@RequestBody RevenueType revenueType) {
        return revenueTypeService.addRevenueType(revenueType);
    }

    @PutMapping("/{id}")
    public RevenueType updateRevenueType(@PathVariable Long id, @RequestBody RevenueType revenueType) {
        return revenueTypeService.updateRevenueType(id, revenueType);
    }

    @DeleteMapping("/{id}")
    public void deleteRevenueType(@PathVariable Long id) {
        revenueTypeService.deleteRevenueType(id);
    }

}