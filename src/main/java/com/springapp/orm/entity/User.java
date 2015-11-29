package com.springapp.orm.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by oleg on 28.11.15.
 */
@Entity
public class User implements Serializable {

    @Id
    @Column(name = "UserID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "surname", length = 100)
    private String surname;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "userType")
    private UserType userType;

    @Column(name = "password")
    private String password;

    @Column(name = "workMark", nullable = true)
    private short workMark;

    @OneToMany(mappedBy = "mobileNumber",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<MobileNumber> mobileNumbers;

    //TODO: add order set, order execution set,

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public short getWorkMark() {
        return workMark;
    }

    public void setWorkMark(short workMark) {
        this.workMark = workMark;
    }

    public void setMobileNumbers(Set<MobileNumber> mobileNumbers) {
        this.mobileNumbers = mobileNumbers;
    }

    public Set<MobileNumber> getMobileNumbers() {
        return mobileNumbers;
    }
}
