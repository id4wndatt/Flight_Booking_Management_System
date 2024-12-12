package com.dnd.fbs.repositories;

import com.dnd.fbs.models.SeatCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatCategoryRepository extends JpaRepository<SeatCategory, Integer> {
}
