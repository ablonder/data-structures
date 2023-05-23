/**
 * Test of TrieGraph
 * 
 * @author Aviva Blonder
 */

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;


public class TrieGraphTest {

	/**
	 * Test of constructor, isEmpty(), size(), addVertex(), addEdge(),
	 * contains(), toString(), toList(), iterator()
	 */
	@Test
	public void test() {
		// test of constructor, isEmpty, and size
		TrieGraph test = new TrieGraph();
		assertTrue("empty after construction", test.isEmpty());
		assertEquals("size after construction", test.size(), 0);
		
		// test of addVertex
		System.out.println(test.addVertex("hello", "m"));
		
		// test of addVertex with a word that's already there
		System.out.println(test.addVertex("hello", "m"));
		
		test.addVertex("hellos", "a");
		test.addVertex("hella", "actor");
		test.addVertex("apples", "movie");
		
		// test of addVertex with non-letter characters
		test.addVertex("boli938via", "m");
		test.addVertex("bologna 3902", "a");
		test.addVertex("app (les9)8", "m");
		
		// test of addEdge
		assertTrue(test.addEdge("hellos", "boli938via"));
		assertTrue(test.addEdge("hellos", "hella"));
		System.out.println(test.getVertex("hellos").edges);
		assertFalse(test.addEdge("app les98", "nope"));
		assertFalse(test.addEdge("hellos", "boli938via"));
		
		// test of contains
		assertTrue("contains bologna", test.contains("bologna 3902"));
		assertTrue("contains hellos", test.contains("hellos"));
		assertFalse("contains hellosa", test.contains("hellosa"));
	}

}
