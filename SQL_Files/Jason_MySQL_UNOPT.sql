USE university;
SET profiling = 1;

SELECT name FROM Student, Transcript, Course 
WHERE Student.id = Transcript.studId 
AND       Transcript.crsCode = Course.crsCode 
AND       deptId = 'deptId916209' 
AND       Student.name 
NOT IN       ( SELECT name 
	FROM Student, Transcript, Course 
	WHERE Student.id = Transcript.studId 
	AND             Transcript.crsCode = Course.crsCode 
	AND             deptId = 'deptId632332'       );

SHOW PROFILES;
