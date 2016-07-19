package com.springapp.dao.daoImpl;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.springapp.dao.UserDao;
import com.springapp.entity.MobileNumber;
import com.springapp.entity.User;
import com.springapp.entity.enums.UserType;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Oleh Kakherskyi, IP-31, FICT, NTUU "KPI", olehkakherskiy@gmail.com
 */
@DatabaseSetup(value = "/userDataSet.xml")
public class UserDaoTest extends ApplicationDBUnitTest {

    @Autowired
    private UserDao userDao;

    private static User testUser;

//    @BeforeClass
//    public static void beforeClassInit() {
//        testUser.setName("name");
//        testUser.setSurname("surname");
//        testUser.setEmail("email");
//        testUser.setPassword("pass");
//        MobileNumber num1 = new MobileNumber("093570568110");
//
//
//        List<MobileNumber> set = new ArrayList<MobileNumber>();
//        set.add(num1);
//        testUser.setMobileNumbers(set);
//    }


    @Test
    @Transactional
    public void testRead() throws Exception {
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
        User user = userDao.read(1);
        Assert.assertTrue(expect.equals(user));
    }

    @Test
    @Transactional
    public void testReadFully() {
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

        User actual = userDao.readFully(1);
        Assert.assertTrue(expect.equals(actual));
    }

    @Test
    @ResetPrimaryKeysToStartValues(tables = {"User", "MobileNumber"}, startPrimaryKeyValues = {10, 13})
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "/insertUser_2mobileNumbers.xml")
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

        userDao.saveOrUpdate(testSaveUser);

        Assert.assertNotNull("User object isn't returned after persisting operation", testSaveUser);
        Assert.assertNotNull("User's object ID isn't initialised after persisting operation", testSaveUser.getID());
    }

    @Test
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
        userDao.saveOrUpdate(testSaveUser);

        Assert.assertNotNull("User object isn't returned after persisting operation", testSaveUser);
        Assert.assertNotNull("User's object ID isn't initialised after persisting operation", testSaveUser.getID());
    }

    /**
     * checking PersistenceException when save user without name
     */
    @Test(expected = PersistenceException.class)
    public void testSaveWithoutName() {
        User testSaveUser = new User();
//        testSaveUser.setName("name_7");
        testSaveUser.setSurname("sur_7");
        testSaveUser.setEmail("em_7@gmail.com");
        testSaveUser.setPassword("000");
        testSaveUser.setUserType(UserType.KITCHENER);

        MobileNumber num1 = new MobileNumber("093570568110");

        List<MobileNumber> set = new ArrayList<MobileNumber>();
        set.add(num1);
        testSaveUser.setMobileNumbers(set);

        userDao.saveOrUpdate(testSaveUser);
    }

    @Test(expected = PersistenceException.class)
    public void testSaveWithoutLastName() {
        User testSaveUser = new User();
        testSaveUser.setName("name_7");
//        testSaveUser.setSurname("sur_7");
        testSaveUser.setEmail("em_7@gmail.com");
        testSaveUser.setPassword("000");
        testSaveUser.setUserType(UserType.KITCHENER);

        MobileNumber num1 = new MobileNumber("093570568110");

        List<MobileNumber> set = new ArrayList<MobileNumber>();
        set.add(num1);
        testSaveUser.setMobileNumbers(set);
        userDao.saveOrUpdate(testSaveUser);
    }

    @Test(expected = PersistenceException.class)
    public void testSaveWithoutEmail() {
        User testSaveUser = new User();
        testSaveUser.setName("name_7");
        testSaveUser.setSurname("sur_7");
//        testSaveUser.setEmail("em_7@gmail.com");
        testSaveUser.setPassword("000");
        testSaveUser.setUserType(UserType.KITCHENER);

        MobileNumber num1 = new MobileNumber("093570568110");

        List<MobileNumber> set = new ArrayList<MobileNumber>();
        set.add(num1);
        testSaveUser.setMobileNumbers(set);

        userDao.saveOrUpdate(testSaveUser);
    }

    @Test(expected = PersistenceException.class)
    public void testSaveWithoutPassword() {
        User testSaveUser = new User();
        testSaveUser.setName("name_7");
        testSaveUser.setSurname("sur_7");
        testSaveUser.setEmail("em_7@gmail.com");
//        testSaveUser.setPassword("000");
        testSaveUser.setUserType(UserType.KITCHENER);

        MobileNumber num1 = new MobileNumber("093570568110");

        List<MobileNumber> set = new ArrayList<MobileNumber>();
        set.add(num1);
        testSaveUser.setMobileNumbers(set);

        userDao.saveOrUpdate(testSaveUser);
    }

    @Test(expected = PersistenceException.class)
    public void testSaveWithoutUserType() {
        User testSaveUser = new User();
        testSaveUser.setName("name_7");
        testSaveUser.setSurname("sur_7");
        testSaveUser.setEmail("em_7@gmail.com");
        testSaveUser.setPassword("000");
//        testSaveUser.setUserType(UserType.KITCHENER);

        MobileNumber num1 = new MobileNumber("093570568110");

        List<MobileNumber> set = new ArrayList<MobileNumber>();
        set.add(num1);
        testSaveUser.setMobileNumbers(set);

        userDao.saveOrUpdate(testSaveUser);
    }

    @Test
    @ExpectedDatabase(value = "/afterDeleteUser.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testDelete() throws Exception {
        userDao.delete(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteAbsentUser() throws Exception {
        userDao.delete(200);
    }

    @Test
    @ExpectedDatabase(value = "/updateUser.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @Transactional
    @Ignore
    public void testUpdate() throws Exception {
        User user = userDao.read(1);
        user.setName("olegka");
        user.setSurname("kakherskiy_jopt");
        user.setEmail("ol@gmail.com");
        userDao.saveOrUpdate(user);
    } //TODO: test update
}