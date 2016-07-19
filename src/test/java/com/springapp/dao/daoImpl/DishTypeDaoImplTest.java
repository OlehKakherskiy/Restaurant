package com.springapp.dao.daoImpl;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Oleh Kakherskyi, IP-31, FICT, NTUU "KPI", olehkakherskiy@gmail.com
 */
@DatabaseSetup(value = "/restaurantMenu.xml")
public class DishTypeDaoImplTest extends ApplicationDBUnitTest {

    @Test
    @Ignore
    public void testReadAllTypes() throws Exception {

    }

    @Test
    public void testReadType() throws Exception {
        
    }

    @Test
    public void testDeleteType() throws Exception {

    }

    @Test
    public void testAddOrUpdateType() throws Exception {

    }
}