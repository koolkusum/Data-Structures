package prereqchecker;
import java.util.HashMap;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class NewClass {
        private HashMap<String, ArrayList<String>> coursesAndPreReqs;
        private HashMap<String, Boolean> marked;
    
        // constructor
        public NewClass() {
            coursesAndPreReqs = new HashMap<String, ArrayList<String>>();
            marked = new HashMap<String, Boolean>();
        }
        public void adjacencylist(HashMap<String, ArrayList <String>>hm){
            this.coursesAndPreReqs =hm;
        }

}
