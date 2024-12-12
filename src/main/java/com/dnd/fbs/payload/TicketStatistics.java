package com.dnd.fbs.payload;

public class TicketStatistics {
    private long ticketCount;
    private int month;
    private int year;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(long ticketCount) {
        this.ticketCount = ticketCount;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "TicketStatistics{" +
                "ticketCount=" + ticketCount +
                ", month=" + month +
                '}';
    }
}
