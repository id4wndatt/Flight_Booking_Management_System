package com.dnd.fbs.models.supportModels;

import com.dnd.fbs.models.Customer;
import com.dnd.fbs.models.Flight;
import com.dnd.fbs.models.Luggage;
import com.dnd.fbs.models.Seat;

import java.util.ArrayList;

public class FlightSelected {
    public Luggage luggage;
    public Customer customer;
    public Seat seat;
    public Flight flight;
    public long airfares;
    public ArrayList<Seat> seats;

    public void setAirfares(long airfares) {
        this.airfares = airfares;
    }

    public long getAirfares() {
        return airfares;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    public Flight getFlight() {
        return flight;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Seat getSeat() {
        return seat;
    }

    public Luggage getLuggage() {
        return luggage;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setLuggage(Luggage luggage) {
        this.luggage = luggage;
    }
}
