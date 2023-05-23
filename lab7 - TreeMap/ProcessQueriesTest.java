/**
 * Test of ProcessQueries
 * 
 * @author Aviva Blonder
 */

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class ProcessQueriesTest {

	/** test of loadURL(), sort() and main() methods */
	@Test
	public void test() {
		ProcessQueries test = new ProcessQueries();
		
		// test of loadURL()
		test.loadURL("urls-profs");
		for(WebPageIndex page : test.queue){
			System.out.println(page.pageURL);
			System.out.println(page.wordIndices);
			System.out.println(page.links);
			System.out.println(page.wordcount);
			System.out.println();
		}
		
		// test of sort()
		ArrayList<WebPageIndex> testsort = test.sort("and");
		for(WebPageIndex page : testsort) System.out.println(page.pageURL);
		System.out.println();
		
		// test of main()
		String[] args = {"urls-cs", "3"};
		test.main(args);
	}

}
