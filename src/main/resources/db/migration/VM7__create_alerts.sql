CREATE TABLE ALERTS ( 
	ID INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,  
	NAME VARCHAR(500) NOT NULL,  
	UPPERCASE_NAME VARCHAR(500) NOT NULL,  
	DESCRIPTION MEDIUMTEXT,  
	METRIC_GROUP_ID INTEGER NOT NULL, 
	IS_ENABLED BOOLEAN NOT NULL, 
	IS_CAUTION_ENABLED BOOLEAN NOT NULL, 
	IS_DANGER_ENABLED BOOLEAN NOT NULL, 
	ALERT_TYPE INTEGER NOT NULL, 
	ALERT_ON_POSITIIVE BOOLEAN NOT NULL,  
	ALLOW_RESEND_ALERT BOOLEAN NOT NULL,  
	RESEND_ALERT_EVERY BIGINT,  
	RESEND_ALERT_EVERY_TIME_UNIT INTEGER,  
	CAUTION_NOTIFICATION_GROUP_ID INTEGER, 
	CAUTION_POSITIVE_NOTIFICATION_GROUP_ID INTEGER, 
	CAUTION_OPERATOR INTEGER,  
	CAUTION_COMBINATION INTEGER,  
	CAUTION_COMBINATION_COUNT INTEGER,  
	CAUTION_THRESHOLD DECIMAL(65,7),  
	CAUTION_WINDOW_DURATION BIGINT,  
	CAUTION_WINDOW_DURATION_TIME_UNIT INTEGER,  
	CAUTION_STOP_TRACKING_AFTER BIGINT, 
	CAUTION_STOP_TRACKING_AFTER_TIME_UNIT INTEGER, 
	CAUTION_MINIMUM_SAMPLE_COUNT INTEGER,  
	IS_CAUTION_ALERT_ACTIVE BOOLEAN NOT NULL, 
	CAUTION_ALERT_LAST_SENT_TIMESTAMP TIMESTAMP NULL DEFAULT NULL, 
	IS_CAUTION_ACKNOWLEDGED BOOLEAN, 
	CAUTION_ACTIVE_ALERTS_SET MEDIUMTEXT, 
	CAUTION_FIRST_ACTIVE_AT TIMESTAMP NULL DEFAULT NULL,  
	DANGER_NOTIFICATION_GROUP_ID INTEGER, 
	DANGER_POSITIVE_NOTIFICATION_GROUP_ID INTEGER, 
	DANGER_OPERATOR INTEGER,  
	DANGER_COMBINATION INTEGER,  
	DANGER_COMBINATION_COUNT INTEGER,  
	DANGER_THRESHOLD DECIMAL(65,7),  
	DANGER_WINDOW_DURATION BIGINT,  
	DANGER_WINDOW_DURATION_TIME_UNIT INTEGER,  
	DANGER_STOP_TRACKING_AFTER BIGINT, 
	DANGER_STOP_TRACKING_AFTER_TIME_UNIT INTEGER, 
	DANGER_MINIMUM_SAMPLE_COUNT INTEGER,  
	IS_DANGER_ALERT_ACTIVE BOOLEAN NOT NULL, 
	DANGER_ALERT_LAST_SENT_TIMESTAMP TIMESTAMP NULL DEFAULT NULL, 
	IS_DANGER_ACKNOWLEDGED BOOLEAN, 
	DANGER_ACTIVE_ALERTS_SET MEDIUMTEXT, 
	DANGER_FIRST_ACTIVE_AT TIMESTAMP NULL DEFAULT NULL 
);

ALTER TABLE ALERTS 
ADD CONSTRAINT A_U_NAME UNIQUE (NAME);

ALTER TABLE ALERTS 
ADD CONSTRAINT A_U_UPPERCASE_NAME UNIQUE (UPPERCASE_NAME);

ALTER TABLE ALERTS 
ADD CONSTRAINT A_MGID_FK FOREIGN KEY (METRIC_GROUP_ID) REFERENCES METRIC_GROUPS(ID);

ALTER TABLE ALERTS 
ADD CONSTRAINT A_CNGID_FK FOREIGN KEY (CAUTION_NOTIFICATION_GROUP_ID) REFERENCES NOTIFICATION_GROUPS(ID);

ALTER TABLE ALERTS 
ADD CONSTRAINT A_DNGID_FK FOREIGN KEY (DANGER_NOTIFICATION_GROUP_ID) REFERENCES NOTIFICATION_GROUPS(ID);

ALTER TABLE ALERTS 
ADD CONSTRAINT A_CPNGID_FK FOREIGN KEY (CAUTION_POSITIVE_NOTIFICATION_GROUP_ID) REFERENCES NOTIFICATION_GROUPS(ID);

ALTER TABLE ALERTS 
ADD CONSTRAINT A_DPNGID_FK FOREIGN KEY (DANGER_POSITIVE_NOTIFICATION_GROUP_ID) REFERENCES NOTIFICATION_GROUPS(ID);