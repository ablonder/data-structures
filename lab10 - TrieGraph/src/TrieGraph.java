import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Implementation of a graph to hold movies and actors for the Kevin Bacon game as a trie
 * 
 * @author Aviva Blonder
 *
 */
public class TrieGraph {
	TrieGraph mark;
	String name;
	int dist;
	TrieGraph parent;
	char type;
	int size;
	TrieGraph[] children;
	ArrayList<TrieGraph> edges;
	static final int num = 37;
	
	/**
	 * Creates an empty trie node and initializes all fields to default values
	 */
	public TrieGraph(){
		type = 'e';
		children = new TrieGraph[num];
		edges = null;
		size = 0;
		mark = null;
		dist = -1;
		parent = null;
		name = "";
	}
	
	/**
	 * Returns the size of the trie
	 * @return size
	 */
	public int size(){
		return size;
	}
	
	/**
	 * Returns the trieGraph designated by string
	 * @param string
	 * @return the trieGraph returned by getVertex of the treeGraph designated by the next letter of string, if they exist
	 * @return this if string is empty and type is not e and null otherwise
	 */
	public TrieGraph getVertex(String string){
		string = string.toLowerCase();
		String[] dex = findIndex(string);
		int index = Integer.parseInt(dex[0]);
		string = dex[1];
		if(index != -1 && children[index] != null)
			return children[index].getVertex(string.substring(1));
		else if(index != -1) return null;
		if(type != 'e') return this;
		return null;
	}
	
	/**
	 * Returns whether the trie contains string
	 * @param string
	 * @return whether the TrieGraph designated by the next letter of string contains the rest of string if they exist
	 * @return true if string is empty and type is not e and false otherwise
	 */
	public boolean contains(String string){
		if(getVertex(string) == null)
			return false;
		return true;
	}
	
	/**
	 * Returns the index where the triegraph representing the next valid character of s would be located in children
	 * @param s
	 * @return index representing the next digit, letter, or space character of s or -1 if none is found
	 * @return the remainder of s when the index is found
	 */
	private String[] findIndex(String s){
		int index = -1;
		while(index == -1 && !s.equals("")){
			char letter = s.charAt(0);
			if(Character.isDigit(letter))
				index = 26 + letter - '0';
			else if(letter == ' ')
				index = num-1;
			else if(Character.isLetter(letter))
				index = letter - 'a';
			else
				s = s.substring(1);
		}
		String[] dex = {"" + index, s};
		return dex;
		
	}
	
	/**
	 * Adds a vertex string to the trie as type t
	 * @param string
	 * @param t
	 * @return true if a new word is added and false if the word is already in the trieGraph
	 */
	public TrieGraph addVertex(String string, String t){
		return add(string, t, "");
	}
		
	/**
	 * Adds a vertex to the trie as type t
	 * @param string
	 * @param t
	 * @param n
	 * @return true if string is empty and the word is not already in the trieGraph
	 */
	private TrieGraph add(String string, String t, String n){
		string = string.toLowerCase();
		String[] dex = findIndex(string);
		int index = Integer.parseInt(dex[0]);
		string = dex[1];
		if(index == -1){
			if(type != 'e')
				return null;
			type = t.charAt(0);
			name = n;
			edges = new ArrayList<TrieGraph>();
			return this;
		}
		if(children[index] == null)
			children[index] = new TrieGraph();
		TrieGraph addition = children[index].add(string.substring(1), t, n+string.charAt(0));
		if(addition != null && t.charAt(0) == 'a'){
			size++;
			return addition;
		}
		return null;
	}
	
	/**
	 * Creates an edge from origin to destination
	 * @param origin
	 * @param destination
	 * @return true if origin and destination are in the trieGraph and the edge does not already exist and false otherwise
	 */
	public boolean addEdge(String origin, String destination){
		TrieGraph start = getVertex(origin);
		TrieGraph end = getVertex(destination);
		if(start != null && end != null){
			if(start.edges.contains(end))
				return false;
			return start.edges.add(end);
		}
		return false;
	}
	
	/**
	 * Returns whether the trie contains any strings
	 * @return true if size is 0 and false otherwise
	 */
	public boolean isEmpty(){
		if(size == 0) return true;
		return false;
	}
	
	/**
	 * Returns a string representation of the trie
	 * @return name
	 */
	public String toString(){
		return name;
	}
	
}