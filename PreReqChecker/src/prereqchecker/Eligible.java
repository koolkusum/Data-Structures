package prereqchecker;

import java.util.*;

/**
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * AdjListInputFile name is passed through the command line as args[0]
 * Read from AdjListInputFile with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * Step 2:
 * EligibleInputFile name is passed through the command line as args[1]
 * Read from EligibleInputFile with the format:
 * 1. c (int): Number of courses
 * 2. c lines, each with 1 course ID
 * 
 * Step 3:
 * EligibleOutputFile name is passed through the command line as args[2]
 * Output to EligibleOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class Eligible {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.Eligible <adjacency list INput file> <eligible INput file> <eligible OUTput file>");
            return;
        }
        HashMap<String,ArrayList<String>> myMap = new HashMap<String, ArrayList<String>>();
        StdIn.setFile(args[0]);
        int numberofcourses = StdIn.readInt();
        int countercourses=0;
        while (countercourses<numberofcourses)
        {
            String courseinput= StdIn.readString();
            ArrayList<String> emptyList = new ArrayList<String>();
            myMap.put(courseinput, emptyList);
            countercourses= countercourses+1;
        }
        int prerquisitecounter = StdIn.readInt();
        for (int i =0; i<prerquisitecounter;i++)
        {
            String course1= StdIn.readString();
            String course2= StdIn.readString();
            ArrayList<String> listwithcourse= myMap.get(course1);
            listwithcourse.add(course2);
            myMap.put(course1, listwithcourse);
        }
        StdIn.setFile(args[1]);
        String targetcourse= StdIn.readString();
        int numberoftakecourses = StdIn.readInt();
        ArrayList<String> coursestaken= new ArrayList<String>();
        for (int i =0; i<numberoftakecourses;i++)
        {
            coursestaken.add(StdIn.readString());
        }
    

	// WRITE YOUR CODE HERE
    }
}
