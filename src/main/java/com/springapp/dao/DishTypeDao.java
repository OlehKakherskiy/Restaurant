package com.springapp.dao;

import com.springapp.entity.DishType;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by oleg on 03.12.15.
 */
@Service("dishTypeService")
@Repository
@Transactional
public interface DishTypeDao {

    @Transactional(readOnly = true)
    List<DishType> readAllTypes();

    @Transactional(readOnly = true)
    DishType readType(int id);

    void deleteType(int id);

    DishType addOrUpdateType(DishType dish);
}
