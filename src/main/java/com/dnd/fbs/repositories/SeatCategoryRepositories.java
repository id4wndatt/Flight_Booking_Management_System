package com.dnd.fbs.repositories;

import com.dnd.fbs.models.SeatCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SeatCategoryRepositories extends JpaRepository<SeatCategory,Integer> {
    public SeatCategory getSeatCategoryByCategoryName(String categoryName);
}
