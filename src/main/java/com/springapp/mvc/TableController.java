package com.springapp.mvc;

import com.springapp.dao.DinnerTableDao;
import com.springapp.entity.DinnerTable;
import com.springapp.entity.TableReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by oleg on 03.12.15.
 */
@RestController
public class TableController {

    @Autowired
    private DinnerTableDao dinnerTableDao;

    @RequestMapping(value = "/tableReservation/{time}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Set<DinnerTable>> getAllTableReservationStatus(@PathVariable("time") LocalDateTime definedTime) {
        return new ResponseEntity<Set<DinnerTable>>(dinnerTableDao.selectAllTableWithReservationStatus(definedTime), HttpStatus.OK);
    }

    @RequestMapping(value = "/tableReservation/{ID}/{startTime}/{endTime}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<DinnerTable> getDetailedTableReservationInformation(
            @PathVariable int ID, @PathVariable LocalDateTime startTime, @PathVariable LocalDateTime endTime) {
        return new ResponseEntity<DinnerTable>(dinnerTableDao.selectWithDetailedReservation(ID, startTime, endTime), HttpStatus.OK);
    }

    @RequestMapping(value = "/reserveTable", method = RequestMethod.POST,
            consumes = "application/json", produces = "application/json")
    public HttpStatus reserveDinnerTable(TableReservation reservation) {
        //TODO: realise method logic. Need DAO. Add Order and take a reservation payment
        return HttpStatus.OK;
    }

    @RequestMapping(value = "/updateReservation", method = RequestMethod.POST,
            consumes = "application/json", produces = "application/json")
    public HttpStatus updateReservation(TableReservation reservation) {
        //TODO: realise method logic. Need DAO.
        return HttpStatus.OK;
    }
}
