package com.springapp.entity;

import com.springapp.entity.enums.OrderStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by oleg on 28.11.15.
 */
@Entity
@Table(name = "Bill")
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

    public Order() {
        dishList = new HashSet<OrderPosition>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (getID() != order.getID()) return false;
        if (!getCustomer().equals(order.getCustomer())) return false;
        if (getWaiter() != null ? !getWaiter().equals(order.getWaiter()) : order.getWaiter() != null) return false;
        if (!getDishList().equals(order.getDishList())) return false;
        return getStatus() == order.getStatus();

    }

    @Override
    public int hashCode() {
        int result = getID();
        result = 31 * result + getCustomer().hashCode();
        result = 31 * result + (getWaiter() != null ? getWaiter().hashCode() : 0);
        result = 31 * result + getDishList().hashCode();
        result = 31 * result + getStatus().hashCode();
        return result;
    }

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
