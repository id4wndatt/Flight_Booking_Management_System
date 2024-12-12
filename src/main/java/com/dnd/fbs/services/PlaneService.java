package com.dnd.fbs.services;

import com.dnd.fbs.models.Plane;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PlaneService {
    Page<Plane> findAll(int pageNo, int pageSize, String sortBy, String sortDir);
    List<Plane> findAll();
    Plane save(Plane plane);
    Optional<Plane> findById(int id);
    void deleteById(int id);
}
