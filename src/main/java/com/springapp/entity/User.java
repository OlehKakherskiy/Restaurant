package com.springapp.entity;

import com.springapp.entity.enums.UserType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * Created by oleg on 28.11.15.
 */
@Entity
@Table
public class User implements Serializable {

    @Id
    @Column(name = "UserID")
    @GeneratedValue
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

    @Column(name = "workMark", nullable = true)
    private short workMark;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "UserID",referencedColumnName = "UserID")
    private Set<MobileNumber> mobileNumbers;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders;

    @OneToMany(mappedBy = "waiter", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> waiterOrders;

    public User(int ID, String name, String surname, String email, UserType userType, String password,
                short workMark) {
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
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.userType = userType;
        this.password = password;
        this.workMark = workMark;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "workMark=" + workMark +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                ", email='" + email + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", ID=" + ID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (ID != user.ID) return false;
        if (workMark != user.workMark) return false;
        if (!name.equals(user.name)) return false;
        if (!surname.equals(user.surname)) return false;
        if (!email.equals(user.email)) return false;
        if (userType != user.userType) return false;
        if (!password.equals(user.password)) return false;
        if (mobileNumbers != null ? !mobileNumbers.equals(user.mobileNumbers) : user.mobileNumbers != null)
            return false;
        if (orders != null ? !orders.equals(user.orders) : user.orders != null) return false;
        return !(waiterOrders != null ? !waiterOrders.equals(user.waiterOrders) : user.waiterOrders != null);

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

    public void setMobileNumbers(Set<MobileNumber> mobileNumbers) {
        this.mobileNumbers = mobileNumbers;
    }

    public Set<MobileNumber> getMobileNumbers() {
        return mobileNumbers;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Order> getWaiterOrders() {
        return waiterOrders;
    }

    public void setWaiterOrders(Set<Order> waiterOrders) {
        this.waiterOrders = waiterOrders;
    }
}
