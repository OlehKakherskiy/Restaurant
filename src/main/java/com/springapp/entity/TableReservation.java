package com.springapp.entity;

import com.springapp.entity.enums.TableStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by oleg on 03.12.15.
 */
@Entity
@Table(name = "Reservation")
public class TableReservation {

    @Id
    @Column(name = "reservationID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column
    private short price;

    @ManyToOne
    @JoinColumn(name = "tableID")
    private DinnerTable dinnerTable;

    @Column(name = "status")
    @Enumerated
    private TableStatus status;

    @Column
    private LocalDateTime startTimeReservation;

    @Column
    private LocalDateTime endTimeReservation;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public short getPrice() {
        return price;
    }

    public void setPrice(short price) {
        this.price = price;
    }

    public DinnerTable getDinnerTable() {
        return dinnerTable;
    }

    public void setDinnerTable(DinnerTable dinnerTable) {
        this.dinnerTable = dinnerTable;
    }

    public TableStatus getStatus() {
        return status;
    }

    public void setStatus(TableStatus status) {
        this.status = status;
    }

    public LocalDateTime getStartTimeReservation() {
        return startTimeReservation;
    }

    public void setStartTimeReservation(LocalDateTime startTimeReservation) {
        this.startTimeReservation = startTimeReservation;
    }

    public LocalDateTime getEndTimeReservation() {
        return endTimeReservation;
    }

    public void setEndTimeReservation(LocalDateTime endTimeReservation) {
        this.endTimeReservation = endTimeReservation;
    }
}
