package cs4370;
/*****************************************************************************************
 * @file  TestTupleGenerator.java
 *
 * @author   Sadiq Charaniya, John Miller
 */

import static java.lang.System.out;

/*****************************************************************************************
 * This class tests the TupleGenerator on the Student Registration Database defined in the
 * Kifer, Bernstein and Lewis 2006 database textbook (see figure 3.6).  The primary keys
 * (see figure 3.6) and foreign keys (see example 3.2.2) are as given in the textbook.
 */
public class TestTupleGenerator
{
    /*************************************************************************************
     * The main method is the driver for TestGenerator.
     * @param args  the command-line arguments
     */
    public static void main (String [] args)
    {
        TupleGenerator test = new TupleGeneratorImpl ();

        test.addRelSchema ("Student",
                           "id name address status",
                           "Integer String String String",
                           "id",
                           null);

	Table Student = new Table("Student",
                           "id name address status",
                           "Integer String String String",
                           "id");
        
        test.addRelSchema ("Professor",
                           "id name deptId",
                           "Integer String String",
                           "id",
                           null);

	Table Professor = new Table("Professor",
                           "id name deptId",
                           "Integer String String",
                           "id");
        
        test.addRelSchema ("Course",
                           "crsCode deptId crsName descr",
                           "String String String String",
                           "crsCode",
                           null);

	Table Course = new Table("Course",
                           "crsCode deptId crsName descr",
                           "String String String String",
                           "crsCode");
        
        test.addRelSchema ("Teaching",
                           "crsCode semester profId",
                           "String String Integer",
                           "crcCode semester",
                           new String [][] {{ "profId", "Professor", "id" },
                                            { "crsCode", "Course", "crsCode" }});

	Table Teaching = new Table("Teaching",
                           "crsCode semester profId",
                           "String String Integer",
                           "crsCode semester");
	
        test.addRelSchema ("Transcript",
                           "studId crsCode semester grade",
                           "Integer String String String",
                           "studId crsCode semester",
                           new String [][] {{ "studId", "Student", "id"},
                                            { "crsCode", "Course", "crsCode" },
                                            { "crsCode semester", "Teaching", "crsCode semester" }});

	Table Transcript = new Table("Transcript",
                           "studId crsCode semester grade",
                           "Integer String String String",
                           "studId crsCode semester");
	
        String [] tables = { "Student", "Professor", "Course", "Teaching", "Transcript" };
	Table [] tables2 = {Student, Professor, Course, Teaching, Transcript};
        
        //int tups [] = new int [] { 10000, 1000, 2000, 50000, 5000 };
	int tups [] = new int [] { 10000, 10000, 2000, 5000, 5000 };
    
        Comparable [][][] resultTest = test.generate (tups);
        
        for (int i = 0; i < resultTest.length; i++) {
            out.println (tables [i]);
	    out.println ( tables2[i].getName() );
            for (int j = 0; j < resultTest [i].length; j++) {
		/*
                for (int k = 0; k < resultTest [i][j].length; k++) {
                    out.print (resultTest [i][j][k] + ",");
                } // for
		*/
		tables2[i].insert( resultTest[i][j] );
                out.println ();
            } // for
	    tables2[i].print();
            out.println ();
        } // for
	out.println("Professor join Teaching on Professor.id == Teaching.profID");
	( Professor.join("id","profId",Teaching) ).print();
	out.println("Teaching join Course");
	( Teaching.join(Course) ).print();
	out.println("Teaching join Transcript");
	( Teaching.join(Transcript) ).print();
	out.println("Course join Transcript");
	( Course.join(Transcript) ).print();
	out.println("Student join Transcript on Student.id == Transcript.studId");
	( Transcript.join("studId","id",Student) ).print();
    } // main

} // TestTupleGenerator

