package friends;

import java.util.ArrayList;

import structures.Queue;
import structures.Stack;

public class Friends {

	/**
	 * Finds the shortest chain of people from p1 to p2.
	 * Chain is returned as a sequence of names starting with p1,
	 * and ending with p2. Each pair (n1,n2) of consecutive names in
	 * the returned chain is an edge in the graph.
	 * 
	 * @param g Graph for which shortest chain is to be found.
	 * @param p1 Person with whom the chain originates
	 * @param p2 Person at whom the chain terminates
	 * @return The shortest chain from p1 to p2. Null or empty array list if there is no
	 *         path from p1 to p2
	 */
	public static ArrayList<String> shortestChain(Graph g, String p1, String p2) {
		
		/** COMPLETE THIS METHOD **/
		//if the graph is empty return null cause there nothing there and if no one is there to begin or end the chain
		if (g==null||p1==null||p2==null) {
			return null;
		}
		// iw ill be using the code on page 540 in textbook to perform a bfs for this method
		//since we have to return an arraylist lets a create an arraylist that holds the shortest chain
		ArrayList <String> shortman = new ArrayList<String>();
		//now we will create a boolean array that stores whether the member was visited or not
		//the graph g has a set number of members so those members wud be the the length of the array
		boolean visits[]=new boolean[g.members.length];
		Person[] visited = new Person[g.members.length];
		//creating queue to store people in 
		Queue<Person> queue = new Queue<Person>();
		int index = g.map.get(p1);
		
		visits[index]=true;//mark the source
		queue.enqueue(g.members[index]);//putting first person in queue
			while(!queue.isEmpty()) {
			Person v = queue.dequeue();//remove next vertex from the queue
			//putting the first vertex visited into the arraylist cause it was visited
			int path = g.map.get(v.name);
			visits[path]=true;
			//this is literally whats next in line lol
			Friend nextinline= v.first;
			if (nextinline==null) {//if whats next is null return null
				return null;
			}
			while (nextinline!=null) {//if whats next is not null
			
				if (visits[nextinline.fnum] == false) {//if the next one is not a vertex visited already
					visits[nextinline.fnum] = true;//marking this vertex now as visit
					visited[nextinline.fnum] =v ; //this is basically keeping track of the last vertex visited
					queue.enqueue(g.members[nextinline.fnum]);//adding this into the queue
					//if the next vertex is the one we are looking for aka we found p2
					if (g.members[nextinline.fnum].name.equals(p2)) {
						v = g.members[nextinline.fnum];//taking this out of queue
						
						while (v.name.equals(p1) == false) {//if the last thing in the queue is not p1
							shortman.add(0, v.name);//adding the shortest path to the arraylist
							v = visited[g.map.get(v.name)];
						}
						shortman.add(0, p1);
						return shortman;
				}
				}
				nextinline=nextinline.next;//moving to next vertex
			}
			}
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY ( we love a happy compiler :))
		// CHANGE AS REQUIRED FOR YOUR IMPLEMENTATION
		return null;
	}
	
	/**
	 * Finds all cliques of students in a given school.
	 * 
	 * Returns an array list of array lists - each constituent array list contains
	 * the names of all students in a clique.
	 * 
	 * @param g Graph for which cliques are to be found.
	 * @param school Name of school
	 * @return Array list of clique array lists. Null or empty array list if there is no student in the
	 *         given school
	 */
	public static ArrayList<ArrayList<String>> cliques(Graph g, String school) {
		//DFS code exmaple used from pg 526 in textbook
		//listing out all null cases
		if (g==null||school==null) {
			return null;
		}
		//array used like the last method
		//using it check of a vertex visited or not
		boolean[] visits = new boolean[g.members.length];
		//created arraylist
		ArrayList<ArrayList<String>> cliqetee = new ArrayList<>();
		//running through all the members
		for(int i = 0; i < g.members.length; i++) {
			Person p = g.members[i];
			if(visits[i] || !p.student) //checking if student
				continue;
			
			ArrayList<String> clicker = new ArrayList<>();
			DFS(g, visits, clicker, school, i);//searching for the cliques uses dfs
			
	//if the clique array list is empty
			if(clicker != null && clicker.size() > 0)
				cliqetee.add(clicker);//add clicker to cliqutee
		}
		
		return cliqetee;
		/** COMPLETE THIS METHOD **/
		
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY
		// CHANGE AS REQUIRED FOR YOUR IMPLEMENTATION
	
		
	}
	private static void DFS(Graph graph, boolean[] visits, 
			ArrayList<String> Members, String school, int index) {//THIS IS A RECURSIVE METHOD

		Person person = graph.members[index];
		
		//Add student to members array list if person attended the school and it hasnt been visited previously
		if(!visits[index] && person.student && person.school.equals(school))
			Members.add(person.name);
		//making the visit for this person as true
		visits[graph.map.get(person.name)] = true;
		//moving on to the next vertex
		Friend nextinline = graph.members[index].first;
		while(nextinline != null) {//while the next vertex is not null
			int position = nextinline.fnum;//the number the vertex associated to
			Person cliquet = graph.members[position];//adding this info to the person
			//if position not visited and the school is the same go through the dfs again
			if(visits[position] == false && cliquet.student && cliquet.school.equals(school)) {

				DFS(graph, visits, Members, school, position);
			}
			
			nextinline = nextinline.next;//moving to next vertex
		}
		
	}
	
	
	/**
	 * Finds and returns all connectors in the graph.
	 * 
	 * @param g Graph for which connectors needs to be found.
	 * @return Names of all connectors. Null or empty array list if there are no connectors.
	 */
	public static ArrayList<String> connectors(Graph g) {
		if (g==null) {//if the graph is null return null
			return null;
		}
		//creating new arraylist with correctors
			ArrayList<String> connectors = new ArrayList<String>();
				//holds whether a vertex was visited or not
			boolean[] visits = new boolean[g.members.length];
				//This is similar to like previous in tree node
			ArrayList<String> prev = new ArrayList<String>();
				
				//Tnumber of memebers, the dfs cannot exceed this amount
			int[] max= new int[g.members.length];
				
				//number of memeber before the dfs 
			int[] before = new int[g.members.length];
				
			//looping throught all memebers of the graph
			for (int i = 0; i < g.members.length; i++){
				if (visits[i] == false) {//off with ur head, oh  i means sending to do a dfs XD
					connectors = DFS2(connectors, g, g.members[i], visits, new int[] {0,0}, max, before, prev, true);
				}
			}
				
				//This will return the answer
				return connectors;
		
			
		/** COMPLETE THIS METHOD **/
		
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY
		// CHANGE AS REQUIRED FOR YOUR IMPLEMENTATION
	
		
	}
private static ArrayList<String> DFS2(ArrayList<String> connectors, Graph g, Person start, boolean[] visits, int[] count, int[] max, int[] before, ArrayList<String> prev, boolean started){
//marking the vetex as visted
		visits[g.map.get(start.name)] = true;
		
		//used like the nextinline from the rest of my code
		Friend nextinline = start.first;
		//first vertex
		max[g.map.get(start.name)] = count[0];
		//second vertex
		before[g.map.get(start.name)] = count[1];
		
		//as long as nextinline in null
		while (nextinline != null) {
			
			if (visits[nextinline.fnum] == false) {
				//adding to first vertex
				count[0]++;
				//second vertex
				count[1]++;
				//THIS IS RECURSIVE
				connectors = DFS2(connectors, g, g.members[nextinline.fnum], visits, count, max, before, prev, false);
				
			//checking vertex numbers
				if (max[g.map.get(start.name)] <= before[nextinline.fnum]) {
					//if not visted and checking the connecter
					if ((connectors.contains(start.name) == false && prev.contains(start.name)) || (connectors.contains(start.name) == false && started == false)) {
						connectors.add(start.name);//adding name to the connecter
					}
				}
				else {
					int first = before[g.map.get(start.name)];
					int second = before[nextinline.fnum];
					if (first < second) {
						before[g.map.get(start.name)] = first;
					}
					else {
						before[g.map.get(start.name)] = second;
					} 
				}		
			prev.add(start.name);//adding name to prev
			}
			else {
				int third = before[g.map.get(start.name)];
				int fourth = max[nextinline.fnum];
				if (third < fourth) {
					before[g.map.get(start.name)] = third;
				}
				else {
					before[g.map.get(start.name)] = fourth;
				}
			}
			nextinline = nextinline.next;
		}
		return connectors;
	}
}

