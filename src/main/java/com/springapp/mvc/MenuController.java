package com.springapp.mvc;

import com.springapp.dao.DishDao;
import com.springapp.dao.DishTypeDao;
import com.springapp.entity.Dish;
import com.springapp.entity.DishType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by oleg on 03.12.15.
 */
@RestController
public class MenuController {

    @Autowired
    private DishDao dishDao;

    @Autowired
    private DishTypeDao dishTypeDao;

    public MenuController() {
    }

    @RequestMapping(value = "/menu/menuItem/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Dish> getDishProfile(@PathVariable("id") int id) {
        return new ResponseEntity<Dish>(dishDao.readDishByID(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/menu/menuList", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<DishType>> getMenu() {
        return new ResponseEntity<List<DishType>>(dishTypeDao.getAllTypes(), HttpStatus.OK);
    }
}
