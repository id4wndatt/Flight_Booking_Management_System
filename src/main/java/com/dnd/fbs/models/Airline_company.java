package com.dnd.fbs.models;

import jakarta.persistence.*;

@Entity
@Table(name = "airline_company")
public class Airline_company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airlineID")
    private int airlineID;

    @Column(name = "airline_name")
    private String airline_name;

    @Column(name = "airline_image")
    private String airline_image;

    public String getAirline_name() {
        return airline_name;
    }

    public int getAirlineID() {
        return airlineID;
    }

    public String getAirline_image() {
        return airline_image;
    }

    public void setAirlineID(int airlineID) {
        this.airlineID = airlineID;
    }

    public void setAirline_name(String airline_name) {
        this.airline_name = airline_name;
    }

    public void setAirline_image(String airline_image) {
        this.airline_image = airline_image;
    }
}
