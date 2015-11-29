package com.springapp.entity;

import javax.persistence.*;

/**
 * Created by oleg on 28.11.15.
 */
@Entity
public class MobileNumber {

    @Id
    @Column(name = "mobileNumberID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "mobileNumber")
    private String mobileNumber;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
