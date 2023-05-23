import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Test of MyHashMap
 * 
 * @author Aviva Blonder
 */


public class MyHashMapTest {

	/**
	 * Test of constructors, size(), isEmpty(), toString(), put(), resize()
	 * and clear() methods
	 */
	@Test
	public void test() {
		// test of empty and parameterized constructor
		MyHashMap<Integer, String> test = new MyHashMap<Integer, String>();
		// test of initial size()
		assertEquals("initial size ", test.size(), 0);
		// test of initial isEmpty()
		assertTrue("initial empty ", test.isEmpty());
		
		// test of put() of a new key
		System.out.println(test.toString());
		assertEquals("first put", test.put(1, "hello"), null);
		System.out.println(test.toString());
		// test of put() of a key that's already there
		assertEquals("second put", test.put(1, "hi"), "hello");
		System.out.println(test.toString());
		// test of size() after put
		assertEquals("size after put ", test.size(), 1);
		// test of isEmpty() after put
		assertFalse("empty after put ", test.isEmpty());
		
		// test of clear
		test.clear();
		assertTrue("after clear empty ", test.isEmpty());
		assertEquals("size after clear ", test.size(), 0);
	}
	
	/** test of resize(), remove(), get(), and contains() methods and Iterator */
	@Test
	public void testSomeMore(){
		MyHashMap<Integer, String> test = new MyHashMap<Integer, String>(1, (float) .5);
		// test of resize()
		test.put(1, "a");
		test.put(2, "b");
		System.out.println(test.toString());
		
		// test of get()
		assertEquals("get 2", test.get(2), "b");
		
		// test of resize()
		test.put(3, "c");		
		test.put(0, "d");
		System.out.println(test.toString());
		
		// test of remove()
		assertEquals("remove 1", test.remove(1), "a");
		assertEquals("remove 4", test.remove(4), null);
		assertEquals("size after remove", test.size(), 3);
		
		// test of get()
		assertEquals("get 3", test.get(3), "c");
		
		// test of containsKey()
		assertTrue("contains 2", test.containsKey(2));
		assertFalse("contains 22", test.containsKey(22));
		
		// test of containsValue()
		assertTrue("contains d", test.containsValue("d"));
		assertFalse("contains y", test.containsValue("y"));
		
		// test of keys()
		Iterator<Integer> testk = (Iterator<Integer>) test.keys();
		
		// test of iterator remove() exception
		boolean testing = false;
		try{
			testk.remove();
			testing = false;
		} catch (IllegalStateException e){
			testing = true;
		}
		assertTrue(testing);
		
		// test of keys() hasNext() and next()
		while(testk.hasNext()) System.out.print(testk.next());
		
		// test of iterator remove()
		testk.remove();
		System.out.println();
		
		// test of values()
		Iterator<String> testv = (Iterator<String>) test.values();
		while(testv.hasNext()) System.out.print(testv.next());
		System.out.println();
		
		// test of next() exception
		testing = false;
		try{
			testv.next();
			testing = false;
		} catch (NoSuchElementException e) {
			testing = true;
		}
		assertTrue(testing);
	}

}
