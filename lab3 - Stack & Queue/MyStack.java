import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Implements MyStack class, implementation of StackADT interface
 * 
 * @author Aviva Blonder
 *
 * @param <Type>
 */

public class MyStack<Type> implements StackADT<Type> {
	private ArrayList<Type> data;
	private int size;
	
	/**
	 * initializes data as an empty ArrayList
	 * initializes size as 0
	 */
	public MyStack(){
		this.data = new ArrayList<Type>();
		this.size = 0;
	}

	/**
	 * Adds an item on to the stack
	 * @param item Type object to add to the stack
	 */
	public void push(Type item){
		this.data.add(item);
		this.size++;
	}
	
	/**
	 * Removes the top item from the stack
	 * @return the last item of data
	 * @throws NoSuchElementException if the stack is empty
	 */
	public Type pop(){
		if(this.size == 0) throw new NoSuchElementException("The stack is empty, there are no elements to pop!");
		else{
			this.size = this.size-1;
			Type item = this.data.get(this.size);
			this.data.remove(this.size);
			return item;
		}
	}
	
	/**
	 * Shows the top item of the stack
	 * @return the last item of data
	 * @throws NoSuchElementException if the stack is empty
	 */
	public Type top(){
		if(this.size == 0) throw new NoSuchElementException("The stack is empty, there is no top element to see!");
		else{
			return this.data.get(this.size-1);
		}
	}
	
	/**
	 * Shows the size of the stack
	 * @return size
	 */
	public int size(){
		return this.size;
	}
	
	/**
	 * Says if the stack is empty
	 * @return true if size is 0, false if not
	 */
	public boolean isEmpty(){
		if(this.size == 0) return true;
		else return false;
	}
	
	/**
	 * Clears out the stack
	 */
	public void clear(){
		this.data = new ArrayList<Type>();
		this.size = 0;
	}
}