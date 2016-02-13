package com.springapp.dao;

import com.springapp.entity.DinnerTable;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by oleg on 03.12.15.
 */
public interface DinnerTableDao {

    Set<DinnerTable> selectAllTableWithReservationStatus(LocalDateTime definedTime); //TODO: realization

    DinnerTable selectWithDetailedReservation(int ID, LocalDateTime startTime, LocalDateTime endTime); //TODO: realization

    void insertDinnerTable(DinnerTable table);

    void removeDinnerTable(DinnerTable table);

    void updateDinnerTable(DinnerTable table); //TODO: realization
}
