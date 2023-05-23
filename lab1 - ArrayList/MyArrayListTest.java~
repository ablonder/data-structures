package lab2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
		fail("Not yet implemented");
	}

	@Test
	public void testMyArrayList() {
		MyArrayList<Integer> test = new MyArrayList<Integer>();
		ArrayList<Integer> real = new ArrayList<Integer>();
		assertEquals("Size after construction", real.size(), test.size());
	}

	@Test
	public void testAddIntType() {
		fail("Not yet implemented");
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
			test.add(test.size()/2, next);
			real.add(real.size()/2, next);
		}
		assertEquals("Size after filling", real.size(), test.size());
		for(int i = 0; i < real.size(); i++){
			assertEquals("Item in array " + i, real.get(i), test.get(i));
		}
	}

	@Test
	public void testGetInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testMain() {
		fail("Not yet implemented");
	}

}
