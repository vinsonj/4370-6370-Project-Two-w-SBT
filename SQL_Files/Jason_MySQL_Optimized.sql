USE university;
SET profiling = 1;

ALTER TABLE Student ADD INDEX (id);

ALTER TABLE Student ADD INDEX (name);

ALTER TABLE Transcript ADD INDEX (studId);

ALTER TABLE Course ADD INDEX (deptId);

SELECT name FROM Student JOIN ( SELECT studId FROM Transcript JOIN ( SELECT * FROM Course WHERE deptId = 'deptId916209' ) AS T1 ON Transcript.crsCode = T1.crsCode WHERE studId NOT IN (SELECT studId FROM Transcript JOIN ( SELECT * FROM Course WHERE deptId = 'deptId632332') AS T2 ON Transcript.crsCode = T2.crsCode )) as T3 ON Student.id = T3.studId;

SHOW PROFILES;
