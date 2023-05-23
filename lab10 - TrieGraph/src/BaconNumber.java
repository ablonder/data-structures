import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Reads a data file of actors and movies and allows the user to query bacon numbers
 * 
 * @author Aviva Blonder
 *
 */
public class BaconNumber {
	TrieGraph center;
	TrieGraph graph;
	ArrayList<TrieGraph> actors;
	double avgdist;
	ArrayList<TrieGraph> allActors;
	
	/**
	 * Initializes BaconNumber with default center Kevin Bacon
	 * @param url
	 */
	public BaconNumber(String url){
		this(url, "Kevin Bacon (I)");
	}
	
	/**
	 * Initializes BaconNumber with a graph based on the list of actors and movies in url
	 * and center
	 * @param url
	 * @param center
	 */
	public BaconNumber(String url, String center){
		allActors = new ArrayList<TrieGraph>();
		graph = new TrieGraph();
		try{
			Scanner s = new Scanner(new URL(url).openStream());
			while(s.hasNextLine()){
				String[] line = s.nextLine().split("\\|");
				TrieGraph actor = graph.addVertex(line[0], "actor");
				if(actor != null)
					allActors.add(actor);
				graph.addVertex(line[1], "movie");
				graph.addEdge(line[0], line[1]);
				graph.addEdge(line[1], line[0]);
			}
			recenter(center);
		}catch (MalformedURLException e){
			System.out.println("That's not a good url, try again.");
		}catch (IOException e){
			System.out.println("That's not a real file, try again.");
		}catch (IndexOutOfBoundsException e){
			System.out.println("That file isn't formatted correctly, try again.");
		}
	}
	
	/**
	 * Makes newcenter center and recenters if it is in graph
	 * @param newcenter
	 */
	public void recenter(String newcenter){
		TrieGraph cent = graph.getVertex(newcenter);
		if(cent == null){
			center = null;
			System.out.println(newcenter + " is not in the dataset provided.");
		}else if(cent.equals(center))
			System.out.println(newcenter + " is already the center.");
		else{
			center = cent;
			recenter();
		}
	}
	
	/**
	 * Goes through the graph from center in a breadth first search, changing dist,
	 * marking nodes, and adding them to actors
	 */
	private void recenter(){
		actors = new ArrayList<TrieGraph>();
		avgdist = 0;
		center.mark = center;
		center.dist = 0;
		actors.add(center);
		ConcurrentLinkedQueue<TrieGraph> queue = new ConcurrentLinkedQueue<TrieGraph>();
		queue.add(center);
		TrieGraph last;
		while(!queue.isEmpty()){
			last = queue.poll();
			for(TrieGraph neighbor : last.edges){
				if(neighbor.mark != center){
					neighbor.parent = last;
					if(last.type == 'a')
						neighbor.dist = last.dist;
					else if(last.type == 'm'){
						neighbor.dist = last.dist + 1;
						avgdist += neighbor.dist;
						actors.add(neighbor);
					}
					queue.add(neighbor);
					neighbor.mark = center;
				}
			}
		}
		avgdist = avgdist/actors.size();
	}
	
	/**
	 * Prints out the vertices backtracking to center from name
	 * @param name
	 */
	public void find(String name){
		if(center == null)
			System.out.println("There's no center. Please recenter.");
		else{
			name = name.toLowerCase();
			TrieGraph start = graph.getVertex(name);
			if(start == null && !name.substring(name.length()-3).equals("(i)"))
				find(name + " (I)");
			else if(start == null || start.mark != center)
				System.out.println("I'm sorry, " + name + " is unreachable from " + center);
			else
				find(start);
		}
	}
	
	/**
	 * Follows and prints out the path from start to the center
	 * @param start
	 */
	public void find(TrieGraph start){
		if(center == null)
			System.out.println("There is no center. Please receter.");
		else{
			String path = start.name;
			TrieGraph next = start;
			while(next.dist > 0){
				next = next.parent;
				path += " -> " + next.name;
			}
			path += " -> " + center + " (" + start.dist + ")";
			System.out.println(path);
		}
	}
	
	/**
	 * Prints out the average distance to center (as calculated in recenter)
	 */
	public void avgdist(){
		if(center == null)
			System.out.println("There is no center. Please recenter.");
		else{
			System.out.println(avgdist + "\t" + center.name + " (" + actors.size() + ", " + 
					(graph.size() - actors.size()) + ")");
		}
	}
	
	/**
	 * Goes through actors and prints out the top n centers by avgdist
	 * @param n
	 */
	public void topcenter(int n){
		if(center == null)
			System.out.println("There is no center, please recenter.");
		else{
			System.out.println("Calculating top center. Please be patient, especially if you are using a long data set.");
			if(n > actors.size())
				n = actors.size();
			String[] topn = new String[n];
			Double[] topnum = new Double[n];
			ArrayList<TrieGraph> actrs = (ArrayList<TrieGraph>) actors.clone();
			for(TrieGraph actor : actrs){
				recenter(actor.name);
				int num = n;
				while(num > 0 && (topn[num-1] == null || avgdist < topnum[num-1])){
					num--;
					if(num < n-1){
						topn[num+1] = topn[num];
						topnum[num+1] = topnum[num];
					}
				}
				if(num < n){
					topn[num] = center.name;
					topnum[num] = avgdist;
				}
			}		
			for(int i = 0; i < n; i++){
				System.out.println(topnum[i] + "\t" + topn[i]);
			}
		}
	}
	
	/**
	 * Goes through actors and prints out how many individuals have each dist from center
	 */
	public void table(){
		if(center == null)
			System.out.println("There is no center. Please recenter.");
		else{
			System.out.println("Table of distances for " + center.name);
			Integer[] distances = new Integer[0];
			Integer[] dist;
			for(TrieGraph actor : actors){
				try{
					distances[actor.dist]++;
				}catch(ArrayIndexOutOfBoundsException e){
					dist = new Integer[actor.dist+1];
					for(int i = 0; i < distances.length; i++){
						dist[i] = distances[i];
					}
					dist[actor.dist] = 1;
					distances = dist;
				}	
			}
			for(int j = 0; j < distances.length; j++){
				System.out.println("Number " + j + ":\t" + distances[j]);
			}
			System.out.println("Unreachable:\t" + (allActors.size() - actors.size()));
		}
	}
	
	/**
	 * Iterates through all actors connected to center and prints their path back to it
	 */
	public void findAll(){
		if(center == null)
			System.out.println("There is no center, please recenter.");
		else for(TrieGraph actor : actors){
			System.out.println(actor);
			find(actor);
		}
	}
	
	/**
	 * Prints out one of the longest paths to the center taken from the end of actors
	 */
	public void longest(){
		if(center == null)
			System.out.println("There is no center. Please recenter.");
		else
			find(actors.get(actors.size()-1));
	}
	
	/**
	 * Goes through allActors to find who was in the most movies
	 */
	public void most(){
		int m = 0;
		String most = "";
		for(TrieGraph actor : allActors){
			if(actor.edges.size() > m){
				m = actor.edges.size();
				most = actor.name;
			}
		}
		if(most.equals(""))
			System.out.println("Your graph is empty.");
		else
			System.out.println(most + " was in the most movies (" + m + ")");
	}
	
	/**
	 * Prints out all the movies that name performed in
	 * @param name
	 */
	public void movies(String name){
		TrieGraph actor = graph.getVertex(name);
		if(actor != null){
			System.out.print(actor + " acted in: ");
			boolean first = true;
			for(TrieGraph movie : actor.edges){
				if(!first)
					System.out.print(", ");
				first = false;
				System.out.print(movie);
			}
			System.out.println();
		} else
			System.out.println("That's not an actor in this database.");
	}
	
	
	/**
	 * Uses the webpage designated by args or a user provided url, or the choice of a preset url
	 * to construct a BaconNumber, which the user can then query using any of the methods above
	 * @param args
	 */
	public static void main(String[] args){
		BaconNumber bacon = null;
		if(args.length > 0)
			bacon = new BaconNumber(args[0]);
		Scanner input = new Scanner(System.in);
		while(bacon == null || bacon.graph.isEmpty()){
			System.out.println("Enter a url or one of the following:");
			System.out.println("cslam - The complete list of Computer Science Lab Assignment Movies featuring 4 films and 5 actors.");
			System.out.println("small - A small, interconnected subset of IMDB, featuring 161 actors.");
			System.out.println("top250 - The top 250 movies on IMDB and the people who acted in them.");
			System.out.println("pre1950 - All movies on IMDB made before 1950.");
			System.out.println("post1950 - All movies on IMDB made after 1950.");
			System.out.println("only-tv-v - All made for TV and direct to video movies on IMDB.");
			System.out.println("no-tv-v - All movies not made for tv or direct to video on IMDB.");
			System.out.println("full - Every movie on IMDB.");
			if(input.hasNextLine()){
				String url = input.nextLine();
				if(url.equals("cslam"))
					url = "http://cs.oberlin.edu/~gr151/imdb/imdb.cslam.txt";
				else if(url.equals("small"))
					url = "http://cs.oberlin.edu/~gr151/imdb/imdb.small.txt";
				else if(url.equals("top250"))
					url = "http://cs.oberlin.edu/~gr151/imdb/imdb.top250.txt";
				else if(url.equals("pre1950"))
					url = "http://cs.oberlin.edu/~gr151/imdb/imdb.pre1950.txt";
				else if(url.equals("post1950"))
					url = "http://cs.oberlin.edu/~gr151/imdb/imdb.post1950.txt";
				else if(url.equals("only-tv-v"))
					url = "http://cs.oberlin.edu/~gr151/imdb/imdb.only-tv-v.txt";
				else if(url.equals("no-tv-v"))
					url = "http://cs.oberlin.edu/~gr151/imdb/imdb.no-tv-v.txt";
				else if(url.equals("full"))
					url = "http://cs.oberlin.edu/~gr151/imdb/imdb.full.txt";
				System.out.println("Loading file. Please be patient.");
				bacon = new BaconNumber(url);
			}
		}
		
		System.out.println();
		System.out.println("What do you want to do next? (Or hit enter to exit.)");
		System.out.println("Methods: recenter, find, findAll, avgdist, topcenter, table, longest, most, movies.");
		boolean done = false;
		while(input.hasNextLine() && !done){
			String action = input.nextLine().toLowerCase();
			if(action.equals("recenter")){
				System.out.println("Who do you want the new center to be?");
				bacon.recenter(input.nextLine());
			}else if(action.equals("find")){
				System.out.println("Who do you want to find?");
				bacon.find(input.nextLine());
			}else if(action.equals("findall"))
				bacon.findAll();
			else if(action.equals("avgdist"))
				bacon.avgdist();
			else if(action.equals("topcenter")){
				System.out.println("How many of the top centers do you want?");
				bacon.topcenter(Integer.parseInt(input.nextLine()));
			}else if(action.equals("table"))
				bacon.table();
			else if(action.equals("longest"))
				bacon.longest();
			else if(action.equals("most"))
				bacon.most();
			else if(action.equals("movies")){
				System.out.println("Which actor do you want to see the movies of?");
				bacon.movies(input.nextLine());
			}
			else if(action.equals(""))
				done = true;
			else
				System.out.println("That's not a valid method. Try again.");
			if(!done){
				System.out.println("What do you want to do next? (Or hit enter to exit.)");
				System.out.println("Methods: recenter, find, findAll, avgdist, topcenter, table, longest, most, movies.");
			}
			else
				System.out.println("Hit enter again.");
		}
	}
}
