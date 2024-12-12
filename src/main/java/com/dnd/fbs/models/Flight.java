package com.dnd.fbs.models;

import jakarta.persistence.*;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flightID")
    private int flightID;

    @Column(name = "departing_from")
    private String departing_from;

    @Column(name = "arriving_at")
    private String arriving_at;

    @Column(name = "flight_time")
    private String flight_time;

    @Column(name = "departure_time")
    private String departure_time;

    @Column(name = "date_flight")
    private String date_flight;

    @Column(name = "fee_flight")
    private long fee_flight;

    @Column(name = "travel_time")
    private String travel_time;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "plane_flight", joinColumns = {@JoinColumn(name = "flightID",referencedColumnName = "flightID")}, inverseJoinColumns = {@JoinColumn(name = "planeID",referencedColumnName = "planeID")})
    private List<Plane> planes = new ArrayList<>();

    public Flight(){}

    public int getFlightID() {
        return flightID;
    }

    public String getArriving_at() {
        return arriving_at;
    }

    public long getFee_flight() {
        return fee_flight;
    }

    public String getDeparting_from() {
        return departing_from;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public String getFlight_time() {
        return flight_time;
    }

    public void setArriving_at(String arriving_at) {
        this.arriving_at = arriving_at;
    }

    public void setDeparting_from(String departing_from) {
        this.departing_from = departing_from;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public void setFee_flight(long fee_flight) {
        this.fee_flight = fee_flight;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public void setFlight_time(String flight_time) {
        this.flight_time = flight_time;
    }

    public String getTravel_time() {
        return travel_time;
    }

    public void setTravel_time(String travel_time) {
        this.travel_time = travel_time;
    }

    public String getDate_flight() {
        return date_flight;
    }

    public void setDate_flight(String date_flight) {
        this.date_flight = date_flight;
    }

    public List<Plane> getPlanes() {
        return planes;
    }

    public void setPlanes(List<Plane> planes) {
        this.planes = planes;
    }

    public String getFormatFeeFlight(long feeFlight) {
        Locale lc = new Locale("nv", "VN");
        NumberFormat nf = NumberFormat.getInstance(lc);
        return nf.format(feeFlight) + " vnđ";
    }

    public String getFormatTime(String flightTime) {
        for(int i=0; i<flightTime.length(); i++) {
            if(flightTime.charAt(0) == '0') {
                flightTime=flightTime.substring(1);
            } else {
                break;
            }
        }
        if(Integer.parseInt(flightTime)<24) {
            return flightTime + ":00";
        }
        else if (Integer.parseInt(flightTime)>=24) {
            int day = Integer.parseInt(flightTime) / 24;
            int hour = Integer.parseInt(flightTime) - day * 24;
            return day+"d " + hour +"h";
        }
        return flightTime;
    }

    public String floatToInt(Float fee) {
        return String.valueOf(fee.intValue());
    }

    public String getFlightRouter() {
        return this.getDeparting_from() + " -> " + this.getArriving_at();
    }
}
