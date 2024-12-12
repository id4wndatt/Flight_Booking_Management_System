package com.dnd.fbs.repositories;

import com.dnd.fbs.models.Plane;
import com.dnd.fbs.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface SeatRepositories extends JpaRepository<Seat,Integer> {
    public ArrayList<Seat> getSeatsBySeatCategory_CategoryNameAndPlane_PlaneID(String categoryName,int pid);
    public ArrayList<Seat> getSeatsBySeatCategory_CategoryName(String categoryName);
    public Seat getSeatBySeatID(int id);

//    @Query(value = "select p from Seat s join Plane p where s.seatID = :sid and s.plane.planeID = :pid")
    public ArrayList<Seat> getSeatsBySeatIDAndPlane_PlaneID(int sid,int pid);
}
