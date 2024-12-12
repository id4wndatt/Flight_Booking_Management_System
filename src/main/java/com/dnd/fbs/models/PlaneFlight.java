package com.dnd.fbs.models;

import jakarta.persistence.*;

@Entity
@Table(name = "plane_flight")
public class PlaneFlight {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Id
    @JoinColumn(name = "flightID")
    private Flight flight;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @Id
    @JoinColumn(name = "planeID")
    private Plane plane;

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }
}
