package by.moseichuk.adlinker.service.impl;

import by.moseichuk.adlinker.bean.*;
import by.moseichuk.adlinker.constant.UserRole;
import by.moseichuk.adlinker.constant.UserStatus;
import by.moseichuk.adlinker.dao.exception.ConnectionPoolException;
import by.moseichuk.adlinker.dao.exception.TransactionException;
import by.moseichuk.adlinker.dao.pool.ConnectionPool;
import by.moseichuk.adlinker.dao.transaction.TransactionFactoryImpl;
import by.moseichuk.adlinker.service.exception.ServiceException;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;


public class ApplicationServiceImplTest {
    private static final String PROPERTIES_FILE = "db_config.properties";
    private Properties props;
    

    @BeforeTest
    public void setUp() throws IOException, ConnectionPoolException {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + PROPERTIES_FILE;
        props = new Properties();
        props.load(new FileInputStream(appConfigPath));

        ConnectionPool.getInstance().init(
                props.getProperty("db.class"),
                props.getProperty("db.url"),
                props.getProperty("db.username"),
                props.getProperty("db.password"),
                Integer.parseInt(props.getProperty("pool.start_size")),
                Integer.parseInt(props.getProperty("pool.size")),
                Integer.parseInt(props.getProperty("pool.connection_timeout")));
    }

    @AfterMethod
    public void tearDown() throws SQLException, IOException, ClassNotFoundException {
        ConnectionPool.getInstance().destroy();

        Class.forName(props.getProperty("db.class"));
        String mysqlUrl = props.getProperty("db.url");
        Connection con = DriverManager.getConnection(mysqlUrl, props.getProperty("db.username"), props.getProperty("db.password"));
        ScriptRunner sr = new ScriptRunner(con);
        Reader reader = new BufferedReader(new FileReader("src/test/resources/sql/create_test_db.sql"));
        sr.runScript(reader);
        con.close();
        reader.close();
        sr.closeConnection();
    }

    @AfterTest
    public void destroy() {
        ConnectionPool.getInstance().destroy();
    }

    @Test
    public void testAdd() throws ServiceException, TransactionException {
        ApplicationServiceImpl service = new ApplicationServiceImpl();
        TransactionFactoryImpl transactionFactory = new TransactionFactoryImpl();
        service.setTransaction(transactionFactory.createTransaction());
        service.add(new Application(1, new User(), "Some comment", new GregorianCalendar()));
        transactionFactory.close();
    }

    @Test
    public void testUpdate() throws ServiceException, TransactionException {
        ApplicationServiceImpl service = new ApplicationServiceImpl();
        TransactionFactoryImpl transactionFactory = new TransactionFactoryImpl();
        service.setTransaction(transactionFactory.createTransaction());
        service.update(new Application(313, new User(), "Some new comment", new GregorianCalendar()));
        transactionFactory.close();
    }

    @Test
    public void testReadAll() throws ServiceException, TransactionException {
        List<Application> testApplicationList = new ArrayList<>();
        Calendar date6 = new GregorianCalendar();
        date6.setTimeInMillis(1609491675000l);
        User user6 = new User(6, "influencer3@mail.ru", "$2y$12$zxhbiFdWbJNiVi2ij4kXuuv2iSb2iZteG4QcSqMiPCjFZPaQd264O", UserRole.INFLUENCER, date6,
                UserStatus.VERIFIED, null);
        Calendar date7 = new GregorianCalendar();
        date7.setTime(new Date(1609491675000l));
        User user7 = new User(7, "influencer4@mail.ru", "$2y$12$zxhbiFdWbJNiVi2ij4kXuuv2iSb2iZteG4QcSqMiPCjFZPaQd264O", UserRole.INFLUENCER, date7,
                UserStatus.VERIFIED, null);

        Calendar app6 = new GregorianCalendar();
        app6.setTimeInMillis(1608028523000l);
                                                       //2020-12-15 13:35:23
        testApplicationList.add(new Application(6, user6,"Прошу рассмотреть заявку. Звоните в любое время", app6));
        testApplicationList.add(new Application(7, user7, "Перспективный стример на платформе youtube", new GregorianCalendar(2020, Calendar.DECEMBER, 1, 18,1,3)));

        ApplicationServiceImpl service = new ApplicationServiceImpl();
        TransactionFactoryImpl transactionFactory = new TransactionFactoryImpl();
        service.setTransaction(transactionFactory.createTransaction());;
        List<Application> applicationList = service.readAll();
        transactionFactory.close();
        Assert.assertEquals(applicationList, testApplicationList);
    }
}