package com.dnd.fbs.services;

import com.dnd.fbs.models.Plane;
import com.dnd.fbs.repositories.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaneServiceImpl implements PlaneService {
    @Autowired
    private PlaneRepository planeRepository;

    @Autowired
    public PlaneServiceImpl(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

    @Override
    public Page<Plane> findAll(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        Page<Plane> planes = planeRepository.findAll(pageable);
        return planes;
    }

    @Override
    public List<Plane> findAll() {
        return planeRepository.findAll();
    }

    @Override
    public Plane save(Plane plane) {
        return planeRepository.save(plane);
    }

    @Override
    public Optional<Plane> findById(int id) {
        return planeRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        planeRepository.deleteById(id);
    }
}
