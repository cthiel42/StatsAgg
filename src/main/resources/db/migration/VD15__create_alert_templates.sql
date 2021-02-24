CREATE TABLE ALERT_TEMPLATES (
	ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), 
	NAME VARCHAR(500) NOT NULL, 
	UPPERCASE_NAME VARCHAR(500) NOT NULL, 
	VARIABLE_SET_LIST_ID INTEGER NOT NULL, 
	DESCRIPTION_VARIABLE CLOB(1048576), 
	ALERT_NAME_VARIABLE VARCHAR(500) NOT NULL, 
	METRIC_GROUP_NAME_VARIABLE VARCHAR(500) NOT NULL, 
	IS_ENABLED BOOLEAN NOT NULL, 
	IS_CAUTION_ENABLED BOOLEAN NOT NULL, 
	IS_DANGER_ENABLED BOOLEAN NOT NULL, 
	ALERT_TYPE INTEGER NOT NULL, 
	ALERT_ON_POSITIIVE BOOLEAN NOT NULL, 
	ALLOW_RESEND_ALERT BOOLEAN NOT NULL, 
	RESEND_ALERT_EVERY BIGINT, 
	RESEND_ALERT_EVERY_TIME_UNIT INTEGER, 
	CAUTION_NOTIFICATION_GROUP_NAME_VARIABLE VARCHAR(500), 
	CAUTION_POSITIVE_NOTIFICATION_GROUP_NAME_VARIABLE VARCHAR(500), 
	CAUTION_OPERATOR INTEGER, 
	CAUTION_COMBINATION INTEGER, 
	CAUTION_COMBINATION_COUNT INTEGER, 
	CAUTION_THRESHOLD DECIMAL(31,7), 
	CAUTION_WINDOW_DURATION BIGINT, 
	CAUTION_WINDOW_DURATION_TIME_UNIT INTEGER, 
	CAUTION_STOP_TRACKING_AFTER BIGINT, 
	CAUTION_STOP_TRACKING_AFTER_TIME_UNIT INTEGER, 
	CAUTION_MINIMUM_SAMPLE_COUNT INTEGER, 
	DANGER_NOTIFICATION_GROUP_NAME_VARIABLE VARCHAR(500), 
	DANGER_POSITIVE_NOTIFICATION_GROUP_NAME_VARIABLE VARCHAR(500), 
	DANGER_OPERATOR INTEGER, 
	DANGER_COMBINATION INTEGER, 
	DANGER_COMBINATION_COUNT INTEGER, 
	DANGER_THRESHOLD DECIMAL(31,7), 
	DANGER_WINDOW_DURATION BIGINT, 
	DANGER_WINDOW_DURATION_TIME_UNIT INTEGER, 
	DANGER_STOP_TRACKING_AFTER BIGINT, 
	DANGER_STOP_TRACKING_AFTER_TIME_UNIT INTEGER, 
	DANGER_MINIMUM_SAMPLE_COUNT INTEGER
);

ALTER TABLE ALERT_TEMPLATES 
ADD CONSTRAINT AT_PK PRIMARY KEY (ID);

ALTER TABLE ALERT_TEMPLATES 
ADD CONSTRAINT AT_U_NAME UNIQUE (NAME);

ALTER TABLE ALERT_TEMPLATES 
ADD CONSTRAINT AT_U_UPPERCASE_NAME UNIQUE (UPPERCASE_NAME);

ALTER TABLE ALERT_TEMPLATES 
ADD CONSTRAINT AT_VSL_FK FOREIGN KEY (VARIABLE_SET_LIST_ID) REFERENCES VARIABLE_SET_LISTS(ID);