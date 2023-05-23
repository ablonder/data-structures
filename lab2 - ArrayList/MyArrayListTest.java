/**Test of implementation of MyArrayList Class
 *
 *@author Aviva Blonder
 *
 *I affirm that I have adhered to the Honor Code on this assignment.
 **/


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Ignore;
import org.junit.Test;


public class MyArrayListTest {

	@Test
	public void testSize() {
		MyArrayList<Integer> test = new MyArrayList<Integer>();
		ArrayList<Integer> real = new ArrayList<Integer>();
		assertEquals("Size after construction", real.size(), test.size());
		test.add(0, 5);
		real.add(0, 5);
		assertEquals("Size after add", real.size(), test.size());
	}

	@Test
	public void testMyArrayListInt() {
		MyArrayList<Integer> test = new MyArrayList<Integer>(10);
		ArrayList<Integer> real = new ArrayList<Integer>(10);
		assertEquals("Size", real.size(), test.size());
	}

	@Test
	public void testMyArrayList() {
		MyArrayList<Integer> test = new MyArrayList<Integer>();
		ArrayList<Integer> real = new ArrayList<Integer>();
		assertEquals("Size after construction", real.size(), test.size());
	}

	@Test
	public void testAddIntType() {
		MyArrayList<Integer> test = new MyArrayList<Integer>();
		ArrayList<Integer> real = new ArrayList<Integer>();
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
			
		MyArrayList<String> test = new MyArrayList<String>();
		ArrayList<String> real = new ArrayList<String>();
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
		
		MyArrayList<String> test = new MyArrayList<String>();
		ArrayList<String> real = new ArrayList<String>();
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
		
		MyArrayList<String> test = new MyArrayList<String>();
		ArrayList<String> real = new ArrayList<String>();
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
		MyArrayList<Integer> test = new MyArrayList<Integer>();
		test.add(-1, 5);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddRightException() throws Exception {
		MyArrayList<Integer> test = new MyArrayList<Integer>();
		test.add(test.size()+1, 5);
	}
	
	@Ignore
	public void testAddEfficiency(){
		MyArrayList<Integer> test = new MyArrayList<Integer>();
		for(int i = 0; i < 1000000; i++){
			test.add(0, i);
			if(i%10000 == 0) System.out.println(test.get(0));
		}
	}
	
	@Test
	public void testGetInt() {
		MyArrayList<Integer> test = new MyArrayList<Integer>();
		ArrayList<Integer> real = new ArrayList<Integer>();
		test.add(2);
		real.add(2);
		assertEquals("Array contents", real.get(0), test.get(0));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetLeftException() throws Exception {
		MyArrayList<Integer> test = new MyArrayList<Integer>();
		test.get(-1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetRightException() throws Exception {
		MyArrayList<Integer> test = new MyArrayList<Integer>();
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
			
		MyArrayList<String> test = new MyArrayList<String>();
		ArrayList<String> real = new ArrayList<String>();
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
	public void testSetException(){
		MyArrayList<Integer> test = new MyArrayList<Integer>();
		test.set(5, 5);
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
			
		MyArrayList<String> test = new MyArrayList<String>();
		ArrayList<String> real = new ArrayList<String>();
		while(reader.hasNextLine()){
			String next = reader.nextLine();
			test.add(next);
			real.add(next);
		}
		
		int line = 0;
		MyArrayList<String> test2 = new MyArrayList<String>();
		ArrayList<String> real2 = new ArrayList<String>();
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
		MyArrayList<Integer> test = new MyArrayList<Integer>();
		test.add(1);
		test.remove(2);
	}
	
	@Ignore
	public void testRemoveEfficiency(){
		MyArrayList<Integer> test = new MyArrayList<Integer>();
		for(int i = 0; i < 1000000; i++) test.add(i);
		while(test.size() > 0){
			if(test.size()%10000 == 0) System.out.println(test.remove(test.size()-1));
			else test.remove(test.size()-1);
			
		}
	}
	
	@Test
	public void testIsEmpty(){
		MyArrayList<Integer> test = new MyArrayList<Integer>();
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
			
		MyArrayList<String> test = new MyArrayList<String>();
		ArrayList<String> real = new ArrayList<String>();
		while(reader.hasNextLine()){
			String next = reader.nextLine();
			test.add(next);
			real.add(next);
		}
		test.clear();
		real.clear();
		assertEquals("Cleared", test.size(), real.size(), 0);
	}
	
	@Ignore
	public void testMemory(){
		MyArrayList<Integer> test = new MyArrayList<Integer>();
		for(int i = 0; i >= 0; i++){
			test.add(i);
			if(i%10000 == 0) System.out.println(test.get(i));
		}
	}

	@Test
	public void testMain() {
		fail("Not yet implemented");
	}

}
