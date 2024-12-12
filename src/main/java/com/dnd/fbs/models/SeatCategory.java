package com.dnd.fbs.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

@Entity
@Table(name = "seat_category")
public class SeatCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seatCategoryID")
    private int seatCategoryID;

    @Column(name = "categoryName")
    private String categoryName;

    @Column(name = "luggageAttach")
    private String luggageAttach;

    @Column(name = "feeCategory")
    private long feeCategory;


    public SeatCategory(){}

    public long getFeeCategory() {
        return feeCategory;
    }

    public int getSeatCategoryID() {
        return seatCategoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getLuggageAttach() {
        return luggageAttach;
    }


    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setFeeCategory(long feeCategory) {
        this.feeCategory = feeCategory;
    }

    public void setLuggageAttach(String luggageAttach) {
        this.luggageAttach = luggageAttach;
    }

    public void setSeatCategoryID(int seatCategoryID) {
        this.seatCategoryID = seatCategoryID;
    }

    @Override
    public String toString() {
        return "SeatCategory{" +
                "seatCategoryId=" + seatCategoryID +
                ", categoryName='" + categoryName + '\'' +
                ", luggageAttach='" + luggageAttach + '\'' +
                ", feeCategory=" + feeCategory +
                '}';
    }

    public String getFormatFeeCategory(long feeCategory) {
        Locale lc = new Locale("nv", "VN");
        NumberFormat nf = NumberFormat.getInstance(lc);
        return nf.format(feeCategory) + " vnđ";
    }

    public String floatToInt(Float fee) {
        return String.valueOf(fee.intValue());
    }
}
