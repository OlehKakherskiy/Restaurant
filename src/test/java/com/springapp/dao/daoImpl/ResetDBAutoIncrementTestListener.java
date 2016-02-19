package com.springapp.dao.daoImpl;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;


/**
 * @author Oleh Kakherskyi, IP-31, FICT, NTUU "KPI", olehkakherskiy@gmail.com
 */
public class ResetDBAutoIncrementTestListener extends AbstractTestExecutionListener {

    private Connection c;

    private String preparedQueryString;

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        c = ((DataSource) testContext.getApplicationContext().getBean("dataSource")).getConnection();
        preparedQueryString = "ALTER TABLE %s AUTO_INCREMENT = %d";
    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        ResetPrimaryKeysToStartValues annotation = testContext.getTestMethod().
                getDeclaredAnnotation(ResetPrimaryKeysToStartValues.class);
        if (annotation == null || annotation.tables().length == 0 || annotation.startPrimaryKeyValues().length == 0 ||
                (annotation.tables().length != annotation.startPrimaryKeyValues().length))
            return;
        for (int i = 0; i < annotation.tables().length; i++) {
            executeStatement(annotation.tables()[i], annotation.startPrimaryKeyValues()[i]);
        }
    }

    private void executeStatement(String tableName, int initIdValue) throws Exception {
        Statement statement = c.createStatement();
        statement.execute(String.format(preparedQueryString, tableName, initIdValue));
        statement.close();
    }

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
        c.close();
    }

}
