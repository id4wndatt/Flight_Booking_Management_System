package com.dnd.fbs.repositories;

import com.dnd.fbs.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

    @Query("SELECT f FROM Flight f WHERE " +
            "f.departing_from LIKE CONCAT('%',:query, '%') " +
            "Or f.arriving_at LIKE CONCAT('%',:query, '%')")
    List<Flight> searchFlights(String query);
}
