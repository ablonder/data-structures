/** JUnit test of MyQueue class
 * 
 * @author Aviva Blonder
 */

import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.Test;


public class MyQueueTest {

	@Test
	public void testMyQueue() {
		MyQueue<Integer> test = new MyQueue<Integer>();
		assertTrue("new queue is empty", test.isEmpty());
	}

	@Test
	public void testEnqueue() {
		MyQueue<Integer> test = new MyQueue<Integer>();
		ConcurrentLinkedQueue<Integer> real = new ConcurrentLinkedQueue<Integer>();
		for(int i = 0; i < 5; i++){
			test.enqueue(i);
			real.add(i);
		}
		assertEquals("size after additions", real.size(), test.size());
		assertEquals("First item", real.peek(), test.front());
	}

	@Test
	public void testDequeue() {
		MyQueue<Integer> test = new MyQueue<Integer>();
		ConcurrentLinkedQueue<Integer> real = new ConcurrentLinkedQueue<Integer>();
		for(int i = 0; i < 5; i++){
			test.enqueue(i);
			real.add(i);
		}
		for(int i = 0; i < 5; i++) assertEquals("removed item " + i, test.dequeue(), real.remove());
		assertEquals("size after removes", test.size(), real.size());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testDequeueException(){
		MyQueue<Integer> test = new MyQueue<Integer>();
		test.dequeue();
	}

	@Test
	public void testFront() {
		MyQueue<Integer> test = new MyQueue<Integer>();
		ConcurrentLinkedQueue<Integer> real = new ConcurrentLinkedQueue<Integer>();
		for(int i = 0; i < 5; i++){
			test.enqueue(i);
			real.add(i);
		}
		for(int i = 0; i < 4; i++){
			test.dequeue();
			real.remove();
			assertEquals("front after remove " + i, test.front(), real.peek());
		}
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testFrontException(){
		MyQueue<Integer> test = new MyQueue<Integer>();
		test.front();
	}
	

	@Test
	public void testIsEmpty() {
		MyQueue<Integer> test = new MyQueue<Integer>();
		assertTrue("new list is empty", test.isEmpty());
		test.enqueue(1);
		assertFalse("list after enqueue", test.isEmpty());
		test.dequeue();
		assertTrue("list after dequeue", test.isEmpty());
	}
	
	@Test
	public void testSize() {
		MyQueue<Integer> test = new MyQueue<Integer>();
		ConcurrentLinkedQueue<Integer> real = new ConcurrentLinkedQueue<Integer>();
		assertEquals("size of new queues", test.size(), real.size());
		for(int i = 0; i < 5; i++){
			test.enqueue(i);
			real.add(i);
			assertEquals("size of queue after enqueue " + i, test.size(), real.size());
		}
		for(int i = 0; i < 5; i++){
			test.dequeue();
			real.remove();
			assertEquals("size of queue after dequeue " + i, test.size(), real.size());
		}
	}

	@Test
	public void testClear() {
		MyQueue<Integer> test = new MyQueue<Integer>();
		for(int i = 0; i < 5; i++) test.enqueue(i);
		test.clear();
		assertTrue("empty after clear", test.isEmpty());
	}

}
