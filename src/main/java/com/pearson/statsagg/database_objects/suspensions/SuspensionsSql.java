package com.pearson.statsagg.database_objects.suspensions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jeffrey Schmidt
 */
public class SuspensionsSql {
    
    private static final Logger logger = LoggerFactory.getLogger(SuspensionsSql.class.getName());
    
    protected final static String DropTable_Suspensions = 
                    "DROP TABLE SUSPENSIONS";
    
    protected final static String CreateTable_Suspensions_Derby =  
                    "CREATE TABLE SUSPENSIONS (" + 
                    "ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " + 
                    "NAME VARCHAR(500) NOT NULL, " + 
                    "UPPERCASE_NAME VARCHAR(500) NOT NULL, " + 
                    "DESCRIPTION CLOB(1048576) NOT NULL, " + 
                    "IS_ENABLED BOOLEAN NOT NULL, " + 
                    "SUSPEND_BY INTEGER NOT NULL, " + 
                    "ALERT_ID INTEGER, " +
                    "METRIC_GROUP_TAGS_INCLUSIVE CLOB(1048576), " + 
                    "METRIC_GROUP_TAGS_EXCLUSIVE CLOB(1048576), " + 
                    "METRIC_SUSPENSION_REGEXES CLOB(1048576), " +
                    "IS_ONE_TIME BOOLEAN NOT NULL, " + 
                    "IS_SUSPEND_NOTIFICATION_ONLY BOOLEAN NOT NULL, " + 
                    "IS_RECUR_SUNDAY BOOLEAN NOT NULL, " + 
                    "IS_RECUR_MONDAY BOOLEAN NOT NULL, " + 
                    "IS_RECUR_TUESDAY BOOLEAN NOT NULL, " + 
                    "IS_RECUR_WEDNESDAY BOOLEAN NOT NULL, " + 
                    "IS_RECUR_THURSDAY BOOLEAN NOT NULL, " + 
                    "IS_RECUR_FRIDAY BOOLEAN NOT NULL, " + 
                    "IS_RECUR_SATURDAY BOOLEAN NOT NULL, " +
                    "START_DATE TIMESTAMP NOT NULL, " + 
                    "START_TIME TIMESTAMP NOT NULL, " +
                    "DURATION BIGINT NOT NULL, " + 
                    "DURATION_TIME_UNIT INTEGER NOT NULL, " + 
                    "DELETE_AT_TIMESTAMP TIMESTAMP " + 
                    ")";
    
    protected final static String CreateTable_Suspensions_MySQL =  
                    "CREATE TABLE SUSPENSIONS (" + 
                    "ID INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL, " + 
                    "NAME VARCHAR(500) NOT NULL, " + 
                    "UPPERCASE_NAME VARCHAR(500) NOT NULL, " + 
                    "DESCRIPTION MEDIUMTEXT NOT NULL, " + 
                    "IS_ENABLED BOOLEAN NOT NULL, " + 
                    "SUSPEND_BY INTEGER NOT NULL, " + 
                    "ALERT_ID INTEGER, " +
                    "METRIC_GROUP_TAGS_INCLUSIVE MEDIUMTEXT, " + 
                    "METRIC_GROUP_TAGS_EXCLUSIVE MEDIUMTEXT, " + 
                    "METRIC_SUSPENSION_REGEXES MEDIUMTEXT, " +
                    "IS_ONE_TIME BOOLEAN NOT NULL, " + 
                    "IS_SUSPEND_NOTIFICATION_ONLY BOOLEAN NOT NULL, " + 
                    "IS_RECUR_SUNDAY BOOLEAN NOT NULL, " + 
                    "IS_RECUR_MONDAY BOOLEAN NOT NULL, " + 
                    "IS_RECUR_TUESDAY BOOLEAN NOT NULL, " + 
                    "IS_RECUR_WEDNESDAY BOOLEAN NOT NULL, " + 
                    "IS_RECUR_THURSDAY BOOLEAN NOT NULL, " + 
                    "IS_RECUR_FRIDAY BOOLEAN NOT NULL, " + 
                    "IS_RECUR_SATURDAY BOOLEAN NOT NULL, " +
                    "START_DATE TIMESTAMP NULL DEFAULT NULL, " + 
                    "START_TIME TIMESTAMP NULL DEFAULT NULL, " +
                    "DURATION BIGINT NOT NULL, " + 
                    "DURATION_TIME_UNIT INTEGER NOT NULL, " + 
                    "DELETE_AT_TIMESTAMP TIMESTAMP NULL DEFAULT NULL" + 
                    ") " +
                    "ROW_FORMAT=DYNAMIC";
    
    protected final static String CreateIndex_Suspensions_PrimaryKey =
                    "ALTER TABLE SUSPENSIONS " +
                    "ADD CONSTRAINT AS_PK PRIMARY KEY (ID)";
    
    protected final static String CreateIndex_Suspensions_Unique_Name =
                    "ALTER TABLE SUSPENSIONS ADD CONSTRAINT AS_U_NAME UNIQUE (" + 
                    "NAME" + 
                    ")";

    protected final static String CreateIndex_Suspensions_Unique_UppercaseName =
                    "ALTER TABLE SUSPENSIONS ADD CONSTRAINT AS_U_UPPERCASE_NAME UNIQUE (" + 
                    "UPPERCASE_NAME" + 
                    ")";
    
    protected final static String CreateIndex_Suspensions_SuspendBy =
                    "CREATE INDEX AS_SUSPEND_BY ON SUSPENSIONS(SUSPEND_BY)";

    protected final static String CreateIndex_Suspensions_DeleteAtTimestamp =
                    "CREATE INDEX AS_DELETE_AT_TIMESTAMP ON SUSPENSIONS(DELETE_AT_TIMESTAMP)";
    
    protected final static String CreateIndex_Suspensions_ForeignKey_AlertId =
                    "ALTER TABLE SUSPENSIONS " +
                    "ADD CONSTRAINT AS_AID_FK FOREIGN KEY (ALERT_ID) " + 
                    "REFERENCES ALERTS(ID)";
    
    protected final static String Select_Suspension_ByPrimaryKey = 
                    "SELECT * FROM SUSPENSIONS " +
                    "WHERE ID = ?";
    
    protected final static String Select_Suspension_ByName = 
                    "SELECT * FROM SUSPENSIONS " +
                    "WHERE NAME = ?";
    
    protected final static String Select_AllSuspensions = 
                    "SELECT * FROM SUSPENSIONS";
    
    protected final static String Select_SuspensionId_BySuspendBy = 
                    "SELECT ID FROM SUSPENSIONS " +
                    "WHERE SUSPEND_BY = ?";
    
    protected final static String Select_Suspension_BySuspendBy = 
                    "SELECT * FROM SUSPENSIONS " +
                    "WHERE SUSPEND_BY = ?";
    
    protected final static String Insert_Suspension =
                    "INSERT INTO SUSPENSIONS " +
                    "(NAME, UPPERCASE_NAME, DESCRIPTION, IS_ENABLED, SUSPEND_BY, ALERT_ID, METRIC_GROUP_TAGS_INCLUSIVE, METRIC_GROUP_TAGS_EXCLUSIVE, METRIC_SUSPENSION_REGEXES, " + 
                    "IS_ONE_TIME, IS_SUSPEND_NOTIFICATION_ONLY, " +
                    "IS_RECUR_SUNDAY, IS_RECUR_MONDAY, IS_RECUR_TUESDAY, IS_RECUR_WEDNESDAY, IS_RECUR_THURSDAY, IS_RECUR_FRIDAY, IS_RECUR_SATURDAY, " +
                    "START_DATE, START_TIME, DURATION, DURATION_TIME_UNIT, DELETE_AT_TIMESTAMP) " +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    protected final static String Update_Suspension_ByPrimaryKey =
                    "UPDATE SUSPENSIONS " +
                    "SET NAME = ?, UPPERCASE_NAME = ?, DESCRIPTION = ?, IS_ENABLED = ?, SUSPEND_BY = ?, ALERT_ID = ?, " +
                    "METRIC_GROUP_TAGS_INCLUSIVE = ?, METRIC_GROUP_TAGS_EXCLUSIVE = ?, METRIC_SUSPENSION_REGEXES = ?, IS_ONE_TIME = ?, IS_SUSPEND_NOTIFICATION_ONLY = ?, " + 
                    "IS_RECUR_SUNDAY = ?, IS_RECUR_MONDAY = ?, IS_RECUR_TUESDAY = ?, IS_RECUR_WEDNESDAY = ?, " +
                    "IS_RECUR_THURSDAY = ?, IS_RECUR_FRIDAY = ?, IS_RECUR_SATURDAY = ?, " +
                    "START_DATE = ?, START_TIME = ?, DURATION = ?, DURATION_TIME_UNIT = ?, DELETE_AT_TIMESTAMP = ? " +
                    "WHERE ID = ?";
    
    protected final static String Delete_Suspension_ByPrimaryKey =
                    "DELETE FROM SUSPENSIONS " +
                    "WHERE ID = ?";
    
    protected final static String Delete_Suspension_DeleteAtTimestamp =
                    "DELETE FROM SUSPENSIONS " +
                    "WHERE DELETE_AT_TIMESTAMP <= ?";
    
    protected final static String Select_Suspension_ByPageNumberAndPageSize_Derby = 
                    "SELECT ID, NAME FROM SUSPENSIONS ORDER BY ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

    protected final static String Select_Suspension_ByPageNumberAndPageSize_MySQL = 
                    "SELECT ID, NAME FROM SUSPENSIONS ORDER BY ID ASC LIMIT ?,?";
}
