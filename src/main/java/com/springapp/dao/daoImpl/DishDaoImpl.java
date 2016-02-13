package com.springapp.dao.daoImpl;

import com.springapp.dao.DishDao;
import com.springapp.entity.Dish;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by oleg on 29.11.15.
 */
public class DishDaoImpl implements DishDao {


    @PersistenceContext
    public EntityManager entityManager;

    public DishDaoImpl() {
    }

    @Override
    public Dish readDishByID(int Dish) {
        return null;
    }

    /**
     * Видаляє страву із системи
     *
     * @param dishID ID страви, яка має бути видалена із системи
     */
    @Override
    public void delete(int dishID) {

    }

    @Override
    public Dish insert(Dish dish) {
        return null;
    }

    @Override
    public void update(Dish dish) {

    }

//    public SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
//
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
}
