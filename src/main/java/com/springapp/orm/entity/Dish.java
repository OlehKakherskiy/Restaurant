package com.springapp.orm.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by oleg on 28.11.15.
 */
@Entity
public class Dish implements Serializable {

    @Id
    @Column(name = "DishID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

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

    //TODO: create hierarchy of DishTypes + create 1:n relation for Dish/DishType
}
