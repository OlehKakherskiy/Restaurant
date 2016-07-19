package com.springapp.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by oleg on 28.11.15.
 */
@Entity
@Table
public class Dish implements Serializable {

    @Id
    @Column(name = "DishID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dishTypeID")
    private DishType type;

    @Column(name = "price")
    private float price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dish dish = (Dish) o;

        if (getID() != dish.getID()) return false;
        if (Float.compare(dish.getPrice(), getPrice()) != 0) return false;
        if (!getDescription().equals(dish.getDescription())) return false;
        return getName().equals(dish.getName());
    }

    @Override
    public int hashCode() {
        int result = getID();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + (getPrice() != +0.0f ? Float.floatToIntBits(getPrice()) : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Dish{");
        sb.append("name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", ID=").append(ID);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DishType getType() {
        return type;
    }

    public void setType(DishType type) {
        this.type = type;
    }
}
