import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;


public class BaconNumberV {
	Vertex center;
	ArrayList<Vertex> actors;
	double avgdist;
	Hashtable<String, Vertex> allActors;
	Hashtable<String, Vertex> allMovies;
	
	/**
	 * Initializes BaconNumber with default center Kevin Bacon
	 * @param url
	 */
	public BaconNumberV(String url){
		this(url, "Kevin Bacon (I)");
	}
	
	/**
	 * Initializes BaconNumber with a graph based on the list of actors and movies in url
	 * and center
	 * @param url
	 * @param center
	 */
	public BaconNumberV(String url, String center){
		allActors = new Hashtable<String, Vertex>();
		allMovies = new Hashtable<String, Vertex>();
		try{
			Scanner s = new Scanner(new URL(url).openStream());
			while(s.hasNextLine()){
				String[] line = s.nextLine().split("\\|");
				line[0] = line[0].toLowerCase();
				line[1] = line[1].toLowerCase();
				Vertex actor = allActors.get(line[0]);
				Vertex movie = allMovies.get(line[1]);
				if(actor == null){
					actor = new Vertex(line[0], "actor");
					allActors.put(line[0], actor);
				}
				if(movie == null){
					movie = new Vertex(line[1], "movie");
					allMovies.put(line[1], movie);
				}
				actor.edges.add(movie);
				movie.edges.add(actor);
			}
			//recenter(center);
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
		newcenter = newcenter.toLowerCase();
		Vertex cent = allActors.get(newcenter);
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
		actors = new ArrayList<Vertex>();
		avgdist = 0;
		center.mark = center;
		center.dist = 0;
		actors.add(center);
		ConcurrentLinkedQueue<Vertex> queue = new ConcurrentLinkedQueue<Vertex>();
		queue.add(center);
		Vertex last;
		while(!queue.isEmpty()){
			last = queue.poll();
			for(Vertex neighbor : last.edges){
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
			Vertex start = allActors.get(name);
			if(start == null && !name.substring(name.length()-3).equals("(i)"))
				find(name + " (I)");
			if(start == null || start.mark != center)
				System.out.println("I'm sorry, " + name + " is unreachable from " + center);
			else
				find(start);
		}
	}
	
	/**
	 * Follows and prints out the path from start to the center
	 * @param start
	 */
	public void find(Vertex start){
		if(center == null)
			System.out.println("There is no center. Please receter.");
		else{
			String path = start.name;
			Vertex next = start;
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
					(allActors.size() - actors.size()) + ")");
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
			if(n > actors.size())
				n = actors.size();
			String[] topn = new String[n];
			Double[] topnum = new Double[n];
			ArrayList<Vertex> actrs = (ArrayList<Vertex>) actors.clone();
			for(Vertex actor : actrs){
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
			for(Vertex actor : actors){
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
			System.out.println("Unreachable:\t" + (actors.size() - allActors.size()));
		}
	}
	
	/**
	 * Iterates through all actors connected to center and prints their path back to it
	 */
	public void findAll(){
		if(center == null)
			System.out.println("There is no center, please recenter.");
		else for(Vertex actor : actors){
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
		for(Vertex actor : allActors.values()){
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
		name = name.toLowerCase();
		Vertex actor = allActors.get(name);
		if(actor != null){
			System.out.print(actor + " acted in: ");
			boolean first = true;
			for(Vertex movie : actor.edges){
				if(!first)
					System.out.print(", ");
				first = false;
				System.out.print(movie);
			}
			System.out.println();
		} else
			System.out.println("That's not an actor in this database.");
	}
}
