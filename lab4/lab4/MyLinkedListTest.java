

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.Ignore;
import org.junit.Test;

public class MyLinkedListTest {

	@Test
	public void testSize() {
		MyLinkedList<Integer> test = new MyLinkedList<Integer>();
		LinkedList<Integer> real = new LinkedList<Integer>();
		assertEquals("Size after construction", real.size(), test.size());
		test.add(0, 5);
		real.add(0, 5);
		assertEquals("Size after add", real.size(), test.size());
	}

	@Test
	public void testMyLinkedList() {
		MyLinkedList<Integer> test = new MyLinkedList<Integer>();
		LinkedList<Integer> real = new LinkedList<Integer>();
		assertEquals("Size after construction", real.size(), test.size());
	}

	@Test
	public void testAddIntType() {
		MyLinkedList<Integer> test = new MyLinkedList<Integer>();
		LinkedList<Integer> real = new LinkedList<Integer>();
		int n = 0;
		for(int i = 0; i < 5; i++) {
			n += 2;
			test.add(i, n);
			real.add(i, n);
			assertEquals("Size after add " + i, real.size(), test.size());
			assertEquals("Item in array " + i, real.get(i), test.get(i));
		}		
	}

	@Test
	public void testAddType() {
		Scanner reader = null;
		try {
			reader = new Scanner(new File("test1.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File Not Found!");
			System.exit(1);
		}
			
		MyLinkedList<String> test = new MyLinkedList<String>();
		LinkedList<String> real = new LinkedList<String>();
		while(reader.hasNextLine()){
			String next = reader.nextLine();
			test.add(next);
			real.add(next);
		}
		assertEquals("Size after filling", real.size(), test.size());
		for(int i = 0; i < real.size(); i++){
			assertEquals("Item in array " + i, real.get(i), test.get(i));
		}
	}

	@Test
	public void testAddIntAnyTypeFront() {
		Scanner reader = null;
		try {
			reader = new Scanner(new File("test1.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File Not Found!");
			System.exit(1);
		}
		
		MyLinkedList<String> test = new MyLinkedList<String>();
		LinkedList<String> real = new LinkedList<String>();
		while(reader.hasNextLine()){
			String next = reader.nextLine();
			test.add(0, next);
			real.add(0, next);
		}
		assertEquals("Size after filling", real.size(), test.size());
		for(int i = 0; i < real.size(); i++){
			assertEquals("Item in array " + i, real.get(i), test.get(i));
		}
	}
	
	
	@Test
	public void testAddIntTypeBack() {
		Scanner reader = null;
		try {
			reader = new Scanner(new File("test1.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File Not Found!");
			System.exit(1);
		}
		
		MyLinkedList<String> test = new MyLinkedList<String>();
		LinkedList<String> real = new LinkedList<String>();
		while(reader.hasNextLine()){
			String next = reader.nextLine();
			test.add(test.size()/2, next);
			real.add(real.size()/2, next);
		}
		assertEquals("Size after filling", real.size(), test.size());
		for(int i = 0; i < real.size(); i++){
			assertEquals("Item in array " + i, real.get(i), test.get(i));
		}
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddLeftException() throws Exception {
		MyLinkedList<Integer> test = new MyLinkedList<Integer>();
		test.add(-1, 5);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddRightException() throws Exception {
		MyLinkedList<Integer> test = new MyLinkedList<Integer>();
		test.add(test.size()+1, 5);
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddNullException() throws Exception{
		MyLinkedList<Integer> test = new MyLinkedList<Integer>();
		test.add(null);
	}
	
	@Ignore
	public void testAddEfficiency(){
		MyLinkedList<String> test = new MyLinkedList<String>();
		Scanner reader = null;
		try{
			reader = new Scanner(new File("huge-wordlist.txt"));
		} catch (FileNotFoundException e){
			System.out.println("File not found!");
		}
		
		int i = 0;
		while (reader.hasNext()){
			test.add(reader.next());
			if(i%10000 == 0) System.out.println(test.get(i));
			i++;
		}
	}
	
	@Test
	public void testGetInt() {
		MyLinkedList<Integer> test = new MyLinkedList<Integer>();
		LinkedList<Integer> real = new LinkedList<Integer>();
		test.add(2);
		real.add(2);
		assertEquals("Array contents", real.get(0), test.get(0));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetLeftException() throws Exception {
		MyLinkedList<Integer> test = new MyLinkedList<Integer>();
		test.get(-1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetRightException() throws Exception {
		MyLinkedList<Integer> test = new MyLinkedList<Integer>();
		test.get(test.size()+1);
	}

	@Test
	public void testSet(){
		Scanner reader = null;
		try {
			reader = new Scanner(new File("test1.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File Not Found!");
			System.exit(1);
		}
			
		MyLinkedList<String> test = new MyLinkedList<String>();
		LinkedList<String> real = new LinkedList<String>();
		while(reader.hasNextLine()){
			String next = reader.nextLine();
			test.add(next);
			real.add(next);
		}
		
		int index = real.size();
		for(String sent : real){
			index += -1;
			assertEquals("Item removed from array " + index, real.set(index, sent), test.set(index, sent));
			assertEquals("Item added to array " + index, real.get(index), test.get(index));
		}
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetIndexException(){
		MyLinkedList<Integer> test = new MyLinkedList<Integer>();
		test.set(5, 5);
	}
	
	@Test(expected = NullPointerException.class)
	public void testSetNullException() throws Exception{
		MyLinkedList<Integer> test = new MyLinkedList<Integer>();
		test.add(5);
		test.set(0, null);
	}
	
	@Test
	public void testRemove() {
		Scanner reader = null;
		try {
			reader = new Scanner(new File("test1.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File Not Found!");
			System.exit(1);
		}
			
		MyLinkedList<String> test = new MyLinkedList<String>();
		LinkedList<String> real = new LinkedList<String>();
		while(reader.hasNextLine()){
			String next = reader.nextLine();
			test.add(next);
			real.add(next);
		}
		
		int line = 0;
		MyLinkedList<String> test2 = new MyLinkedList<String>();
		LinkedList<String> real2 = new LinkedList<String>();
		while(line < real.size()){
			test2.add(test.remove(line));
			real2.add(real.remove(line));
			line += 2;
		}
		
		assertEquals("Size after additions", test2.size(), real2.size());
		for(int i = 0; i < real2.size(); i++){
			assertEquals("Removed item " + i, test2.get(i), real2.get(i));
		}
		
		assertEquals("Size after removals", test.size(), real.size());
		for(int j = 0; j < real.size(); j++){
			assertEquals("Remaining item " + j, test.get(j), real.get(j));
		}
	}
	
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveException(){
		MyLinkedList<Integer> test = new MyLinkedList<Integer>();
		test.add(1);
		test.remove(2);
	}
	
	
	@Test
	public void testIsEmpty(){
		MyLinkedList<Integer> test = new MyLinkedList<Integer>();
		assertTrue("Initial", test.isEmpty());
		test.add(1);
		assertFalse("After addition", test.isEmpty());	
	}
	
	@Test
	public void testClear(){
		Scanner reader = null;
		try {
			reader = new Scanner(new File("test1.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File Not Found!");
			System.exit(1);
		}
			
		MyLinkedList<String> test = new MyLinkedList<String>();
		LinkedList<String> real = new LinkedList<String>();
		while(reader.hasNextLine()){
			String next = reader.nextLine();
			test.add(next);
			real.add(next);
		}
		test.clear();
		real.clear();
		assertEquals("Cleared", test.size(), real.size(), 0);
	}
	
	/** test of iterator next, prev, hasNext, hasPrev, and remove methods
	 */
	@Test
	public void testIterator(){
		MyLinkedList<Integer> test = new MyLinkedList<Integer>();
		LinkedList<Integer> real = new LinkedList<Integer>();	
		int size = 100;
		for(int i = 0; i < size; i++){
			test.add(i);
			real.add(i);
		}
		
		ListIterator<Integer> testit = test.listIterator();
		ListIterator<Integer> realit = real.listIterator();
		
		int testval;
		int realval;
		int item = 0;
		int j = 2;
		while(j < Math.sqrt(size)){
			item = 0;
			while(testit.hasNext()){
				testval = testit.next();
				realval = realit.next();
				assertEquals("item " + item + " in list on run " + j, testval, realval);
				if(testval > j && testval%j == 0) testit.remove();
				if(realval > j && realval%j == 0) realit.remove();
				item++;
			}
			j++;
			item = test.size();
			while(testit.hasPrevious()){
				testval = testit.previous();
				realval = realit.previous();
				assertEquals("item " + item + " in list on run " + j, testval, realval);
				if(testval > j && testval%j == 0) testit.remove();
				if(realval > j && realval%j == 0) realit.remove();
				item = item - 1;
			}
			j++;
		}
		while(testit.hasNext()){
			System.out.println(testit.next());
		}
	}
	
	/** test of the iterator next method  (using iterator as opposed to listIterator)
	 */
	@Test
	public void testIteratorNext(){
		MyLinkedList<Integer> test = new MyLinkedList<Integer>();
		LinkedList<Integer> real = new LinkedList<Integer>();	
		
		int size = 10;
		for(int i = 0; i < size; i++){
			test.add(i);
			real.add(i);
		}
		
		Iterator<Integer> testit = test.iterator();
		Iterator<Integer> realit = real.iterator();
		int item = 0;
		while(testit.hasNext()){
			item++;
			assertEquals("item in list " + item, testit.next(), realit.next());
		}
	}
	
	/** test of the iterator add method
	 */
	@Test
	public void testIteratorAdd(){
		MyLinkedList<Integer> test = new MyLinkedList<Integer>();
		LinkedList<Integer> real = new LinkedList<Integer>();
		
		for(int i = 0; i < 10; i++){
			test.add(i);
			real.add(i);
		}
		
		ListIterator<Integer> testit = test.listIterator();
		ListIterator<Integer> realit = real.listIterator();
		
		int j = 0;
		while(j < 10){
			for(int k = 0; k < j; k++){
				if(testit.hasNext()){
					int testnext = testit.next();
					int realnext = realit.next();
					System.out.println(testnext + " " + realnext);
					assertEquals(testnext, realnext);
				}
			}
			j++;
			System.out.println(j);
			testit.add(j);
			realit.add(j);
		}
	}
	
	/** test of the ConcurrentModificationException in the add method of the iterator
	 */
	@Test (expected = ConcurrentModificationException.class)
	public void testIteratorAddModException(){
		MyLinkedList<Integer> test = new MyLinkedList<Integer>();
		ListIterator<Integer> testit = test.listIterator();
		ListIterator<Integer> testit2 = test.listIterator();
		test.add(1);
		testit2.next();
		testit.next();
		testit.add(1);
		testit2.add(1);
	}
	
	/** test of the ConcurrentModificationException in the next method of the iterator
	 */
	@Test (expected = ConcurrentModificationException.class)
	public void testIteratorNextModException(){
		MyLinkedList<Integer> test = new MyLinkedList<Integer>();
		ListIterator<Integer> testit = test.listIterator();
		ListIterator<Integer> testit2 = test.listIterator();
		test.add(1);
		testit.next();
		testit.add(1);
		testit2.next();
	}
	
	/** test of the ConcurrentModificationException in the previous method of the iterator
	 */
	@Test (expected = ConcurrentModificationException.class)
	public void testIteratorPrevModException(){
		MyLinkedList<Integer> test = new MyLinkedList<Integer>();
		ListIterator<Integer> testit = test.listIterator();
		ListIterator<Integer> testit2 = test.listIterator();
		test.add(1);
		test.add(2);
		testit2.next();
		testit2.next();
		testit.next();
		testit.add(1);
		testit2.previous();
	}
	
	/** test of the ConcurrentModificationException in the remove method of the iterator
	 */
	@Test (expected = ConcurrentModificationException.class)
	public void testIteratorRemoveModException(){
		MyLinkedList<Integer> test = new MyLinkedList<Integer>();
		ListIterator<Integer> testit = test.listIterator();
		ListIterator<Integer> testit2 = test.listIterator();
		test.add(1);
		testit2.next();
		testit.next();
		testit.add(1);
		testit2.remove();
	}
	
	/** test of the NoSuchElementException in the next method of the iterator
	 */
	@Test (expected = NoSuchElementException.class)
	public void testIteratorNextNoElementException(){
		MyLinkedList<Integer> test = new MyLinkedList<Integer>();
		ListIterator<Integer> testit = test.listIterator();
		testit.next();
	}
	
	/** test of the NoSuchElementException in the previous method of the iterator
	 */
	@Test (expected = NoSuchElementException.class)
	public void testIteratorPrevtNoElementException(){
		MyLinkedList<Integer> test = new MyLinkedList<Integer>();
		ListIterator<Integer> testit = test.listIterator();
		testit.previous();
	}
	
	/** test of the IllegalStateException in the set method of the iterator
	 */
	@Test (expected = IllegalStateException.class)
	public void testIteratorSetIllegalStateException(){
		MyLinkedList<Integer> test = new MyLinkedList<Integer>();
		ListIterator<Integer> testit = test.listIterator();
		test.add(1);
		test.add(2);
		testit.next();
		testit.remove();
		testit.set(2);
	}
	
	/** test of the IllegalStateException in the remove method of the iterator
	 */
	@Test (expected = IllegalStateException.class)
	public void testIteratorRemoveIllegalStateException(){
		MyLinkedList<Integer> test = new MyLinkedList<Integer>();
		ListIterator<Integer> testit = test.listIterator();
		test.add(1);
		testit.next();
		testit.add(3);
		testit.remove();
	}
	
	/** Running CollectionTimer
	 */
	@Ignore
	public void testRuntimeExploration(){
		String[] args = new String[4];
		args[0] = ("medium-wordlist.txt");
		args[1] = ("pride-and-prejudice.txt");
		args[2] = "5";
		args[3] = "5";
		CollectionTimer.main(args);
	}
}