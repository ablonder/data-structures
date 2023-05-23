/**
 * test of MyTreeMap
 * 
 * @author Aviva Blonder
 * 
 */

import static org.junit.Assert.*;

import java.util.TreeMap;

import org.junit.Test;


public class MyTreeMapTest {
	
	/** test of empty constructor and put() 
	 * put() that changes the value of a non-root node */
	@Test
	public void putTest() {
		MyTreeMap<String, Integer> test = new MyTreeMap<String,Integer>();
		TreeMap<String, Integer> real = new TreeMap<String, Integer>();
		System.out.println("putTest");
		
		// test of put() creating a new root node
		test.put("b", 1);
		real.put("b", 1);
		System.out.println(test);
		// test of put() adding to the left
		test.put("a", 2);
		real.put("a", 2);
		System.out.println(test);
		// test of put() adding to the right
		test.put("c", 3);
		real.put("c", 3);
		System.out.println(test);
		System.out.println(real);
		
		// test of put() changing the value of the root node
		assertEquals("replaced root", test.put("b", 4), real.put("b", 4));
		System.out.println(test);
		// test of put() changing the value of a non-root node to the left
		assertEquals("replaced left", test.put("a", 5), real.put("a", 5));
		System.out.println(test);
		// test of put() changing the value of a non-root node to the right
		assertEquals("replaced right", test.put("c", 6), real.put("c", 6));
		System.out.println(test);
		System.out.println(real);
	}
	
	
	/** test of restructure() method and two parameter constructor */
	@Test
	public void restructureTest(){
		// test of two parameter constructor
		MyTreeMap<Integer, String> test = new MyTreeMap<Integer, String>(1, "a");
		TreeMap<Integer, String> real = new TreeMap<Integer, String>();
		real.put(1, "a");
		System.out.println("rotationTest");
		System.out.println(test);
		
		//test of left rotation
		test.put(2, "b");
		real.put(2, "b");
		System.out.println(test);
		test.put(5,  "c");
		real.put(5, "c");
		System.out.println(test);
		System.out.println(real);
		
		//test of right rotation
		test.put(0, "d");
		real.put(0, "d");
		test.put(-1, "e");
		real.put(-1, "e");
		System.out.println(test);
		System.out.println(real);
		
		//test of right-left rotation
		test.put(8, "f");
		real.put(8, "f");
		test.put(6, "g");
		real.put(6, "g");
		System.out.println(test);
		System.out.println(real);
		
		//test of left-right rotation
		test.put(3, "i");
		real.put(3, "i");
		test.put(4, "j");
		real.put(4, "j");
		System.out.println(test);
		System.out.println(real);
		
		//test of get()
		for(int i = -1; i < 9; i++){
			assertEquals("get key " + i, test.get(i), real.get(i));
		}
	}

}
