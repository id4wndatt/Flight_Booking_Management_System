package com.dnd.fbs.models;

import jakarta.persistence.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "order_infor")
public class OrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderID")
    private int orderID;

    @Column(name="status")
    private int status;

    @Column(name = "total_cost")
    private long total_cost;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "customerID",referencedColumnName = "customerID")
    public Customer customer;

    @Column(name = "qr_code")
    private String qrCode;


    public OrderInfo(){
    }

    public String toStringDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        return dateFormat.format(date);
    }
    public int getOrderID() {
        return orderID;
    }

    public int getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setTotal_cost(long total_cost) {
        this.total_cost = total_cost;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public long getTotal_cost() {
        return total_cost;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getQrCode() {
        return qrCode;
    }
}
