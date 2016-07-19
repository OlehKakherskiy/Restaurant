package com.springapp.dao;

import com.springapp.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by oleg on 29.11.15.
 */
@Service("userService")
@Repository
@Transactional
public interface UserDao {

    @Transactional(readOnly = true)
    User read(int ID);

    @Transactional(readOnly = true)
    User readFully(int ID);

    User saveOrUpdate(User user);

    void delete(int ID);
}
