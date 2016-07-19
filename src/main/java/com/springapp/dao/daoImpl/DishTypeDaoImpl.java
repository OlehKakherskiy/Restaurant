package com.springapp.dao.daoImpl;

import com.springapp.dao.DishTypeDao;
import com.springapp.entity.DishType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 03.12.15.
 */
public class DishTypeDaoImpl implements DishTypeDao {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Повертає всю ієрархію типів страв із залежними від них страв (об'єкти страв
     * повинні мати лише клієнтську частину даних)
     *
     * @return список типів
     */
    @Override
    public List<DishType> readAllTypes() {
        return new ArrayList<DishType>();
    }

    @Override
    public DishType readType(int id) {
        DishType dishType = entityManager.find(DishType.class, id);
        dishType.getDishes();
        dishType.getSubtypes();
        return dishType;
    }


    /**
     * Виконує видалення типу страви із системи. Всі залежні типи та страви мають
     * бути перезаписані до супертипу типу страви, що видаляється.
     *
     * @param id ID типу страви, що має бути видалена із системи
     */
    @Override
    public void deleteType(int id) {
        entityManager.remove(entityManager.find(DishType.class, id));
    }

    /**
     * Додає тип страви до системи
     *
     * @param dish об'єкт типу страви, що буде доданий до системи
     */
    @Override
    public DishType addOrUpdateType(DishType dish) {
        if (entityManager.contains(dish))
            entityManager.merge(dish);
        else entityManager.persist(dish);
        return dish;
    }
}
