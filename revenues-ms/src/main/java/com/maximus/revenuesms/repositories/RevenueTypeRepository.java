package com.maximus.revenuesms.repositories;

import com.maximus.revenuesms.models.RevenueType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevenueTypeRepository extends JpaRepository<RevenueType, Long> {
}
