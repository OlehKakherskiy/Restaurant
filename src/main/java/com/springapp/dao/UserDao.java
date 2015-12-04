package com.springapp.dao;

import com.springapp.entity.User;

/**
 * Created by oleg on 29.11.15.
 */
public interface UserDao {

    User selectById(int ID);

    User save(User user);

    void delete(User user);

    void update(User user);
}
