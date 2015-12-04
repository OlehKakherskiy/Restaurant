package com.springapp.dao;

import com.springapp.entity.Dish;

/**
 * Created by oleg on 29.11.15.
 */
public interface DishDao {

    Dish readDishByID(int Dish);

    void delete(Dish dish);

    Dish insert(Dish dish);

    void update(Dish dish);
}
