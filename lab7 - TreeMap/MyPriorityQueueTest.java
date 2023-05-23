/**
 * Test of MyPriorityQueue
 * 
 * @author Aviva Blonder
 */

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.PriorityQueue;

import org.junit.Test;

public class MyPriorityQueueTest {

	/**
	 * test of constructor, offer(), percolateUp(), parent(), percolateDown()
	 * leftChild(), rightChild(), peek(), poll(), size(), and iterator() methods
	 */
	@Test
	public void test() {
		StringComparator comparator = new StringComparator();
		// test of constructor
		MyPriorityQueue<String> test = new MyPriorityQueue<String>(comparator);
		PriorityQueue<String> real = new PriorityQueue<String>();
		
		// test of offer(), percolateUp(), parent(), and peek()
		assertTrue("item added", test.offer("z"));
		real.offer("z");
		test.offer("y");
		real.offer("y");
		real.offer("x");
		test.offer("x");
		test.offer("w");
		real.offer("w");
		real.offer("v");
		test.offer("v");
		test.offer("u");
		real.offer("u");
		assertEquals("top item after offer and percolation", test.peek(), real.peek());
		
		// test of size()
		assertEquals("size after offer", test.size(), real.size());
		
		// test of poll(), percolateDown(), leftChild(), and rightChild()
		assertEquals("poll() 1", test.poll(), real.poll());
		assertEquals("poll() 2", test.poll(), real.poll());
		
		// test of iterator();
		Iterator<String> testit = test.iterator();
		Iterator<String> realit = real.iterator();
		int i = 0;
		while(realit.hasNext())
			assertEquals("iterator item " + i++, realit.next(), testit.next());
	}
	
	/** test of clear() */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testClear(){
		StringComparator comparator = new StringComparator();
		MyPriorityQueue<String> test = new MyPriorityQueue<String>(comparator);
		
		test.offer("a");
		assertEquals("initial", test.peek(), "a");
		
		test.clear();
		test.poll();
	}

}
