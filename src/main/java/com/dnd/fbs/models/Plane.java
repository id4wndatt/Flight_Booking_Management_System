package com.dnd.fbs.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "plane")
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planeID")
    private int planeID;

    @Column(name = "plane_name")
    private String plane_name;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "airlineID",referencedColumnName = "airlineID")
    private Airline_company airlineCompany;

    @ManyToMany(mappedBy = "planes")
    private List<Flight> flights = new ArrayList<>();


    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    //    @OneToMany(mappedBy = "plane",cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private ArrayList<Seat> seats;
//
//    public Plane(){}
//
//    public void setSeats(ArrayList<Seat> seats) {
//        this.seats = seats;
//    }
//
//    public ArrayList<Seat> getSeats() {
//        return seats;
//    }

    public int getPlaneID() {
        return planeID;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPlane_name() {
        return plane_name;
    }

    public Airline_company getAirlineCompany() {
        return airlineCompany;
    }

    public void setPlane_name(String plane_name) {
        this.plane_name = plane_name;
    }

    public void setPlaneID(int planeID) {
        this.planeID = planeID;
    }

    public void setAirlineCompany(Airline_company airlineCompany) {
        this.airlineCompany = airlineCompany;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
