package com.springapp.dao.daoImpl;

import com.springapp.dao.UserDao;
import com.springapp.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by oleg on 29.11.15.
 */

public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public UserDaoImpl() {
    }

    @Override
    public User read(int ID) {
        return entityManager.find(User.class, ID);
    }

    @Override
    public User readFully(int ID) {
        User res = entityManager.find(User.class, ID);
        res.getMobileNumbers();
        res.getWaiterOrders();
        res.getOrders();
        return res;
    }

    @Override
    public User saveOrUpdate(User user) {
        if (entityManager.contains(user))
            entityManager.merge(user);
        else {
            entityManager.persist(user);
        }
        return user;
    }

    @Override
    public void delete(int ID) {
        entityManager.remove(read(ID));
    }
}
