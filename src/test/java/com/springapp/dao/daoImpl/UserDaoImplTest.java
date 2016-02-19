package com.springapp.dao.daoImpl;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.springapp.dao.UserDao;
import com.springapp.entity.MobileNumber;
import com.springapp.entity.User;
import com.springapp.entity.enums.UserType;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Oleh Kakherskyi, IP-31, FICT, NTUU "KPI", olehkakherskiy@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ResetDBAutoIncrementTestListener.class, TransactionDbUnitTestExecutionListener.class})
@ContextConfiguration("file:src/main/resources/app-context-xml.xml")
@ActiveProfiles("test")
@DbUnitConfiguration(databaseConnection = {"dbUnitDatabaseConnection"})
@DatabaseSetup(value = "/userDataSet.xml", type = DatabaseOperation.CLEAN_INSERT)
public class UserDaoImplTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private UserDao userDao;


    @Test
    @Transactional
    public void testSelectById() throws Exception {
        User expect = new User();
        expect.setID(1);
        expect.setEmail("ol@gmail.com");
        expect.setName("oleh");
        expect.setSurname("kakherskiy");
        expect.setWorkMark((short) 0);
        expect.setPassword("123");
        expect.setUserType(UserType.ADMIN);
        List<MobileNumber> numbers = new ArrayList<MobileNumber>();
        MobileNumber mobileNumber1 = new MobileNumber("09357056811");
        mobileNumber1.setID(1);
        MobileNumber mobileNumber2 = new MobileNumber("093570568112");
        mobileNumber2.setID(10);
        MobileNumber mobileNumber3 = new MobileNumber("093570568113");
        mobileNumber3.setID(11);
        MobileNumber mobileNumber4 = new MobileNumber("093570568114");
        mobileNumber4.setID(12);
        numbers.add(mobileNumber1);
        numbers.add(mobileNumber2);
        numbers.add(mobileNumber3);
        numbers.add(mobileNumber4);
        expect.setMobileNumbers(numbers);
        User user = userDao.selectById(1);
        Assert.assertTrue(expect.equals(user));
    }

    @Test
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "/insertUser_2mobileNumbers.xml")
    @Transactional
    @ResetPrimaryKeysToStartValues(tables = {"User", "MobileNumber"}, startPrimaryKeyValues = {10, 13})
    public void testSaveWith2MobileNumbers() throws Exception {
        User testSaveUser = new User();
        testSaveUser.setName("name_7");
        testSaveUser.setSurname("sur_7");
        testSaveUser.setEmail("em_7@gmail.com");
        testSaveUser.setPassword("000");
        testSaveUser.setUserType(UserType.CLIENT);

        MobileNumber num1 = new MobileNumber("093570568110");
        MobileNumber num2 = new MobileNumber("093570568111");

        List<MobileNumber> set = new ArrayList<MobileNumber>();
        set.add(num1);
        set.add(num2);
        testSaveUser.setMobileNumbers(set);

        userDao.save(testSaveUser);

        Assert.assertNotNull("User object isn't returned after persisting operation", testSaveUser);
        Assert.assertNotNull("User's object ID isn't initialised after persisting operation", testSaveUser.getID());
    }

    @Test
    @Transactional
    @ResetPrimaryKeysToStartValues(tables = {"User", "MobileNumber"}, startPrimaryKeyValues = {10, 13})
    public void testSaveAllDataFilled() throws Exception {
        User testSaveUser = new User();
        testSaveUser.setName("name_7");
        testSaveUser.setSurname("sur_7");
        testSaveUser.setEmail("em_7@gmail.com");
        testSaveUser.setPassword("000");
        testSaveUser.setUserType(UserType.KITCHENER);

        MobileNumber num1 = new MobileNumber("093570568110");

        List<MobileNumber> set = new ArrayList<MobileNumber>();
        set.add(num1);
        testSaveUser.setMobileNumbers(set);
        userDao.save(testSaveUser);

        Assert.assertNotNull("User object isn't returned after persisting operation", testSaveUser);
        Assert.assertNotNull("User's object ID isn't initialised after persisting operation", testSaveUser.getID());
    }

//    public void testDelete() throws Exception {
//
//    }
//
//    public void testUpdate() throws Exception {
//
//    }
}