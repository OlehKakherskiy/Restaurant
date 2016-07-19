package com.springapp.dao.daoImpl;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.springapp.dao.DishDao;
import com.springapp.entity.Dish;
import com.springapp.entity.DishType;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

/**
 * @author Oleh Kakherskyi, IP-31, FICT, NTUU "KPI", olehkakherskiy@gmail.com
 */
@DatabaseSetup(value = "/restaurantMenu.xml")
public class DishDaoTest extends ApplicationDBUnitTest {

    @Autowired
    private DishDao dishDao;

    @Test
    @Transactional
    public void testRead() throws Exception {
        Dish expectedDish = new Dish();
        expectedDish.setID(151);
        expectedDish.setDescription("Масляная рыба, палтус, лосось, шпинат, рис отварной, лук-порей, специи, лимонный соус");
        expectedDish.setName("Лосось, палтус, масляная рыба, гарнированные рисом со шпинатом и лимонным соусом");
        expectedDish.setPrice(590.0F);

        Dish actualDish = dishDao.read(151);
        Assert.assertEquals(expectedDish, actualDish);
    }

    @Test
    @Transactional
    public void testReadAbsentDish() throws Exception {
        Assert.assertNull(dishDao.read(10000));
    }

    @Test
    @ExpectedDatabase(value = "/restaurantMenu_removeDish.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void testDelete() throws Exception {
        dishDao.remove(173);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteAbsent() throws Exception {
        dishDao.remove(10000);
    }

    @Test
    @ExpectedDatabase("/restaurantMenu_insertedDish.xml")
    @ResetPrimaryKeysToStartValues(tables = "Dish", startPrimaryKeyValues = 175)
    @Ignore
    public void testSaveOrUpdate_saveAllDataFilled() throws Exception {
        Dish d = new Dish();
        d.setDescription("testDescriptionId175");
        d.setName("testNameId175");
        d.setPrice(900.0F);

        dishDao.saveOrUpdate(d); //TODO: хз что делать с dishType - cascade?
    }

    @Test(expected = PersistenceException.class)
    public void testSaveOrUpdate_saveWithoutName() throws Exception {
        testSaveWithoutParamBase(true, false, false, false);
    }

    @Test(expected = PersistenceException.class)
    public void testSaveOrUpdate_saveWithoutDescription() throws Exception {
        testSaveWithoutParamBase(false, true, false, false);
    }

    @Test(expected = PersistenceException.class)
    public void testSaveOrUpdate_saveWithoutPrice() throws Exception {
        testSaveWithoutParamBase(false, false, true, false);
    }

    @Test(expected = PersistenceException.class)
    public void testSaveOrUpdate_saveWithoutType() throws Exception {
        testSaveWithoutParamBase(false, false, false, true);
    }

    private void testSaveWithoutParamBase(boolean excludeName, boolean excludeDescription, boolean excludePrice, boolean excludeType) throws Exception {
        Dish dish = new Dish();
        if (!excludeName)
            dish.setName("testName");
        if (!excludeDescription)
            dish.setDescription("testDesc");
        if (!excludePrice)
            dish.setPrice(1.0F);
        if (!excludeType)
            dish.setType(new DishType());

        dishDao.saveOrUpdate(dish);
    }
}