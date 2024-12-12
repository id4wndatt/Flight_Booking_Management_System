package com.dnd.fbs.services;

import com.dnd.fbs.models.Flight;
import org.springframework.stereotype.Service;
import java.util.Optional;
import org.springframework.data.domain.Page;

import java.util.List;
@Service
public interface FlightService {
    Page<Flight> findAll(int pageNo, int pageSize, String sortBy, String sortDir);
    Flight save(Flight flight);
    Optional<Flight> findById(int id);
    void deleteById(int id);
    List<Flight> searchFlights(String query);
    public List<Flight> getAllFlights();
    public List<Flight> findFlightByForm();
    public Flight getFlightByID(int fid);
}
