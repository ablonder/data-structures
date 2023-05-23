import static org.junit.Assert.*;
import java.util.Stack;
import org.junit.Test;
import java.util.NoSuchElementException;

public class MyStackTest {

    @Test
    public void testMyStack() {
	Stack<String> real = new Stack<String>();
	MyStack<String> fake = new MyStack<String>();
	assertTrue("Lengths unequal after construction", real.size() == fake.size());
	assertTrue("Isn't empty after construction", fake.isEmpty());
    }

    @Test
    public void testPush() {
	Stack<String> real = new Stack<String>();
	MyStack<String> fake = new MyStack<String>();
	assertTrue("Lengths unequal after construction", real.size() == fake.size());
	assertTrue("Isn't empty after construction", fake.isEmpty());
	real.push("Pushed Element");
	fake.push("Pushed Element");
	assertTrue("Different pushed things", real.peek().equals(fake.top()));
	assertEquals("Wrong size", real.size(),fake.size());
	real.push("Pushed Element 2");
	fake.push("Pushed Element 2");
	assertTrue("Different pushed things", real.peek().equals(fake.top()));
	assertEquals("Wrong size", real.size(),fake.size());
	real.push("Pushed Element 3");
	fake.push("Pushed Element 3");
	assertTrue("Different pushed things", real.peek().equals(fake.top()));
	assertEquals("Wrong size", real.size(),fake.size());
    }

    @Test(expected=NoSuchElementException.class)
    public void testPop() throws NoSuchElementException
    {
	Stack<String> real = new Stack<String>();
	MyStack<String> fake = new MyStack<String>();
	assertTrue("Lengths unequal after construction", real.size() == fake.size());
	assertTrue("Isn't empty after construction", fake.isEmpty());
	real.push("Pushed Element");
	fake.push("Pushed Element");
	assertTrue("Different pushed things", real.peek().equals(fake.top()));
	assertEquals("Wrong size", real.size(),fake.size());
	real.push("Pushed Element 2");
	fake.push("Pushed Element 2");
	assertTrue("Different pushed things", real.peek().equals(fake.top()));
	assertEquals("Wrong size", real.size(),fake.size());
	assertTrue("Different popped things", real.pop().equals(fake.pop()));
	assertEquals("Wrong size after pop", real.size(),fake.size());
	assertTrue("Different popped things", real.pop().equals(fake.pop()));
	assertEquals("Wrong size", real.size(),fake.size());
	fake.pop();
    }

    @Test(expected=NoSuchElementException.class)
    public void testTop() throws NoSuchElementException
    {
	MyStack<String> fake = new MyStack<String>();
	fake.top();
    }

    @Test
    public void testIsEmpty() {
	Stack<String> real = new Stack<String>();
	MyStack<String> fake = new MyStack<String>();
	assertTrue("Lengths unequal after construction", real.size() == fake.size());
	assertTrue("Isn't empty after construction", fake.isEmpty());
	real.push("Pushed Element");
	fake.push("Pushed Element");
	assertTrue("Different pushed things", real.peek().equals(fake.top()));
	real.push("Pushed Element 2");
	fake.push("Pushed Element 2");
	assertTrue("Different pushed things", real.peek().equals(fake.top()));
	assertTrue("Different popped things", real.pop().equals(fake.pop()));
	assertTrue("Different popped things", real.pop().equals(fake.pop()));
	assertTrue("Test is wrong", real.isEmpty());
	assertTrue("isEmpty fails after pushing then popping", fake.isEmpty());
    }

    @Test
    public void testClear() {
	Stack<String> real = new Stack<String>();
	MyStack<String> fake = new MyStack<String>();
	assertTrue("Lengths unequal after construction", real.size() == fake.size());
	assertTrue("Isn't empty after construction", fake.isEmpty());
	real.push("Pushed Element");
	fake.push("Pushed Element");
	assertTrue("Different pushed things", real.peek().equals(fake.top()));
	real.push("Pushed Element 2");
	fake.push("Pushed Element 2");
	real.clear();
	fake.clear();
	assertTrue("Test is wrong", real.isEmpty());
	assertTrue("isEmpty fails after clearing", fake.isEmpty());
	assertEquals("Wrong size", real.size(),fake.size());
	real.push("3");
	fake.push("4");
	assertTrue("Test is wrong", !real.isEmpty());
	assertTrue("isEmpty fails after clearing then adding", !fake.isEmpty());
	assertEquals("Wrong size", real.size(),fake.size());
	
    }

}