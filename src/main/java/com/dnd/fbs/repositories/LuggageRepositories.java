package com.dnd.fbs.repositories;

import com.dnd.fbs.models.Luggage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface LuggageRepositories extends JpaRepository<Luggage,Integer> {
    @Query(value = "select * from Luggage l",nativeQuery = true)
    public ArrayList<Luggage> findAll();

    public Luggage getLuggagesByLuggageID(int id);
}
