package com.dnd.fbs.repositories;

import com.dnd.fbs.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlightRepositories extends JpaRepository<Flight, Integer> {
//    public List<Flight> findFlightsByDate_flightAndDeparting_fromAndArriving_at(String date_flight,String departing_from,String arriving_at);
    @Query(value = "select * from Flight fl where fl.date_flight = :date_flight and fl.departing_from = :departing_from and fl.arriving_at = :arriving_at",nativeQuery = true)
    public List<Flight> getFlightsByDate_flightAndDeparting_fromAndArriving_at(@Param("date_flight") String date_flight,@Param("departing_from") String departing_from,@Param("arriving_at") String arriving_at);

    public Flight getFlightByFlightID(int fid);

    @Query(value = "select distinct f from Flight f join f.planes p where f.flightID = :fid and p.planeID = :pid")
    public Flight getFlightByIDAndPlaneID(@Param("fid") int fid,@Param("pid") int pid);
}
