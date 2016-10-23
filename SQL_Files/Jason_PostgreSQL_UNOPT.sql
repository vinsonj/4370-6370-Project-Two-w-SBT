\timing

SELECT name
FROM student, transcript, course
WHERE student.id = transcript.studId AND
      transcript.crsCode = course.crsCode AND
      deptId = 'deptId878811'
EXCEPT
(
      SELECT name
FROM student, transcript, course
WHERE student.id = transcript.studId AND
            transcript.crsCode = course.crsCode AND
            deptId = 'deptId449361'
        );
