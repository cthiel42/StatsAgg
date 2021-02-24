ALTER TABLE METRIC_GROUPS ADD COLUMN METRIC_GROUP_TEMPLATE_ID INTEGER;
ALTER TABLE METRIC_GROUPS ADD COLUMN VARIABLE_SET_ID INTEGER;

ALTER TABLE METRIC_GROUPS 
ADD CONSTRAINT MG_MGTPID_FK FOREIGN KEY (METRIC_GROUP_TEMPLATE_ID) REFERENCES METRIC_GROUP_TEMPLATES(ID);

ALTER TABLE METRIC_GROUPS 
ADD CONSTRAINT MG_VSID_FK FOREIGN KEY (VARIABLE_SET_ID) REFERENCES VARIABLE_SETS(ID);
