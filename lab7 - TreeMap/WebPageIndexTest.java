/**
 * Test of WebPageIndex
 * 
 * @author Aviva Blonder
 */

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Iterator;

import org.junit.Test;


public class WebPageIndexTest {

	/**
	 * test of WebPageIndex constructor and catch
	 */
	@Test
	public void testWebPageIndex() {
		try{
			WebPageIndex test = new WebPageIndex("testscannerfile");
			// ensures that variables have been initialized properly
			System.out.println(test.wordIndices);
			System.out.println(test.links);
			System.out.println(test.wordcount);
			System.out.println(test.pageURL);
		} catch (IOException e){
			fail("File not found!");
		}
		
		// test of catch
		try{
			WebPageIndex error = new WebPageIndex("nope");
			fail("Nonexistant file did not cause error.");
		} catch (IOException e){ }
	}

	/**
	 * test of getWordCount(), getUrl(), and toString() methods
	 */
	@Test
	public void testOverallMethods() {
		try{
			WebPageIndex test = new WebPageIndex("testscannerfile");
			// test of getWordCount()
			assertEquals("wordcount", test.getWordCount(), 12);
			// test of getUrl()
			assertEquals("getUrl", test.getUrl(), "testscannerfile");
			// test of toString()
			System.out.println(test.toString());
		} catch (IOException e){
			fail("File not found!");
		}
	}

	/**
	 * test of contains(), getCount(), getFrequency(), getLocations(), and words() methods
	 */
	@Test
	public void testByItemMethods() {
		try{
			WebPageIndex test = new WebPageIndex("testscannerfile");
			String[] wordlist = {"happening", "hi", "if", "important", "is", "it", "tagged", "there", "what"};
			
			// test of words()
			Iterator<String> wordstest = test.words();
			
			for(String word : wordlist){
				int count = 1;
				double total = 12;
				// test of contains()
				assertTrue("contains " + word, test.contains(word));
				// test of words()
				assertEquals("word " + word, word, wordstest.next());
				
				if(word == "is") count = 3;
				if(word == "it") count = 2;
				// test of getCount()
				assertEquals(word + " count", test.getCount(word), count);
				// test of getFrequency()
				assertEquals(word + " frequency", test.getFrequency(word), count/total, 1/total);
				// test of getLocations()
				System.out.println(word + " " + test.getLocations(word));
			}
			
			// test of methods with absent string
			assertFalse("contains i", test.contains("i"));
			assertEquals("i count", test.getCount("i"), 0);
			assertEquals("i frequency", test.getFrequency("i"), 0, 1/12);
			assertEquals("i location", test.getLocations("i") + "", "[]");
		} catch (IOException e){
			fail("File not found!");
		}
	}
	
	/**
	 * test of containsPhrase(), getPhraseCount(), getPhraseFrequency(), and getPhraseLocations() methods
	 */
	@Test
	public void testPhrase() {
		try{
			WebPageIndex test = new WebPageIndex("testscannerfile");
		
			// test of containsPhrase()
			assertTrue("contains happening is it", test.containsPhrase("happening is it"));
			assertFalse("contains hi happening", test.containsPhrase("hi happening"));
			
			// test of getPhraseCount()
			assertEquals("is count", test.getPhraseCount("is"), 3);
			assertEquals("there what count", test.getPhraseCount("there what"), 1);
			assertEquals("hi happening count", test.getPhraseCount("hi happening"), 0);
			
			// test of getPhraseFrequency()
			double total = 12;
			assertEquals("is frequency", test.getPhraseFrequency("is"), 3/total, 1/total);
			assertEquals("there what frequency", test.getPhraseFrequency("there what"), 2/total, 1/total);
			assertEquals("hi happening frequency", test.getPhraseFrequency("hi happening"), 0, 1/total);
			System.out.println("there what frequency" + test.getPhraseFrequency("there what"));
			
			// test of getPhraseLocations()
			System.out.println("is locations " + test.getPhraseLocations("is"));
			System.out.println("there what locations " + test.getPhraseLocations("there what"));
			System.out.println("hi happening locations " + test.getPhraseLocations("hi happening"));
		
		} catch (IOException e) {
			fail("File not found!");
		}
	}
	
	/**
	 * test of main() method
	 */
	@Test
	public void testMain(){
		String[] arg = {"testscannerfile", "http://occs.cs.oberlin.edu/~rhoyle/16s-cs151/lab06/sample.html", "blah"};
		WebPageIndex.main(arg);
		System.out.println();
	}

}
