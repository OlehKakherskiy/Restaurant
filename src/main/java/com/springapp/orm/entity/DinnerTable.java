package com.springapp.orm.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by oleg on 28.11.15.
 */
@Entity
@Table(name = "Table")
public class DinnerTable implements Serializable {

    @Column(name = "tableID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int ID;

    @Column(name = "seatsNumber")
    private int seatsNumber;

    //TODO: reservation set. Add Reservation Class
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }
}
