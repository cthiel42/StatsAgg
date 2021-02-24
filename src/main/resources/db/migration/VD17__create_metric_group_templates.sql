CREATE TABLE METRIC_GROUP_TEMPLATES (
	ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), 
	NAME VARCHAR(500) NOT NULL, 
	UPPERCASE_NAME VARCHAR(500) NOT NULL, 
	VARIABLE_SET_LIST_ID INTEGER NOT NULL, 
	METRIC_GROUP_NAME_VARIABLE VARCHAR(500) NOT NULL, 
	DESCRIPTION_VARIABLE CLOB(1048576),
	MATCH_REGEXES_VARIABLE CLOB(1048576),  
	BLACKLIST_REGEXES_VARIABLE CLOB(1048576),  
	TAGS_VARIABLE CLOB(1048576),  
	IS_MARKED_FOR_DELETE BOOLEAN NOT NULL
);

ALTER TABLE METRIC_GROUP_TEMPLATES 
ADD CONSTRAINT MGTP_PK PRIMARY KEY (ID);

ALTER TABLE METRIC_GROUP_TEMPLATES 
ADD CONSTRAINT MGTP_U_NAME UNIQUE (NAME);

ALTER TABLE METRIC_GROUP_TEMPLATES 
ADD CONSTRAINT MGTP_U_UPPERCASE_NAME UNIQUE (UPPERCASE_NAME);

ALTER TABLE METRIC_GROUP_TEMPLATES 
ADD CONSTRAINT MGTP_VSL_FK FOREIGN KEY (VARIABLE_SET_LIST_ID) REFERENCES VARIABLE_SET_LISTS(ID);