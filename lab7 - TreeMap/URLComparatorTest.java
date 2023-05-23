/**
 * Test of URLComparator
 * 
 * @author Aviva Blonder
 */

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


public class URLComparatorTest {

	/** test of scoreCalc() and compare() */
	@Test
	public void test() {
		URLComparator comp = new URLComparator("happening what 'hi there' if");
		
		try{
			// test of scoreCalc()
			WebPageIndex page = new WebPageIndex("testscannerfile");
			assertEquals("score of ", comp.scoreCalc(page), 4);
			
			// test of compare()
			comp = new URLComparator("oberlin content");
			WebPageIndex page1 = new WebPageIndex("urls-catalog");
			WebPageIndex page2 = new WebPageIndex("urls-new.oberlin.edu");
			assertEquals("comparison smaller ", comp.compare(page1, page2), -3321);
			assertEquals("comparison self ", comp.compare(page2, page2), 0);
			assertEquals("comparison larger ", comp.compare(page1, page), 372);
		}catch(IOException e){
			System.out.println("File not found.");
		}
		
	}

}
