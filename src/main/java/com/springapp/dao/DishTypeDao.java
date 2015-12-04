package com.springapp.dao;

import com.springapp.entity.Dish;
import com.springapp.entity.DishType;

import java.util.List;

/**
 * Created by oleg on 03.12.15.
 */
public interface DishTypeDao {

    List<DishType> getAllTypes();

    DishType getTypeById(int id);

    void updateType(Dish dish);

    void deleteType(Dish dish);

    void addType(Dish dish);
}
