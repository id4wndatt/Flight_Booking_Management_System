package com.dnd.fbs.repositories;

import com.dnd.fbs.models.AirlineCompany;
import com.dnd.fbs.models.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaneRepository extends JpaRepository<Plane, Integer> {
}
