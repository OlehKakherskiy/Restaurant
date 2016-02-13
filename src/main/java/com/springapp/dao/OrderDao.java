package com.springapp.dao;

import com.springapp.entity.Order;

/**
 * Created by oleg on 04.12.15.
 */
public interface OrderDao {

    Order selectOrder(int ID); //TODO: realization

    void deleteOrder(int ID); //TODO: realization

    void insertOrder(Order order); //TODO: realization
}
