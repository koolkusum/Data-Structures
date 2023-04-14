package prereqchecker;

import java.util.HashMap;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
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
 * AdjListOutputFile name is passed through the command line as args[1]
 * Output to AdjListOutputFile with the format:
 * 1. c lines, each starting with a different course ID, then 
 *    listing all of that course's prerequisites (space separated)
 */
public class AdjList {
 
    public static void main(String[] args) {
 
        if ( args.length < 2 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.AdjList <adjacency list INput file> <adjacency list OUTput file>");
            return;
        }
        HashMap<String,ArrayList<String>> myMap = new HashMap<String, ArrayList<String>>();
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);
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
        
        String adanjacenylistrepresentation = "";
        for (String courseID :myMap.keySet())
        {
            ArrayList<String> listwithcourse = myMap.get(courseID);
            adanjacenylistrepresentation=adanjacenylistrepresentation+courseID+" ";
            //System.out.println(listwithcourse.size());
            for (int i = 0; i < listwithcourse.size();i++)
            {
                adanjacenylistrepresentation= adanjacenylistrepresentation+listwithcourse.get(i) +" ";
            }
            adanjacenylistrepresentation=(adanjacenylistrepresentation+"\n");
        }
        StdOut.print(adanjacenylistrepresentation);
	// WRITE YOUR CODE HERE
    }
}
