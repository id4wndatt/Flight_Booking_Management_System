package com.dnd.fbs.models;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "airline_company")
public class AirlineCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airlineID")
    private int airlineID;

    @Column(name = "airline_name")
    private String airlineName;

    @Column(name = "airline_image")
    private String airlineLogo;

    public AirlineCompany() {}

    public AirlineCompany(int airlineID, String airlineName, String airlineLogo) {
        this.airlineID = airlineID;
        this.airlineName = airlineName;
        this.airlineLogo = airlineLogo;
    }

    public int getAirlineID() {
        return airlineID;
    }

    public void setAirlineID(int airlineID) {
        this.airlineID = airlineID;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getAirlineLogo() {
        return airlineLogo;
    }

    public void setAirlineLogo(String airlineLogo) {
        this.airlineLogo = airlineLogo;
    }

    @Transient
    public String getPhotosImagePath() {
        if (airlineLogo == null) return "/images/default-image.png";

        return "/admin/airlineCompany-photos/" + this.airlineID + "/" + this.airlineLogo;
    }
}
