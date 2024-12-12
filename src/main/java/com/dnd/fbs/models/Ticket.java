package com.dnd.fbs.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketID")
    private int ticketID;

    @Column(name = "airfares")
    private float airfares;

    @ManyToOne
    @JoinColumn(name = "luggageID",referencedColumnName = "luggageID")
    private Luggage luggage;

    @ManyToOne
    @JoinColumn(name = "seatID", referencedColumnName = "seatID")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "customerID",referencedColumnName = "customerID")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "orderID",referencedColumnName = "orderID")
    private OrderInfo order;

    @ManyToOne
    @JoinColumn(name = "flightID",referencedColumnName = "flightID")
    private Flight flight;

    @Column(name = "bar_code")
    private String barCode;

    public Ticket(){}

    public Flight getFlight() {
        return flight;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setOrder(OrderInfo order) {
        this.order = order;
    }

    public Customer getCustomer() {
        return customer;
    }

    public OrderInfo getOrder() {
        return order;
    }

    public float getAirfares() {
        return airfares;
    }

    public int getTicketID() {
        return ticketID;
    }

    public Luggage getLuggage() {
        return luggage;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setAirfares(float airfares) {
        this.airfares = airfares;
    }

    public void setLuggage(Luggage luggage) {
        this.luggage = luggage;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}

