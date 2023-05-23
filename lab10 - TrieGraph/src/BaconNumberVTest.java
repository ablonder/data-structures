import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;


public class BaconNumberVTest {


	@Ignore
	public void test() {
		// test of constructor
		BaconNumberV test = new BaconNumberV("http://cs.oberlin.edu/~gr151/imdb/imdb.cslam.txt");
		System.out.println(test.allActors);
		System.out.println(test.allMovies);
		
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
		BaconNumberV test = new BaconNumberV("http://cs.oberlin.edu/~gr151/imdb/imdb.small.txt");
		// .155 seconds
		System.out.println("small topcenter");
		test.recenter("christopher lee i");
		test.topcenter(5);
		// .171 seconds
	}
	
	@Ignore
	public void top250EfficiencyTest(){
		System.out.println("top 250 efficiency");
		BaconNumberV test = new BaconNumberV("http://cs.oberlin.edu/~gr151/imdb/imdb.top250.txt");
		// .379 seconds
		System.out.println("top 250 topcenter");
		test.recenter("bess flowers");
		test.topcenter(5);
		// 15.400 seconds
	}
	
	@Ignore
	public void pre1950EfficiencyTest(){
		System.out.println("pre 1950 efficiency");
		BaconNumberV test = new BaconNumberV("http://cs.oberlin.edu/~gr151/imdb/imdb.pre1950.txt");
		// 9.284 seconds
		System.out.println("pre 1950 recenter");
		test.recenter("matsunosuke onoe");
		// 11.686 seconds
		System.out.println("pre 1950 findAll");
		test.findAll();
		// 24.094 seconds
		//System.out.println("pre 1950 topcenter");
		//test.topcenter(5);
	}
	
	@Test
	public void post1950EfficiencyTest(){
		System.out.println("post 1950 efficiency");
		BaconNumberV test = new BaconNumberV("http://cs.oberlin.edu/~gr151/imdb/imdb.post1950.txt");
		// 819.129 seconds
		//System.out.println("post 1950 recenter");
		//test.recenter("tom byron i");
		//System.out.println("post 1950 topcenter");
		//test.topcenter(5);
	}
	
	@Ignore
	public void tvvEfficiencyTest(){
		System.out.println("only tv & video efficiency");
		BaconNumberV test = new BaconNumberV("http://cs.oberlin.edu/~gr151/imdb/imdb.only-tv-v.txt");
		// 227.407 seconds
		System.out.println("tv & video topcenter");
		test.recenter("mark wood iv");
		test.topcenter(5);
	}
	
	@Ignore
	public void notvvEfficiencyTest(){
		System.out.println("no tv & video efficiency");
		BaconNumberV test = new BaconNumberV("http://cs.oberlin.edu/~gr151/imdb/imdb.no-tv-v.txt");
		// 1900 to 6127.549 seconds
		//System.out.println("no tv & video topcenter");
		//test.recenter("ernie adams i");
		//test.topcenter(5);
	}
	
	@Ignore
	public void fullEfficiencyTest(){
		System.out.println("full efficiency");
		BaconNumberV test = new BaconNumberV("http://cs.oberlin.edu/~gr151/imdb/imdb.full.txt");
		// 910.052 seconds
		System.out.println("full topcenter");
		test.recenter("tom byron i");
		// 1413.253 seconds
		test.topcenter(5);
	}

}
