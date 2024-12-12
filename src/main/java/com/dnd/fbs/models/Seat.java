package com.dnd.fbs.models;

import jakarta.persistence.*;

@Entity
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seatID")
    private int seatID;

    @Column(name = "position")
    private String position;

    @Column(name = "status")
    private int status;

    @ManyToOne
    @JoinColumn(name = "seatCategoryID",referencedColumnName = "seatCategoryID")
    private SeatCategory seatCategory;

    @ManyToOne
    @JoinColumn(name = "planeID",referencedColumnName = "planeID")
    private Plane plane;

    public Seat() {}

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public Plane getPlane() {
        return plane;
    }

    public SeatCategory getSeatCategory() {
        return seatCategory;
    }
    public int getSeatID() {
        return seatID;
    }

    public String getPosition() {
        return position;
    }

    public int getStatus() {
        return status;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSeatCategory(SeatCategory seatCategory) {
        this.seatCategory = seatCategory;
    }

    public void setSeatID(int seatID) {
        this.seatID = seatID;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
