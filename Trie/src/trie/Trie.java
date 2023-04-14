package trie;

import java.util.ArrayList;

/**
 * This class implements a Trie. 
 * 
 * @author Sesh Venugopal
 *
 */
public class Trie {
	
	// prevent instantiation
	private Trie() { }
	
	/**
	 * Builds a trie by inserting all words in the input array, one at a time,
	 * in sequence FROM FIRST TO LAST. (The sequence is IMPORTANT!)
	 * The words in the input array are all lower case.
	 * 
	 * @param allWords Input array of words (lowercase) to be inserted.
	 * @return Root of trie with all words inserted from the input array
	 */
	//hello future self! I tried putting as many comments for you so you understand what the heck
	//you are doing if you come back to look at this code, this code has caused you many tears but
	//girls you did it, anyways I wonder if u quit ur part time job yet, I know the medical bills are 
	//pricey but my job is the reason why im handing this in so late 
	public static TrieNode buildTrie(String[] allWords) {
		/** COMPLETE THIS METHOD **/
		TrieNode root= new TrieNode(null, null, null);//intializing the root of the trie "root"
		TrieNode first = null;
		//i have a bunch of print stuff throughout the code, this helps me debug
		for (int i=0;i<allWords.length;i++){
			int ab = allWords[i].length()-1;//getting length of specific word in the array of words
			short b = (short) ab;
			short a = 0;
			//System.out.println("Word is "+ allWords[i]);
			Indexes word = new Indexes(i, a , b); //index of word in the array, starting number, ending number
			if (i==0){ //first child of root
				first= root.firstChild = new TrieNode(word, null, null);//after the null(top of tree ig)the first child would be the first word
			}
		
			
			else {//possiblity for siblings for first or children
				TrieNode sprefix = null;
				first= root.firstChild;
				TrieNode prev = root;
				boolean secondprefix = false;//this basically checks if we went dfrom a prefix like d to do
				boolean didit=false;//basically checks if prefix was inserted 
				boolean prefixtings=true;//this automatically makes it go to siblings unless its true (yes i purposelfully spelled things like that xD)
				while (first!=null){//took this directly from slides
					//from here we want to compare if the first(pointerig) is equal to the first character of next word in array
					int minf= allWords[first.substr.wordIndex].substring(0, first.substr.endIndex+1).length();
					int minn= allWords[i].length();
					//System.out.println("Minf Word is "+allWords[first.substr.wordIndex].substring(0, first.substr.endIndex+1));
			
					//System.out.println("Length of first word" + minf);
					//System.out.println("Length of second one " + minn);
					int min=0;
					//need to find the word with the minimum length so there won't be an error (more comparisons but nothing to compare to)
					if (minf>=minn){
						min=minn;
					}
					if (minf<minn){
						min=minf;
					}
					//System.out.println("Minimum is "+min);
					if (allWords[first.substr.wordIndex].substring(0, first.substr.endIndex+1).charAt(0)!=allWords[i].charAt(0)){//is if they share no prefix
						prev = first;
						first = first.sibling;
						prefixtings = false;
						//System.out.println("Chars are not equal)");
					}
					
					else{
						Indexes wprefix =null;//i ran out of name ideas 
						TrieNode wombats=null;
						int commoncounter=0; //counts the number of common characters in two words, we need this to make the start/end index lol
						for(int j=0; j<min; j++){
							if (allWords[first.substr.wordIndex].substring(0, first.substr.endIndex+1).charAt(j)==allWords[i].charAt(j)){//share at least one charactter prefix
								commoncounter=commoncounter+1;
								//System.out.println("allWords[first.substr.wordIndex].substring(0, first.substr.endIndex+1).charAt(j)" + allWords[first.substr.wordIndex].substring(0, first.substr.endIndex+1).charAt(j));
								//System.out.println("allWords[i].charAt(j)" + allWords[i].charAt(j));
								//System.out.println("counter "+commoncounter); 
							}
						}
						if (commoncounter-1 == (first.substr.endIndex)){//this case if a node like do already exists but we are comparing door to doom so we need a new node of doo
							prev = first;
							first = first.firstChild;
							prefixtings = true;
							secondprefix = true;
							sprefix = prev;
							//System.out.println("sprefix " + sprefix);
							if (allWords[first.substr.wordIndex].substring(0, first.substr.endIndex+1).charAt(commoncounter)!=allWords[i].charAt(commoncounter)){
								prev = first;//this is if the there is nothing more in commin after the prefix^
								first = first.sibling;
								prefixtings = false;
							}
							else{//this scenario is for when they do have a node witha new prefix to insert
								wprefix = new Indexes(first.substr.wordIndex, (short) (sprefix.substr.endIndex + 1), (short) (commoncounter));
									Indexes ndabay = new Indexes(i, (short) (commoncounter+1), (short) (allWords[i].length() - 1)); 
									Indexes grammys = new Indexes(first.substr.wordIndex, (short) commoncounter, (short) first.substr.endIndex);
									first = new TrieNode(grammys, first.firstChild, first.sibling);
									wombats= new TrieNode(ndabay, null, null);
									//System.out.println("sprefix****** "+ sprefix);
									TrieNode newcommon = new TrieNode(wprefix, first, first.sibling);
									first.sibling = wombats;
								//System.out.println("new common "+newcommon);
									if (prefixtings == false) {
										prev.sibling = newcommon;
									}
									else {
										prev.firstChild = newcommon;
									}
								didit=true;
								break;
								}
							
						
						}
						
						
						else{
							//if(secondprefix=false){
							wprefix = new Indexes(first.substr.wordIndex, (short) 0, (short) (commoncounter-1));//node with the prefix
							Indexes ndabay = new Indexes(i, (short) commoncounter, (short) (allWords[i].length() - 1)); //from the prefix to the end of word in new word
							Indexes grammys = new Indexes(first.substr.wordIndex, (short) commoncounter, (short) (first.substr.endIndex));//for a new first
							//now that we made the indexes we can go ahead and insert it into the node
							//System.out.println("Wprefix "+wprefix);
							//System.out.println("ndabay " + ndabay);
							//System.out.println("grammys+ "+grammys);
							first = new TrieNode(grammys, first.firstChild, first.sibling);
							//System.out.println("first ***********"+first);
							wombats = new TrieNode(ndabay, null, null);
							TrieNode newcommon= new TrieNode(wprefix, first, first.sibling);
							first.sibling = wombats;//this is the prefix the worse seperates itself into this prefix
							if(prefixtings == false) {
								prev.sibling = newcommon;
							}
							else {
								prev.firstChild = newcommon;
							}
							didit = true;
							
							break;
						
						}

					
					}	
				}

				if (didit==false){//if prefix was not inserted
					if(secondprefix==true){//if there was a pre pre fix ig
							Indexes indexforwordy = new Indexes(i, (short) (sprefix.substr.endIndex+1), (short) (allWords[i].length() - 1));
							TrieNode wordy = new TrieNode(indexforwordy, null, null);
							prev.sibling = wordy;
					}
					else{
					Indexes indexforwordy = new Indexes(i, (short) 0, (short) (allWords[i].length() - 1));
						TrieNode wordy = new TrieNode(indexforwordy, null, null);
						prev.sibling = wordy;
					}
				}
			}
		}
		

			 
		
		// FOLLOWING LINE IS A PLACEHOLDER TO ENSURE COMPILATION
		// MODIFY IT AS NEEDED FOR YOUR IMPLEMENTATION
		return root;//just returning the tree
	}
	
	/**
	 * Given a trie, returns the "completion list" for a prefix, i.e. all the leaf nodes in the 
	 * trie whose words start with this prefix. 
	 * For instance, if the trie had the words "bear", "bull", "stock", and "bell",
	 * the completion list for prefix "b" would be the leaf nodes that hold "bear", "bull", and "bell"; 
	 * for prefix "be", the completion would be the leaf nodes that hold "bear" and "bell", 
	 * and for prefix "bell", completion would be the leaf node that holds "bell". 
	 * (The last example shows that an input prefix can be an entire word.) 
	 * The order of returned leaf nodes DOES NOT MATTER. So, for prefix "be",
	 * the returned list of leaf nodes can be either hold [bear,bell] or [bell,bear].
	 *
	 * @param root Root of Trie that stores all words to search on for completion lists
	 * @param allWords Array of words that have been inserted into the trie
	 * @param prefix Prefix to be completed with words in trie
	 * @return List of all leaf nodes in trie that hold words that start with the prefix, 
	 * 			order of leaf nodes does not matter.
	 *         If there is no word in the tree that has this prefix, null is returned.
	 */
	public static ArrayList<TrieNode> completionList(TrieNode root,
										String[] allWords, String prefix) {
		/** COMPLETE THIS METHOD **/
		//i treed doing this iteratively at first to make this more efficent then i Cried and
		//decided to do recursion, anyways the recursion was so much easier 
		//i shud come back to do the itertively another time but for mental health reasons i shall not
		//i also am handing this code in like 3 days late so i dont have time to figure it out 
		//rip me, ill be taking donations of postivity! p.s i shud probs take therapy or something lol
		ArrayList<TrieNode> list = new ArrayList<TrieNode>();
		TrieNode first = root.firstChild;
		String prefixes = "" ;
		
		if (first == null || allWords.length == 0 || prefix.length() == 0) {
			return null;//this adresses all the scernios where it shud return null so if the first node doesnt exist
			//if the array has no input and the prefix given doesnt exist either
		}
		while (first != null) {
			if (prefix.charAt(first.substr.startIndex) == allWords[first.substr.wordIndex].charAt(first.substr.startIndex)) {//first letter of first and prefix are equal
				for (int i = first.substr.startIndex; i <= first.substr.endIndex; i++) {//running with firsts length
					if (i == prefix.length()) {//if first length=prefixlength
						break;
					}
					if (prefix.charAt(i) != allWords[first.substr.wordIndex].charAt(i)) {//if the letters are not equal return null
						return null;	
					}
					prefixes += prefix.charAt(i);//add prefix to string
					
					if (prefixes.equals(prefix)) {//code outside for loop adresses this scenario
						break;
					}
				}
				if (prefixes.equals(prefix)) {
					if (first.firstChild == null) {//is there is not child for first just add first to the lsit
						list.add(first);
						break;
					}
					TrieNode shawty = first.firstChild;//once there children involved we will use recursion
							list = tearshavebeenshed(shawty, list);//we are gonna check if shawty has kids or not
					break;
				} else {
				first = first.firstChild;//go to the child
					continue;
				}
			} else {
				first = first.sibling;//moving to next sibling if nothing similar from that whole if statement above
			}
		}
		if (prefixes == "" || list == null || !(prefixes.equals(prefix))) {
			//if the string is empty, the list is empty orthe prefix in list does not equal prefix return null
			return null;
		}
		// FOLLOWING LINE IS A PLACEHOLDER TO ENSURE COMPILATION
		// MODIFY IT AS NEEDED FOR YOUR IMPLEMENTATION
		return list;
	}

	private static ArrayList<TrieNode> tearshavebeenshed(TrieNode shawty, ArrayList<TrieNode> list) {
		while (shawty != null) {
			if (shawty.firstChild == null) {//if it has no childrern then just add shawty to list
				list.add(shawty);
			} else {//if shawty does have children(childsupport lol) then add shawtys children to the list
				list = tearshavebeenshed(shawty.firstChild, list);
			}
			shawty = shawty.sibling;// now we move on to shawty sister or shawtys second child
		}
		return list;
	}
		
	
	public static void print(TrieNode root, String[] allWords) {
		System.out.println("\nTRIE\n");
		print(root, 1, allWords);
	}
	
	private static void print(TrieNode root, int indent, String[] words) {
		if (root == null) {
			return;
		}
		for (int i=0; i < indent-1; i++) {
			System.out.print("    ");
		}
		
		if (root.substr != null) {
			String pre = words[root.substr.wordIndex]
							.substring(0, root.substr.endIndex+1);
			System.out.println("      " + pre);
		}
		
		for (int i=0; i < indent-1; i++) {
			System.out.print("    ");
		}
		System.out.print(" ---");
		if (root.substr == null) {
			System.out.println("root");
		} else {
			System.out.println(root.substr);
		}
		
		for (TrieNode ptr=root.firstChild; ptr != null; ptr=ptr.sibling) {
			for (int i=0; i < indent-1; i++) {
				System.out.print("    ");
			}
			System.out.println("     |");
			print(ptr, indent+1, words);
		}
	}
 }
