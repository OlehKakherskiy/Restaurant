package com.springapp.entity;

import javax.persistence.*;

/**
 * Created by oleg on 28.11.15.
 */
@Entity
@Table(name = "mobileNumber")
public class MobileNumber {

    @Id
    @Column(name = "mobileNumberID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "mobileNumber")
    private String mobileNumber;

    public MobileNumber() {
    }

    public MobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

}
