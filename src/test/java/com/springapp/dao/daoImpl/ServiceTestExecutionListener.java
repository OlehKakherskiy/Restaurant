package com.springapp.dao.daoImpl;

import org.dbunit.IDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

import java.io.File;
import java.lang.reflect.Field;

/**
 * @author Oleh Kakherskyi, IP-31, FICT, NTUU "KPI", olehkakherskiy@gmail.com
 */
public class ServiceTestExecutionListener implements TestExecutionListener {

    IDatabaseTester dbTester;

    Field expectedAnnotatedField;

    FlatXmlDataSetBuilder xmlDataSetBuilder;

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        Field[] fields = testContext.getTestClass().getDeclaredFields();
        for (Field f : fields)
            if (f.getDeclaredAnnotation(ExpectationField.class) != null && f.getType() == IDataSet.class) {
                expectedAnnotatedField = f;
            }
        xmlDataSetBuilder = new FlatXmlDataSetBuilder();
    }

    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception {
    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        DataSets sets = testContext.getTestMethod().getAnnotation(DataSets.class);
        if (sets == null || sets.initDataSet().equals(""))
            return;

        dbTester = (IDatabaseTester) testContext.getApplicationContext().getBean("databaseTester");
        dbTester.setSchema("restaurant");
        dbTester.getConnection().getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());

        File f = new File(sets.initDataSet());
        System.out.println(f.exists());
        dbTester.setDataSet(xmlDataSetBuilder.build(new File(sets.initDataSet())));
        if (!(sets.expectedDataSet().equals("") || expectedAnnotatedField == null))
            PropertyAccessorFactory.forBeanPropertyAccess(testContext.getTestInstance()).setPropertyValue(expectedAnnotatedField.getName(), xmlDataSetBuilder.build(
                    new File(sets.expectedDataSet())));
        dbTester.onSetup();
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        dbTester.onTearDown();
    }


    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
    }
}
