package com.springapp.dao.daoImpl;

import com.springapp.dao.DishTypeDao;
import com.springapp.entity.DishType;

import java.util.List;

/**
 * Created by oleg on 03.12.15.
 */
public class DishTypeDaoImpl implements DishTypeDao {

//    @Autowired
//    private SessionFactory sessionFactory;

    public DishTypeDaoImpl() {
    }


    /**
     * Повертає всю ієрархію типів страв із залежними від них страв (об'єкти страв
     * повинні мати лише клієнтську частину даних)
     *
     * @return список типів
     */
    @Override
    public List<DishType> getAllTypes() {

        return null;
    }

    @Override
    public DishType getTypeById(int id) {
        return null;
    }

    /**
     * Виконує оновлення типу страв
     *
     * @param dish об'єкт з новими даними
     */
    @Override
    public void updateType(DishType dish) {

    }

    /**
     * Виконує видалення типу страви із системи. Всі залежні типи та страви мають
     * бути перезаписані до супертипу типу страви, що видаляється.
     *
     * @param dish ID типу страви, що має бути видалена із системи
     */
    @Override
    public void deleteType(DishType dish) {

    }

    /**
     * Додає тип страви до системи
     *
     * @param dish об'єкт типу страви, що буде доданий до системи
     */
    @Override
    public void addType(DishType dish) {

    }
}
