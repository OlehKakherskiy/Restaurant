package com.springapp.entity;

import com.springapp.entity.enums.UserType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by oleg on 28.11.15.
 */
@Entity
@Table
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
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(name = "password")
    private String password;

    @Column(name = "workMark")
    private short workMark;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID", referencedColumnName = "UserID", nullable = false)
    private List<MobileNumber> mobileNumbers;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    @OneToMany(mappedBy = "waiter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> waiterOrders;

    public User(int ID, String name, String surname, String email, UserType userType, String password,
                short workMark) {
        this();
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.userType = userType;
        this.password = password;
        this.workMark = workMark;
    }

    public User(String name, String surname, String email, UserType userType, String password,
                short workMark) {
        this();
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.userType = userType;
        this.password = password;
        this.workMark = workMark;
    }

    public User() {
        mobileNumbers = new ArrayList<MobileNumber>();
        orders = new ArrayList<Order>();
        waiterOrders = new ArrayList<Order>();
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder("MobileNumbers: ");
        for (MobileNumber m : getMobileNumbers())
            b.append(m.toString()).append(", ");
        b.delete(b.length() - 2, b.length());
        return "User{" +
                "workMark=" + workMark +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                ", email='" + email + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", ID=" + ID + " " + b.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (getID() != user.getID()) return false;
        if (getWorkMark() != user.getWorkMark()) return false;
        if (!getName().equals(user.getName())) return false;
        if (!getSurname().equals(user.getSurname())) return false;
        if (!getEmail().equals(user.getEmail())) return false;
        if (getUserType() != user.getUserType()) return false;
        if (!getPassword().equals(user.getPassword())) return false;

        if (!getMobileNumbers().equals(user.getMobileNumbers())) return false;
        if (!getOrders().equals(user.getOrders())) return false;
        return getWaiterOrders().equals(user.getWaiterOrders());
    }

    @Override
    public int hashCode() {
        int result = getID();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getSurname().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getUserType().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + (int) getWorkMark();
        result = 31 * result + getMobileNumbers().hashCode();
        result = 31 * result + getOrders().hashCode();
        result = 31 * result + getWaiterOrders().hashCode();
        return result;
    }

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

    public List<MobileNumber> getMobileNumbers() {
        return mobileNumbers;
    }

    public void setMobileNumbers(List<MobileNumber> mobileNumbers) {
        this.mobileNumbers = mobileNumbers;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getWaiterOrders() {
        return waiterOrders;
    }

    public void setWaiterOrders(List<Order> waiterOrders) {
        this.waiterOrders = waiterOrders;
    }
}
