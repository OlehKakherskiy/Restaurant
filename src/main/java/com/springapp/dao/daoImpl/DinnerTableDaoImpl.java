package com.springapp.dao.daoImpl;

import com.springapp.dao.DinnerTableDao;
import com.springapp.entity.DinnerTable;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by oleg on 04.12.15.
 */
public class DinnerTableDaoImpl implements DinnerTableDao{

    @Override
    public Set<DinnerTable> selectAllTableWithReservationStatus(LocalDateTime definedTime) {
        return null;
    }

    @Override
    public DinnerTable selectWithDetailedReservation(int ID, LocalDateTime startTime, LocalDateTime endTime) {
        return null;
    }

    @Override
    public void insertDinnerTable(DinnerTable table) {

    }

    @Override
    public void removeDinnerTable(DinnerTable table) {

    }

    @Override
    public void updateDinnerTable(DinnerTable table) {

    }
}
