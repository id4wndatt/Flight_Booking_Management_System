package com.dnd.fbs.payload;

public class TicketDto {
    private int ticketId;
    private float airfares;
    private int luggageId;
    private int flightId;

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public float getAirfares() {
        return airfares;
    }

    public void setAirfares(float airfares) {
        this.airfares = airfares;
    }

    public int getLuggageId() {
        return luggageId;
    }

    public void setLuggageId(int luggageId) {
        this.luggageId = luggageId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }
}
