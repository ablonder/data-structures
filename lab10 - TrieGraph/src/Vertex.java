import java.util.ArrayList;

/**
 * A single vertex of the BaconNumber graph
 * 
 * @author Aviva Blonder
 *
 */
public class Vertex {
	Vertex mark;
	Vertex parent;
	String name;
	char type;
	int dist;
	ArrayList<Vertex> edges;
	
	/**
	 * Creates an empty vertex and initializes all fields to default values
	 */
	public Vertex(String n, String t){
		edges = new ArrayList<Vertex>();
		mark = null;
		dist = -1;
		name = n;
		type = t.charAt(0);
	}
	
	/**
	 * Creates an edge from this to destination
	 * @param destination
	 * @return the outcome of adding destination to edges
	 */
	public boolean addEdge(Vertex destination){
		return edges.add(destination);
	}
	
	/**
	 * Returns a string representation of the Vertex
	 * @return name
	 */
	public String toString(){
		return name;
	}
}
