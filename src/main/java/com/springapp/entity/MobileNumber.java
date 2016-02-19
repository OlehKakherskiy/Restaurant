package com.springapp.entity;

import javax.persistence.*;

/**
 * Created by oleg on 28.11.15.
 */
@Entity
@Table
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MobileNumber that = (MobileNumber) o;

        return getID() == that.getID() && getMobileNumber().equals(that.getMobileNumber());
    }

    @Override
    public int hashCode() {
        int result = getID();
        result = 31 * result + getMobileNumber().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MobileNumber{" +
                "ID=" + ID +
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';
    }
}
