package com.springapp.entity;

import javax.persistence.*;

/**
 * Created by oleg on 29.11.15.
 */
@Entity
public class OrderPosition {

    @Id
    @Column(name = "orderPositionID")
    private int ID;

    @ManyToOne(optional = false)
    @JoinColumn(name = "orderID")
    private Order parent;

    @ManyToOne(optional = false)
    @JoinColumn(name = "dishID")
    private Dish dish;

    @Column()
    private short dishCount;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Order getParent() {
        return parent;
    }

    public void setParent(Order parent) {
        this.parent = parent;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public short getDishCount() {
        return dishCount;
    }

    public void setDishCount(short dishCount) {
        this.dishCount = dishCount;
    }
}
