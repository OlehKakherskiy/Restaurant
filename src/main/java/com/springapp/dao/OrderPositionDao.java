package com.springapp.dao;

import com.springapp.entity.OrderPosition;

/**
 * Created by oleg on 04.12.15.
 */
public interface OrderPositionDao {

    void update(OrderPosition position);

    void remove(int orderPositionID);

    void insert(OrderPosition position);
}
