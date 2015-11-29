package com.springapp.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by oleg on 29.11.15.
 */
@Entity
public class DishType {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "DishTypeHierarchy",
            joinColumns = @JoinColumn(name = "parentTypeID"),
            inverseJoinColumns = @JoinColumn(name = "subtypeID"))
    private Set<DishType> subtypes;

    @OneToMany(mappedBy = "Dish", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Dish> dishes;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Set<DishType> getSubtypes() {
        return subtypes;
    }

    public void setSubtypes(Set<DishType> subtypes) {
        this.subtypes = subtypes;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }
}
