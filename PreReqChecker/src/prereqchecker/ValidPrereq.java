package prereqchecker;

import java.util.HashMap;
import java.util.HashSet;
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
 * ValidPreReqInputFile name is passed through the command line as args[1]
 * Read from ValidPreReqInputFile with the format:
 * 1. 1 line containing the proposed advanced course
 * 2. 1 line containing the proposed prereq to the advanced course
 * 
 * Step 3:
 * ValidPreReqOutputFile name is passed through the command line as args[2]
 * Output to ValidPreReqOutputFile with the format:
 * 1. 1 line, containing either the word "YES" or "NO"
 */

public class ValidPrereq {
    private  HashMap<String, Boolean> marked;
    private HashMap<String, ArrayList<String>> coursesandprereq;

    void Dfs(String ID, HashSet<String> inserthere)
    {
        ArrayList<String> listwithcourse = coursesandprereq.get(ID);

        for (int i =0; i<listwithcourse.size();i++)
        {
            if(marked.get(ID)==false)
            {
                inserthere.add(listwithcourse.get(i));
                Dfs(listwithcourse.get(i),inserthere);
            }
        }
    }
    public static void main(String[] args) {
        ValidPrereq nonstatic = new ValidPrereq();
        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.ValidPrereq <adjacency list INput file> <valid prereq INput file> <valid prereq OUTput file>");
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
            nonstatic.marked.put(courseinput, false);
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
       
	// WRITE YOUR CODE HERE
 
        StdIn.setFile(args[1]);
        String currecntcourseid = StdIn.readString();
        String currentpossibleprereq = StdIn.readString();
        HashSet<String> holdingprereq = new HashSet<String>();
         nonstatic.coursesandprereq=myMap;
        nonstatic.Dfs(currecntcourseid,holdingprereq);
        StdOut.setFile(args[2]);
        if (holdingprereq.contains(currentpossibleprereq))
        {
            StdOut.print("YES");
        }
        else{
            StdOut.print("NO");
        }
    
}



}
