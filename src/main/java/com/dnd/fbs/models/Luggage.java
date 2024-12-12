package com.dnd.fbs.models;

import jakarta.persistence.*;

@Entity
@Table(name = "luggage")
public class Luggage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "luggageID")
    private int luggageID;

    @Column(name = "weight")
    private int weight;

    @Column(name = "cost")
    private float cost;

    public Luggage(){}

    public int getLuggageID() {
        return luggageID;
    }

    public float getCost() {
        return cost;
    }

    public int getWeight() {
        return weight;
    }

    public void setLuggageID(int luggageID) {
        this.luggageID = luggageID;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
