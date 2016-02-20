package com.springapp.dao.daoImpl;

import com.springapp.dao.UserDao;
import com.springapp.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by oleg on 29.11.15.
 */
@Service("userJpaService")
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public UserDaoImpl() {
    }

    @Transactional(readOnly = true)
    @Override
    public User selectById(int ID) {
        return entityManager.find(User.class, ID);
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
        User u = entityManager.find(User.class, ID);
        entityManager.remove(u);
        User u2 = entityManager.find(User.class, ID);
        System.out.println(u2);
    }
}
