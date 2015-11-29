package com.springapp.entity;

import com.springapp.entity.enums.OrderStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by oleg on 28.11.15.
 */
@Entity
public class Order implements Serializable {

    @Id
    @Column(name = "OrderID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userID")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "waiterID")
    private User waiter;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderPosition> dishList;

    @Column(name = "orderStatus")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getWaiter() {
        return waiter;
    }

    public void setWaiter(User waiter) {
        this.waiter = waiter;
    }

    public Set<OrderPosition> getDishList() {
        return dishList;
    }

    public void setDishList(Set<OrderPosition> dishList) {
        this.dishList = dishList;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
