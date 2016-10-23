\timing

CREATE INDEX studId_index
ON Student (id);

CREATE INDEX studName_index
ON Student (name);

CREATE INDEX Transcript_studID_index
ON Transcript (studId);

CREATE INDEX Course_deptId_index
ON Course (deptId);

SELECT name FROM Student JOIN ( SELECT studId FROM Transcript JOIN ( SELECT * FROM Course WHERE deptId = 'deptId878811' ) AS T1 ON Transcript.crsCode = T1.crsCode WHERE studId NOT IN (SELECT studId FROM Transcript JOIN ( SELECT * FROM Course WHERE deptId = 'deptId449361') AS T2 ON Transcript.crsCode = T2.crsCode )) as T3 ON Student.id = T3.studId;

\timing
