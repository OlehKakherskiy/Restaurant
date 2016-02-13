package com.springapp.dao;

import com.springapp.entity.Dish;
import com.springapp.entity.DishType;

import java.util.List;

/**
 * Created by oleg on 03.12.15.
 */
public interface DishTypeDao {

    List<DishType> getAllTypes(); //TODO: realization

    DishType getTypeById(int id); //TODO: realization

    void updateType(DishType dish);

    void deleteType(DishType dish);

    void addType(DishType dish);
}
