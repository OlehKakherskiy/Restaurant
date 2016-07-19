package com.springapp.dao.daoImpl;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * @author Oleh Kakherskyi, IP-31, FICT, NTUU "KPI", olehkakherskiy@gmail.com
 */
@TestExecutionListeners({ResetDBAutoIncrementTestListener.class, TransactionDbUnitTestExecutionListener.class})
@ContextConfiguration("file:src/main/resources/app-context-xml.xml")
@ActiveProfiles("test")
@DbUnitConfiguration(databaseConnection = {"dbUnitDatabaseConnection"})
public class ApplicationDBUnitTest extends AbstractJUnit4SpringContextTests{
}
