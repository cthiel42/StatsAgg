CREATE TABLE METRIC_GROUP_TAGS ( 
	ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),  
	METRIC_GROUP_ID INTEGER NOT NULL, 
	TAG CLOB(65535) NOT NULL 
);

ALTER TABLE METRIC_GROUP_TAGS 
ADD CONSTRAINT MGT_PK PRIMARY KEY (ID);

ALTER TABLE METRIC_GROUP_TAGS 
ADD CONSTRAINT MGT_MGID_FK FOREIGN KEY (METRIC_GROUP_ID) REFERENCES METRIC_GROUPS(ID);