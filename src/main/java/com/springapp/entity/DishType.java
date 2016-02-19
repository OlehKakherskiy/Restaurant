package com.springapp.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "DishTypeHierarchy",
            joinColumns = @JoinColumn(name = "parentTypeID"),
            inverseJoinColumns = @JoinColumn(name = "subtypeID"))
    private List<DishType> subtypes;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dish> dishes;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public List<DishType> getSubtypes() {
        return subtypes;
    }

    public void setSubtypes(List<DishType> subtypes) {
        this.subtypes = subtypes;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
