package com.pearson.statsagg.webui;

import com.pearson.statsagg.web_ui.MetricGroupsLogic;
import java.util.List;
import java.util.TreeSet;
import com.pearson.statsagg.database_objects.metric_groups.MetricGroup;
import com.pearson.statsagg.database_objects.metric_groups.MetricGroupsDao;
import com.pearson.statsagg.database_objects.metric_group_regexes.MetricGroupRegex;
import com.pearson.statsagg.database_objects.metric_group_regexes.MetricGroupRegexesDao;
import com.pearson.statsagg.database_objects.metric_group_tags.MetricGroupTag;
import com.pearson.statsagg.database_objects.metric_group_tags.MetricGroupTagsDao;
import com.pearson.statsagg.globals.DatabaseConnections;
import com.pearson.statsagg.drivers.Driver;
import com.pearson.statsagg.utilities.db_utils.DatabaseUtils;
import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Jeffrey Schmidt
 */
public class MetricGroupsLogicTest {
    
    private final MetricGroupsLogic metricGroupsLogic_ = new MetricGroupsLogic();

    public MetricGroupsLogicTest() {
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
        // delete a metric group that was inserted into the database from a previous test. verify that it was deleted.
        String result = metricGroupsLogic_.deleteRecordInDatabase("metricgroup junit_test");
        assertTrue(result.contains("success") || result.contains("Metric group not found"));
        
        // delete a metric group that was inserted into the database from a previous test. verify that it was deleted.
        result = metricGroupsLogic_.deleteRecordInDatabase("metricgroup junit_test_11");
        assertTrue(result.contains("success") || result.contains("Metric group not found"));
    }
    
    @After
    public void tearDown() {

    }

    /**
     * Test of alterRecordInDatabase method, of class MetricGroupsLogic.
     */
    @Test
    public void testAlterRecordInDatabase() {
        MetricGroup metricGroup1 = new MetricGroup(-1, "metricgroup junit_test", "this is a junit test 1");
        TreeSet<String> matchRegexes1 = new TreeSet<>();
        matchRegexes1.add(".*junit_1_1.*");
        matchRegexes1.add(".*junit_1_2.*");
        TreeSet<String> tags1 = new TreeSet<>();
        tags1.add("tag1_1");
        tags1.add("tag1_2");
        
        String result = metricGroupsLogic_.alterRecordInDatabase(metricGroup1, null, null, null);
        assertTrue(result.contains("Fail"));
        
        result = metricGroupsLogic_.alterRecordInDatabase(null, matchRegexes1, null, tags1);
        assertTrue(result.contains("Fail"));
        
        // create a metric group & insert it into the db
        result = metricGroupsLogic_.alterRecordInDatabase(metricGroup1, matchRegexes1, null, tags1);
        assertTrue(result.contains("Success"));

        // check to see that the metric group is in the database & has correct values
        MetricGroup metricGroupFromDb = MetricGroupsDao.getMetricGroup(DatabaseConnections.getConnection(), true, "metricgroup junit_test");
        assertTrue(metricGroupFromDb.getDescription().equals("this is a junit test 1"));

        // check to see that the metric group's regexes made it into the db
        List<MetricGroupRegex> metricGroupRegexes = MetricGroupRegexesDao.getMetricGroupRegexesByMetricGroupId(DatabaseConnections.getConnection(), true, metricGroupFromDb.getId());
        for (MetricGroupRegex metricGroupRegex : metricGroupRegexes) assertTrue(matchRegexes1.contains(metricGroupRegex.getPattern()));
        assertEquals(matchRegexes1.size(), metricGroupRegexes.size());
        
        // check to see that the metric group's tags made it into the db
        List<MetricGroupTag> metricGroupTags = MetricGroupTagsDao.getMetricGroupTagsByMetricGroupId(DatabaseConnections.getConnection(), true, metricGroupFromDb.getId());
        for (MetricGroupTag metricGroupTag : metricGroupTags) assertTrue(tags1.contains(metricGroupTag.getTag()));
        assertEquals(tags1.size(), metricGroupTags.size());
 
        // test altering metric group name
        Connection connection = DatabaseConnections.getConnection();
        MetricGroup metricGroupFromDbOriginalName = MetricGroupsDao.getMetricGroup(connection, false, "metricgroup junit_test"); // pt1
        assertTrue(metricGroupFromDbOriginalName.getName().contains("metricgroup junit_test"));
        MetricGroup metricGroupFromDbNewName = MetricGroup.copy(metricGroupFromDbOriginalName);
        metricGroupFromDbNewName.setName("metricgroup junit_test_11");
        result = metricGroupsLogic_.alterRecordInDatabase(metricGroupFromDbNewName, matchRegexes1, null, tags1, metricGroupFromDbNewName.getName());
        assertTrue(result.contains("Successful"));
        MetricGroup metricGroupFromDbNewNameVerify = MetricGroupsDao.getMetricGroup(connection, false, metricGroupFromDbNewName.getName()); // pt2
        assertTrue(metricGroupFromDbNewNameVerify.getName().contains(metricGroupFromDbNewName.getName()));
        assertFalse(metricGroupFromDbNewNameVerify.getName().equals(metricGroupFromDbOriginalName.getName()));
        assertEquals(metricGroupFromDbOriginalName.getId(), metricGroupFromDbNewNameVerify.getId());
        MetricGroup metricGroupFromDbOriginalName_NoResult = MetricGroupsDao.getMetricGroup(connection, false, metricGroupFromDbOriginalName.getName()); // pt3
        assertEquals(metricGroupFromDbOriginalName_NoResult, null);
        MetricGroup metricGroupFromDbOriginalName_Reset = MetricGroup.copy(metricGroupFromDbOriginalName); // pt4
        metricGroupFromDbOriginalName_Reset.setName(metricGroupFromDbOriginalName.getName());  
        result = metricGroupsLogic_.alterRecordInDatabase(metricGroupFromDbOriginalName_Reset, matchRegexes1, null, tags1, metricGroupFromDbOriginalName.getName());
        assertTrue(result.contains("Successful"));
        DatabaseUtils.cleanup(connection);
        
        // create a second metric group & insert it into the db. also check to see if inserting blacklist regexes works.
        MetricGroup metricGroup2 = new MetricGroup(-1, "metricgroup junit_test", "this is a junit test 2");
        TreeSet<String> matchRegexes2 = new TreeSet<>();
        matchRegexes2.add(".*junit_2_2.*");
        matchRegexes2.add(".*junit_2_1.*");
        matchRegexes2.add(".*junit_2_3.*");
        TreeSet<String> blacklistRegexes2 = new TreeSet<>();
        blacklistRegexes2.add(".*blaclist1.*");
        blacklistRegexes2.add(".*blaclist2.*");
        TreeSet<String> tags2 = new TreeSet<>();
        tags2.add("tag2_1");
        tags2.add("tag2_2");
        tags2.add("tag2_3");
        result = metricGroupsLogic_.alterRecordInDatabase(metricGroup2, matchRegexes2, blacklistRegexes2, tags2);
        assertTrue(result.contains("Fail"));
        result = metricGroupsLogic_.alterRecordInDatabase(metricGroup2, matchRegexes2, blacklistRegexes2, tags2, metricGroup2.getName());
        assertTrue(result.contains("Success"));
        
        // check to see that the second metric group is in the database & has correct values
        metricGroupFromDb = MetricGroupsDao.getMetricGroup(DatabaseConnections.getConnection(), true, "metricgroup junit_test");
        Integer metricGroupFromDb2 = metricGroupFromDb.getId();
        assertTrue(metricGroupFromDb.getDescription().equals("this is a junit test 2"));
        
        // check to see that the second metric group's regexes made it into the db & that we didn't accidentially associate with other regexes 
        metricGroupRegexes = MetricGroupRegexesDao.getMetricGroupRegexesByMetricGroupId(DatabaseConnections.getConnection(), true,metricGroupFromDb.getId());
        for (MetricGroupRegex metricGroupRegex : metricGroupRegexes) {
            if (!metricGroupRegex.isBlacklistRegex()) {
                assertTrue(matchRegexes2.contains(metricGroupRegex.getPattern()));
                assertFalse(matchRegexes1.contains(metricGroupRegex.getPattern()));
            }
            else {
                assertTrue(blacklistRegexes2.contains(metricGroupRegex.getPattern()));
            }
        }
        assertEquals(matchRegexes2.size() + blacklistRegexes2.size(), metricGroupRegexes.size());
        assertEquals(matchRegexes2.first(), ".*junit_2_1.*");
        
        // check to see that the second metric group's tags made it into the db & that we didn't accidentially associate with other tags 
        metricGroupTags = MetricGroupTagsDao.getMetricGroupTagsByMetricGroupId(DatabaseConnections.getConnection(), true, metricGroupFromDb.getId());
        for (MetricGroupTag metricGroupTag : metricGroupTags) {
            assertTrue(tags2.contains(metricGroupTag.getTag()));
            assertFalse(tags1.contains(metricGroupTag.getTag()));
        }
        assertEquals(tags2.size(), metricGroupTags.size());
        assertEquals(tags2.first(),"tag2_1");
        
        // cleanup
        metricGroupsLogic_.deleteRecordInDatabase("metricgroup junit_test");
        metricGroupFromDb = MetricGroupsDao.getMetricGroup(DatabaseConnections.getConnection(), true, "metricgroup junit_test");
        assertEquals(null, metricGroupFromDb);
        metricGroupRegexes = MetricGroupRegexesDao.getMetricGroupRegexesByMetricGroupId(DatabaseConnections.getConnection(), true, metricGroupFromDb2);
        assertEquals(0, metricGroupRegexes.size());
    }
    
    /**
     * Test of deleteRecordInDatabase method, of class MetricGroupsLogic.
     */
    @Test
    public void testDeleteRecordInDatabase() {
        // create a metric group & insert it into the db
        MetricGroup metricGroup1 = new MetricGroup(-1, "metricgroup junit_test_delete", "this is a junit test of delete");
        TreeSet<String> matchRegexes1 = new TreeSet<>();
        matchRegexes1.add(".*junit_delete_1_1.*");
        matchRegexes1.add(".*junit_delete_1_2.*");
        TreeSet<String> blacklistRegexes1 = new TreeSet<>();
        blacklistRegexes1.add(".*blaclist1.*");
        blacklistRegexes1.add(".*blaclist2.*");
        TreeSet<String> tags1 = new TreeSet<>();
        tags1.add("tag1_1");
        tags1.add("tag1_2");
        String result = metricGroupsLogic_.alterRecordInDatabase(metricGroup1, matchRegexes1, blacklistRegexes1, tags1);
        assertTrue(result.contains("Success"));
        
        // check to see that the metric group is in the database & has correct values
        MetricGroup metricGroupFromDb = MetricGroupsDao.getMetricGroup(DatabaseConnections.getConnection(), true, "metricgroup junit_test_delete");
        Integer metricGroupIdFromDb = metricGroupFromDb.getId();
        assertTrue(metricGroupFromDb.getDescription().equals("this is a junit test of delete"));
        
        // check to see that the metric group's regexes made it into the db
        List<MetricGroupRegex> metricGroupRegexes = MetricGroupRegexesDao.getMetricGroupRegexesByMetricGroupId(DatabaseConnections.getConnection(), true, metricGroupFromDb.getId());
        assertEquals(matchRegexes1.size() + blacklistRegexes1.size(), metricGroupRegexes.size());
        
        // check to see that the metric group's tags made it into the db
        List<MetricGroupTag> metricGroupTags = MetricGroupTagsDao.getMetricGroupTagsByMetricGroupId(DatabaseConnections.getConnection(), true, metricGroupFromDb.getId());
        assertEquals(tags1.size(), metricGroupTags.size());
        
        // delete the metric group & verify that everything is deleted (metric group, regexes, tags)
        result = metricGroupsLogic_.deleteRecordInDatabase("metricgroup junit_test_delete");
        assertTrue(result.contains("success"));
        metricGroupFromDb = MetricGroupsDao.getMetricGroup(DatabaseConnections.getConnection(), true, "metricgroup junit_test_delete");
        assertEquals(null, metricGroupFromDb);
        metricGroupRegexes = MetricGroupRegexesDao.getMetricGroupRegexesByMetricGroupId(DatabaseConnections.getConnection(), true, metricGroupIdFromDb);
        assertEquals(0, metricGroupRegexes.size());
        metricGroupTags = MetricGroupTagsDao.getMetricGroupTagsByMetricGroupId(DatabaseConnections.getConnection(), true, metricGroupIdFromDb);
        assertEquals(0, metricGroupTags.size());
        
        result = metricGroupsLogic_.deleteRecordInDatabase("metricgroup junit_test_delete");
        assertTrue(result.contains("not found"));
        
        result = metricGroupsLogic_.deleteRecordInDatabase("metricgroup junit_test_this_doesnt_exist");
        assertTrue(result.contains("not found"));
    }

    
}
