package com.springapp.dao;

import com.springapp.entity.User;

/**
 * Created by oleg on 29.11.15.
 */
public interface UserDao {

    User selectById(int ID); //TODO: realization выборка персональных данных, заказов, связанных с данным пользователем, заданий и тд

    User save(User user); //TODO: realization регистрация нового пользователя

    void delete(User user); //TODO: realization

    void update(User user); //TODO: realization замена контактных данных
}
