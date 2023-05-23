
import static org.junit.Assert.*;

import java.util.ListIterator;

import org.junit.Test;

public class MRUListTest {

	/** test of single parameter and double parameter add method
	 */
	@Test
	public void testAddType() {
		MRUList<Integer> test = new MRUList<Integer>();
		for(int i = 0; i < 10; i++){
			if(i%2 == 0) test.add(i, i);
			else assertTrue(test.add(i));
			assertEquals(i + "th addition", (int) (test.get(0)), i);
		}
		
		ListIterator<Integer> testit = test.listIterator();
		
		int j = 0;
		while(testit.hasNext()){
			j++;
			assertEquals(j + "th term in list", (int) (testit.next()), 10-j);
		}
		
	}

	/** test of contains method
	 */
	@Test
	public void testContainsObject() {
		MRUList<Integer> test = new MRUList<Integer>();
		for(int i = 0; i < 10; i++){
			test.add(i);
		}
		assertTrue("does contain", test.contains(5));
		assertFalse("does not contain", test.contains(22));
		assertEquals("first item after access", (int) (test.get(0)), 5);
	}

}
