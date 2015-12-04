package com.springapp.mvc;

import com.springapp.dao.OrderDao;
import com.springapp.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by oleg on 04.12.15.
 */
@RestController
public class OrderController {

    @Autowired
    OrderDao orderDao;

    @RequestMapping(value = "/addOrder", method = RequestMethod.POST, consumes = "application/json")
    public HttpStatus addOrder(Order order) {
        orderDao.insertOrder(order);
        return HttpStatus.OK;
    }

    @RequestMapping(value = "/order/{orderID}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Order> getOrderDetails(@PathVariable int orderID) {
        return new ResponseEntity<Order>(orderDao.selectOrder(orderID), HttpStatus.OK);
    }

    @RequestMapping(value = "/removeOrder/{orderID}", method = RequestMethod.POST)
    public HttpStatus removeOrder(@PathVariable int orderID) {
        orderDao.deleteOrder(orderID);
        return HttpStatus.OK;
    }
}
