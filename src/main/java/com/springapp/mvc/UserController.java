package com.springapp.mvc;

import com.springapp.dao.UserDao;
import com.springapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by oleg on 03.12.15.
 */
@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(method = RequestMethod.POST, value = "/register", consumes = "application/json")
    public HttpStatus registerUser(User u) {
        userDao.saveOrUpdate(u);
        return HttpStatus.OK;
    }

    @RequestMapping(value = "/profile/{ID}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<User> getUserProfile(@PathVariable int ID) {
        return new ResponseEntity<User>(userDao.read(ID), HttpStatus.OK);
    }

    @RequestMapping(value = "/updateProfile", consumes = "application/json", method = RequestMethod.POST)
    public HttpStatus updateProfile(User user) {
        userDao.saveOrUpdate(user);
        return HttpStatus.OK;
    }
}
