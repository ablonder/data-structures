/**
 * Test of MyTrie
 * 
 * @author Aviva Blonder
 */

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class MyTrieTest {

	/**
	 * Test of constructor, isEmpty(), size(), containsEmptyString(), add(),
	 * contains(), containsPrefix(), toString(), toList(), iterator()
	 */
	@Test
	public void test() {
		// test of constructor, isEmpty, and size
		MyTrie test = new MyTrie();
		assertTrue("empty after construction", test.isEmpty());
		assertEquals("size after construction", test.size(), 0);
		
		// test of containsEmptyString
		assertFalse("contains empty string", test.containsEmptyString());
		
		// test of add
		assertTrue(test.add("hello"));
		
		// test of add a word that's already there
		assertFalse(test.add("hello"));
		
		test.add("hellos");
		test.add("hella");
		test.add("apples");
		
		// test of add with non-letter characters
		test.add("boli938via");
		test.add("bologna3902");
		
		// test of contains
		assertTrue("contains bologna", test.contains("bologna"));
		assertTrue("contains hellos", test.contains("hellos"));
		assertFalse("contains hellosa", test.contains("hellosa"));
		
		// test of containsPrefix()
		assertTrue("contains bol", test.containsPrefix("bol"));
		assertFalse("contains gna", test.containsPrefix("gna"));
		
		// test of toString() and toList()
		System.out.println(test.toString());
		
		// test of iterator() and toList()
		Iterator<String> testit = test.iterator();
		while(testit.hasNext())
			System.out.println(testit.next());
	}
}
