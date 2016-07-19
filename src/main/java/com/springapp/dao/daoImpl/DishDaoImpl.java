package com.springapp.dao.daoImpl;

import com.springapp.dao.DishDao;
import com.springapp.entity.Dish;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by oleg on 29.11.15.
 */
public class DishDaoImpl implements DishDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Dish read(int ID) {
        return entityManager.find(Dish.class, ID);
    }

    /**
     * Видаляє страву із системи
     *
     * @param dishID ID страви, яка має бути видалена із системи
     */
    @Override
    public void remove(int dishID) {
        entityManager.remove(read(dishID));
    }

    @Override
    public Dish saveOrUpdate(Dish dish) {
        if (entityManager.contains(dish))
            entityManager.merge(dish);
        else entityManager.persist(dish);
        return dish;
    }

}
