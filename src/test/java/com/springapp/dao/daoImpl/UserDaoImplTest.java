package com.springapp.dao.daoImpl;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.springapp.dao.UserDao;
import com.springapp.entity.MobileNumber;
import com.springapp.entity.User;
import com.springapp.entity.enums.UserType;
import junit.framework.TestCase;
import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Oleh Kakherskyi, IP-31, FICT, NTUU "KPI", olehkakherskiy@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ServiceTestExecutionListener.class, DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class})
@ContextConfiguration("file:src/main/resources/app-context-xml.xml")
@ActiveProfiles("test")
public class UserDaoImplTest extends TestCase {

    @ExpectationField
    private IDataSet dataSet;

    @Autowired
    private IDatabaseTester dbTester;

    @Autowired
    private UserDao userDao;

    @DataSets(initDataSet = "src/test/java/com/springapp/dao/daoImpl/dbTestDataSets/userDataSet.xml")
    @Test
    public void testSelectById() throws Exception {
        User expect = new User();
        expect.setID(1);
        expect.setEmail("ol@gmail.com");
        expect.setName("oleh");
        expect.setSurname("kakherskiy");
        expect.setWorkMark((short) 0);
        expect.setPassword("123");
        expect.setUserType(UserType.ADMINISTRATOR);

        Set<MobileNumber> numbers = new HashSet<MobileNumber>();
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
        assertEquals(expect, userDao.selectById(1));
    }

    @DataSets(initDataSet = "src/test/java/com/springapp/dao/daoImpl/dbTestDataSets/userDataSet.xml", expectedDataSet = "src/test/java/com/springapp/dao/daoImpl/dbTestDataSets/insertUser_2mobileNumbers.xml")
    @Test
    public void testSaveWith2MobileNumbers() throws Exception {
        User testSaveUser = new User();
        testSaveUser.setName("name_7");
        testSaveUser.setEmail("sur_7");
        testSaveUser.setEmail("em_7@gmail.com");
        testSaveUser.setPassword("000");
        testSaveUser.setUserType(UserType.CLIENT);

        MobileNumber num1 = new MobileNumber("093570568110");
        MobileNumber num2 = new MobileNumber("093570568111");

        Set<MobileNumber> set = new HashSet<MobileNumber>();
        set.add(num1);
        set.add(num2);
        testSaveUser.setMobileNumbers(set);
    }
//
//    @DataSets(initDataSet = "/dbTestDataSets/userDataSet.xml", expectedDataSet = "/dbTestDataSets/insertUser_allDataFilled.xml")
//    @Test
//    @Ignore
//    public void testSaveAllDataFilled() throws Exception {
//        User testSaveUser = new User();
//        testSaveUser.setName("name_7");
//        testSaveUser.setEmail("sur_7");
//        testSaveUser.setEmail("em_7@gmail.com");
//        testSaveUser.setPassword("000");
//        testSaveUser.setUserType(UserType.CLIENT);
//
//        MobileNumber num1 = new MobileNumber("093570568110");
//
//        Set<MobileNumber> set = new HashSet<MobileNumber>();
//        set.add(num1);
//        testSaveUser.setMobileNumbers(set);
//        userDao.save(testSaveUser);
//
//        assertNotNull("User object isn't returned after persisting operation", testSaveUser);
//        assertNotNull("User's object ID isn't initialised after persisting operation", testSaveUser.getID());
//
//        IDataSet actualData = dbTester.getConnection().createDataSet();
//        String[] ignoreUserCol = {"userID"};
//        String[] ignoreMobCol = {"mobileNumberID"};
//        Assertion.assertEqualsIgnoreCols(dataSet, actualData, "User", ignoreUserCol);
//        Assertion.assertEqualsIgnoreCols(dataSet, actualData, "mobileNumber", ignoreMobCol);
//    }

//    public void testDelete() throws Exception {
//
//    }
//
//    public void testUpdate() throws Exception {
//
//    }
}