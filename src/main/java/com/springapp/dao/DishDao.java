package com.springapp.dao;

import com.springapp.entity.Dish;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by oleg on 29.11.15.
 */
@Service("dishRepositoryService")
@Repository
@Transactional
public interface DishDao {

    @Transactional(readOnly = true)
    Dish read(int Dish);

    void remove(int dishID);

    Dish saveOrUpdate(Dish dish);
}
