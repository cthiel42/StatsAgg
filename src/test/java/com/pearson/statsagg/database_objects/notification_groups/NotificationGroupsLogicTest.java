package com.pearson.statsagg.database_objects.notification_groups;

import com.pearson.statsagg.database_objects.notification_groups.NotificationGroupsLogic;
import com.pearson.statsagg.database_objects.notification_groups.NotificationGroup;
import com.pearson.statsagg.database_objects.notification_groups.NotificationGroupsDao;
import com.pearson.statsagg.globals.DatabaseConnections;
import com.pearson.statsagg.drivers.Driver;
import com.pearson.statsagg.utilities.db_utils.DatabaseUtils;
import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Jeffrey Schmidt
 */
public class NotificationGroupsLogicTest {
    
    private final NotificationGroupsLogic notificationGroupsLogic_ = new NotificationGroupsLogic();

    public NotificationGroupsLogicTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Driver.initializeApplication_Logger();
        Driver.initializeApplication_DatabaseConfiguration(true);
        Driver.connectToDatabase();
        Driver.setupDatabaseSchema();
    }
    
    @AfterClass
    public static void tearDownClass() {
        DatabaseConnections.disconnectAndShutdown();
    }
    
    @Before
    public void setUp() {
        // delete notification that was inserted into the database from a previous test. verify that it was deleted.
        String result = notificationGroupsLogic_.deleteRecordInDatabase("notification junit name 1");
        assertTrue(result.contains("success") || result.contains("Notification group not found"));
        
        // delete a notification that was inserted into the database from a previous test. verify that it was deleted.
        result = notificationGroupsLogic_.deleteRecordInDatabase("notification junit name 11");
        assertTrue(result.contains("success") || result.contains("Notification group not found"));
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of alterRecordInDatabase method, of class NotificationGroupsLogic.
     */
    @Test
    public void testAlterRecordInDatabase() {
        // cleanup from previous unit test
        String result = notificationGroupsLogic_.deleteRecordInDatabase("notification junit name 1");
        assertTrue(result.contains("success") || result.contains("Notification group not found"));
        
        // create & insert a NotificationGroup, insert it into the db, retrieve it from the db, & compare the original & retrieved records
        NotificationGroup notification1 = new NotificationGroup(-1, "notification junit name 1", "notification junit email 1", null);   
        result = notificationGroupsLogic_.alterRecordInDatabase(notification1);
        assertTrue(result.contains("Success"));
        NotificationGroup notification1FromDb = NotificationGroupsDao.getNotificationGroup(DatabaseConnections.getConnection(), true, "notification junit name 1");
        assertTrue(notification1FromDb.getName().contains("notification junit name 1"));
        assertTrue(notification1FromDb.getEmailAddresses().contains("notification junit email 1"));
        
        notification1FromDb.setEmailAddresses("notification junit email 1_2");
        result = notificationGroupsLogic_.alterRecordInDatabase(notification1FromDb);
        assertTrue(result.contains("Fail"));
        result = notificationGroupsLogic_.alterRecordInDatabase(notification1FromDb, notification1FromDb.getName());
        assertTrue(result.contains("Success"));
        NotificationGroup notification2FromDb = NotificationGroupsDao.getNotificationGroup(DatabaseConnections.getConnection(), true, "notification junit name 1");
        assertTrue(notification2FromDb.getName().contains("notification junit name 1"));
        assertTrue(notification2FromDb.getEmailAddresses().contains("notification junit email 1_2"));
        
        result = notificationGroupsLogic_.deleteRecordInDatabase("notification junit fake 1");
        assertTrue(result.contains("Cancelling"));
        NotificationGroup notification3FromDb = NotificationGroupsDao.getNotificationGroup(DatabaseConnections.getConnection(), true, "notification junit name 1");
        assertTrue(notification3FromDb.getName().contains("notification junit name 1"));
        
        // test altering metric group name
        Connection connection = DatabaseConnections.getConnection();
        NotificationGroup notificationGroupFromDbOriginalName = NotificationGroupsDao.getNotificationGroup(connection, false, "notification junit name 1"); // pt1
        assertTrue(notificationGroupFromDbOriginalName.getName().contains("notification junit name 1"));
        NotificationGroup notificationGroupFromDbNewName = NotificationGroup.copy(notificationGroupFromDbOriginalName);
        notificationGroupFromDbNewName.setName("notification junit name 11");
        result = notificationGroupsLogic_.alterRecordInDatabase(notificationGroupFromDbNewName, notificationGroupFromDbNewName.getName());
        assertTrue(result.contains("Successful"));
        NotificationGroup notificationGroupFromDbNewNameVerify = NotificationGroupsDao.getNotificationGroup(DatabaseConnections.getConnection(), true, notificationGroupFromDbNewName.getName()); // pt2
        assertTrue(notificationGroupFromDbNewNameVerify.getName().contains(notificationGroupFromDbNewName.getName()));
        assertFalse(notificationGroupFromDbNewNameVerify.getName().equals(notificationGroupFromDbOriginalName.getName()));
        assertEquals(notificationGroupFromDbOriginalName.getId(), notificationGroupFromDbNewNameVerify.getId());
        NotificationGroup notificationGroupFromDbOriginalName_NoResult = NotificationGroupsDao.getNotificationGroup(DatabaseConnections.getConnection(), true, notificationGroupFromDbOriginalName.getName()); // pt3
        assertEquals(notificationGroupFromDbOriginalName_NoResult, null);
        NotificationGroup notificationGroupFromDbOriginalName_Reset = NotificationGroup.copy(notificationGroupFromDbOriginalName); // pt4
        notificationGroupFromDbOriginalName_Reset.setName(notificationGroupFromDbOriginalName.getName());  
        result = notificationGroupsLogic_.alterRecordInDatabase(notificationGroupFromDbOriginalName_Reset, notificationGroupFromDbOriginalName.getName());
        assertTrue(result.contains("Successful"));
        DatabaseUtils.cleanup(connection);
        
        result = notificationGroupsLogic_.deleteRecordInDatabase("notification junit name 1");
        assertTrue(result.contains("success"));
        NotificationGroup notification4FromDb = NotificationGroupsDao.getNotificationGroup(DatabaseConnections.getConnection(), true, "notification junit name 1");
        assertEquals(null, notification4FromDb);
    }

    /**
     * Test of deleteRecordInDatabase method, of class NotificationGroupsLogic.
     */
    @Test
    public void testDeleteRecordInDatabase() {
        String result = notificationGroupsLogic_.deleteRecordInDatabase("notification junit name 1");
        assertTrue(result.contains("success") || result.contains("Notification group not found"));
        
        NotificationGroup notification1 = new NotificationGroup(-1, "notification junit name 1", "notification junit email 1", null);   
        result = notificationGroupsLogic_.alterRecordInDatabase(notification1);
        assertTrue(result.contains("Success"));
        NotificationGroup notification1FromDb = NotificationGroupsDao.getNotificationGroup(DatabaseConnections.getConnection(), true, "notification junit name 1");
        assertTrue(notification1FromDb.getName().contains("notification junit name 1"));
        assertTrue(notification1FromDb.getEmailAddresses().contains("notification junit email 1"));
        
        result = notificationGroupsLogic_.deleteRecordInDatabase("notification junit name 1");
        assertTrue(result.contains("success"));
        NotificationGroup notification2FromDb = NotificationGroupsDao.getNotificationGroup(DatabaseConnections.getConnection(), true, "notification junit name 1");
        assertEquals(null, notification2FromDb);
        
        result = notificationGroupsLogic_.deleteRecordInDatabase("notification junit fake 1");
        assertTrue(result.contains("Cancelling"));
    }
    
}
