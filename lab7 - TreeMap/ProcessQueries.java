/**
 * Ranks a list of urls from a file based on user entered queries
 * 
 * @author Aviva Blonder
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class ProcessQueries {
	static MyPriorityQueue<WebPageIndex> queue;
	static URLComparator comp;
	
	/** 
	 * turns all of the webpages listed in file into WebPageIndices
	 * 
	 * @param file
	 */
	public static void loadURL(String file){
		comp = new URLComparator("");
		queue = new MyPriorityQueue<WebPageIndex>(comp);
		ArrayList<String> urls = new ArrayList<String>();
		try{
			Scanner f = new Scanner(new File(file));
			while(f.hasNextLine()){
				String url = f.nextLine();
				if(!urls.contains(url)){
					try{
						queue.heap.add(new WebPageIndex(url));
						urls.add(url);
					}catch (IOException e){ }
				}
			}
		} catch (FileNotFoundException e){
			System.out.println("File " + file + " not found!" );
		}
	}
	
	/**
	 * Sorts queue based on query into a list
	 * 
	 * @param query
	 * @return ArrayList of WebPageIdicies in order from highest to lowest order based on query
	 */
	public static ArrayList<WebPageIndex> sort(String query){
		ArrayList<WebPageIndex> results = new ArrayList<WebPageIndex>();
		comp = new URLComparator(query);
		MyPriorityQueue<WebPageIndex> newqueue = new MyPriorityQueue<WebPageIndex>(comp);
		queue.setComparator(comp);
		while(!queue.isEmpty()){
			WebPageIndex page = queue.poll();
			if(comp.scoreCalc(page) != 0) results.add(0, page);
			newqueue.offer(page);
		}
		queue = newqueue;
		return results;
	}
	
	/**
	 * prints out the pages in args[0] sort() by query
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		loadURL(args[0]);
		ArrayList<WebPageIndex> results;
		boolean done = false;
		int num = 0;
		if(args.length > 1){
			try { num = Integer.parseInt(args[1]); }
			catch (NumberFormatException e) {	}
		}
		Scanner query = new Scanner(System.in);
		System.out.println("What do you want to search for? (Or hit enter to exit.)");
		while(query.hasNextLine() && !done){
			String q = query.nextLine();
			if(q.isEmpty()){
				done = true;
				System.out.println("Hit enter again.");
			} else {
				results = sort(q);
				int show = num;
				if(num < 1 || num > results.size()) show = results.size();
				for(int i = 0; i < show; i++) System.out.println("" + comp.scoreCalc(results.get(i)) + "\t" + results.get(i).pageURL);
				System.out.println();
				System.out.println("What do you want to search for? (Or hit enter to exit.)");
			}
		}
		query.close();
	}

}
