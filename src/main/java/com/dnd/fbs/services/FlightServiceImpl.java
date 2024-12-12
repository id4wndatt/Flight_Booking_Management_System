package com.dnd.fbs.services;

import com.dnd.fbs.models.Flight;
import com.dnd.fbs.repositories.FlightRepositories;
import com.dnd.fbs.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService{
    @Autowired
    FlightRepositories fl;

    private FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public Page<Flight> findAll(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        Page<Flight> flights = flightRepository.findAll(pageable);
        return flights;
    }

    @Override
    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public Optional<Flight> findById(int id) {
        return flightRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        flightRepository.deleteById(id);
    }

    @Override
    public List<Flight> searchFlights(String query) {
        return flightRepository.searchFlights(query);
    }

    public List<Flight> getAllFlights(){
        return fl.findAll();
    }

    @Override
    public List<Flight> findFlightByForm() {
        return null;
    }

    public List<Flight> findFlightByForm(String date_flight,String departing_from,String arriving_at){
        return fl.getFlightsByDate_flightAndDeparting_fromAndArriving_at(date_flight,departing_from,arriving_at);
    }

    public Flight getFlightByID(int fid){
        return fl.getFlightByFlightID(fid);
    }
}
