import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;


public class BaconNumberTest {

	@Ignore
	public void test() {
		// test of constructor
		BaconNumber test = new BaconNumber("http://cs.oberlin.edu/~gr151/imdb/imdb.cslam.txt");
		System.out.println(test.graph);
		
		// test of recenter
		test.recenter("Alice");
		
		// test of find
		test.find("Bob");
		test.find("Carol");
		test.find("Dave");
		test.find("Eve");
		test.find("Lala");
		
		// test of findAll
		test.findAll();
		
		// test of avgdist
		test.avgdist();
		test.recenter("Bob");
		test.avgdist();
		System.out.println();
		
		// test of topcenter
		test.topcenter(5);
		
		// test of table
		test.table();
		
		test.recenter("Alice");
		System.out.println(test.actors);
		
		// test of longest
		test.longest();
		
		System.out.println();
		// test of main
		//String[] args = {};
		//BaconNumber.main(args);
		
		// test of most
		test.most();
		
		// test of movies
		test.movies("Alice");
		test.movies("Bob");
	}
	
	@Ignore
	public void smallEfficiencyTest(){
		System.out.println("small efficiency");
		BaconNumber test = new BaconNumber("http://cs.oberlin.edu/~gr151/imdb/imdb.small.txt");
		// .126 seconds
		System.out.println("small topcenter");
		test.recenter("christopher lee i");
		test.topcenter(5);
		// 1.125 seconds
	}
	
	@Test
	public void top250EfficiencyTest(){
		System.out.println("top 250 efficiency");
		BaconNumber test = new BaconNumber("http://cs.oberlin.edu/~gr151/imdb/imdb.top250.txt");
		// 1.105 seconds
		System.out.println("\ntop 250 most");
		test.most();
		System.out.println("\ntop 250 recenter");
		test.recenter("bess flowers");
		System.out.println("\ntop 250 Kevin Bacon (I) movies");
		test.movies("Kevin Bacon (I)");
		System.out.println("\ntop250 avgdist");
		test.avgdist();
		System.out.println("\ntop250 table");
		test.table();
		System.out.println("\ntop250 longest");
		test.longest();
		System.out.println("\ntop250 findAll");
		test.findAll();
		System.out.println("\ntop250 topcenter");
		test.topcenter(5);
		// 21.337 seconds
	}
	
	@Ignore
	public void pre1950EfficiencyTest(){
		System.out.println("pre 1950 efficiency");
		BaconNumber test = new BaconNumber("http://cs.oberlin.edu/~gr151/imdb/imdb.pre1950.txt");
		// 36.083 seconds
		System.out.println("pre 1950 recenter");
		test.recenter("matsunosuke onoe");
		// 48.513 seconds
		System.out.println("pre 1950 findAll");
		test.findAll();
		// 49.311 seconds
		//System.out.println("pre 1950 topcenter");
		//test.topcenter(5);
	}
	
	@Ignore
	public void post1950EfficiencyTest(){
		System.out.println("post 1950 efficiency");
		BaconNumber test = new BaconNumber("http://cs.oberlin.edu/~gr151/imdb/imdb.post1950.txt");
		// 819.129 seconds
		System.out.println("post 1950 topcenter");
		test.recenter("tom byron i");
		test.topcenter(5);
	}
	
	@Ignore
	public void tvvEfficiencyTest(){
		System.out.println("only tv & video efficiency");
		BaconNumber test = new BaconNumber("http://cs.oberlin.edu/~gr151/imdb/imdb.only-tv-v.txt");
		// 227.407 seconds
		System.out.println("tv & video topcenter");
		test.recenter("mark wood iv");
		test.topcenter(5);
	}
	
	@Ignore
	public void notvvEfficiencyTest(){
		System.out.println("no tv & video efficiency");
		BaconNumber test = new BaconNumber("http://cs.oberlin.edu/~gr151/imdb/imdb.no-tv-v.txt");
		// 1900 seconds to 6127.549
		//System.out.println("no tv & video topcenter");
		//test.recenter("ernie adams i");
		//test.topcenter(5);
	}
	
	@Ignore
	public void fullEfficiencyTest(){
		System.out.println("full efficiency");
		BaconNumber test = new BaconNumber("http://cs.oberlin.edu/~gr151/imdb/imdb.full.txt");
		// 910.052 seconds
		System.out.println("\nfull most");
		test.most();
		System.out.println("\nfull recenter");
		test.recenter("tom byron i");
		// 1413.253 seconds
		System.out.println("\nfull Kevin Bacon (I) movies");
		test.movies("Kevin Bacon (I)");
		System.out.println("\nfull avgdist");
		test.avgdist();
		System.out.println("\nfull table");
		test.table();
		System.out.println("\nfull longest");
		test.longest();
		System.out.println("\nfull findAll");
		test.findAll();
		System.out.println("\nfull topcenter");
		test.topcenter(5);
	}

}
