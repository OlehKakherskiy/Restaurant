package com.springapp.mvc;

import com.springapp.entity.OrderPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by oleg on 04.12.15.
 */
@RestController
public class OrderPositionController {

//    @Autowired
//    private OrderPositionDao orderPositionDao;
//
//    @RequestMapping(value = "/addOrderPosition",method = RequestMethod.POST,
//            produces = "application/json",
//            consumes = "application/json")
//    public HttpStatus addOrderPosition(OrderPosition orderPosition){
//        orderPositionDao.insert(orderPosition);
//        return HttpStatus.OK;
//    }

//    @RequestMapping(value = "/updateOrderPosition", method = RequestMethod.POST,
//            produces = "application/json",
//            consumes = "application/json")
//    public HttpStatus updateOrderPosition(OrderPosition orderPosition){
//        orderPositionDao.update(orderPosition);
//        return HttpStatus.OK;
//    }

//    @RequestMapping(value = "/removeOrderPosition/{ID}",method = RequestMethod.POST, produces = "application/json")
//    public HttpStatus removeOrderPosition(@PathVariable int ID){
//        orderPositionDao.remove(ID);
//        //TODO: бизнесс - логика оплаты блюда, которое уже готово.
//        return HttpStatus.OK;
//    }
}
