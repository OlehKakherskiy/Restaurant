package com.springapp.dao;

import com.springapp.entity.Order;

/**
 * Created by oleg on 04.12.15.
 */
public interface OrderDao {

    Order selectOrder(int ID);

    void deleteOrder(int ID);

    void insertOrder(Order order);
}
