package com.springapp.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by oleg on 29.11.15.
 */
@Entity
@Table
public class DishType {

    @Id
    @Column(name = "DishTypeID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "dishTypeID",referencedColumnName = "DishTypeID")
    private List<Dish> dishes;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "parentTypeID", referencedColumnName = "DishTypeID")
    private List<DishType> subtypes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DishType dishType = (DishType) o;

        return getID() == dishType.getID();

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public List<DishType> getSubtypes() {
        return subtypes;
    }

    public void setSubtypes(List<DishType> subtypes) {
        this.subtypes = subtypes;
    }
}
