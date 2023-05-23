/**
 * Test of MyTreeSet
 * 
 * @author Aviva Blonder
 */

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.TreeSet;

import org.junit.Test;


public class MyTreeSetTest {

	/** test of constructor, add(), size(), clear(), and iterator() methods */
	@Test
	public void testMyTreeSet() {
		// test of constructor
		MyTreeSet<Integer> test = new MyTreeSet<Integer>();
		TreeSet<Integer> real = new TreeSet<Integer>();
		for(int i = 0; i < 10; i++){
			// test of add()
			test.add(i);
			real.add(i);
			// test of size()
			assertEquals("size after addition " + i, test.size(), real.size());
		}
		
		// test of iterator()
		Iterator <Integer> testit = test.iterator();
		Iterator <Integer> realit = real.iterator();
		int j = 0;
		while(realit.hasNext()){
			j++;
			assertEquals("item " + j, testit.next(), realit.next());
		}
		
		// test of clear()
		System.out.println(test);
		test.clear();
		System.out.println(test);
	}

}
