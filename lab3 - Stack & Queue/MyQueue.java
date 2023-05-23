import java.util.NoSuchElementException;
/***Implements MyQueue Class, implementation of MyQueue interface
 * @author James Cumberbatch
 *
 * @param <AnyType>
 */
public class MyQueue<AnyType> implements QueueADT<AnyType>{
    /***
     * Node Class for a doubly-linked list
     * @author James Cumberbatch
     *
     * @param <AnyType>
     */
    private class MyNode<T>
    {
	private T data;//Data in the node
	private MyNode<T> next;//Next node
	private MyNode<T> prev;//Previous Node

	/***Initializes Node with data
	 */

	public MyNode(T dataC)
	{
	    data = dataC;
	    this.next = null;
	}

	/***Initializes Empty Node
	 */

	public MyNode()
	{
	    this(null);
	}

	/***Returns next node
	 * @return the next node
	 */

	public MyNode<T> getNext()
	{
	    return next;
	}
	
	/***Sets the next node
	 * @param next node
	 * @return old next node
	 */
	
	public MyNode<T> setNext(MyNode<T> newNext)
	{
	    MyNode<T> oldNext = next;
	    next = newNext;
	    return oldNext;
	}
	
	/**Sets the data to be something else
	 * @param new data
	 * @return old data
	 * */
	
	public T setData(T newData)
	{
	    T oldData = data;
	    data = newData;
	    return oldData;
	}
	
	/**Gets the data
	 * @return the data
	 * */
	
	public T getData()
	{
	    return data;
	}
	
	/***Gets the previous Node
	 * 
	 * @return the previous node
	 */
	
	public MyNode<T> getPrev()
	{
	    return prev;
	}
	
	/**Sets the previous Node
	 * @param the node which is to be behind this one
	 * @return the old previous node
	 * */
	
	public MyNode<T> setPrev(MyNode<T> newPrev)
	{
	    MyNode<T> oldPrev = prev;
	    prev = newPrev;
	    return oldPrev;
	}

    }
    private MyNode<AnyType> start;
    private MyNode<AnyType> end;
    private int size;
    
    /**Initializes empty start and end nodes
     */
    
    public MyQueue() 
    {
	start = new MyNode<AnyType>();
	end = new MyNode<AnyType>();
	start.setNext(end);
	end.setPrev(start);
	this.size = 0;
    }
    
    /**
     * Add an item to the queue
     * @param item the data item to add (of type AnyType)
     */
    
    public void enqueue(AnyType item)
    {
	end.getPrev().setNext(new MyNode<AnyType>(item));
	end.setPrev(end.getPrev().getNext());
	end.getPrev().setNext(end);
	this.size++;
    }
    
    /**
     * Remove the front item from the queue
     * @return the front item in the queue
     * @throws NoSuchElementException if the queue is empty
     */
    
    public AnyType dequeue() throws NoSuchElementException
    {
	if(start.getNext() == end)
	{
	    throw new NoSuchElementException("The list is empty");
	}
	MyNode<AnyType> oldStart = start.getNext();
	start.setNext(start.getNext().getNext());
	start.getNext().setPrev(start);
	this.size -= 1;
	return oldStart.getData();
    }
    
    /**
     * Return the front item in the queue without removing it
     * @return the front item in the queue
     * @throws NoSuchElementException if the queue is empty
     */
    
    public AnyType front() throws NoSuchElementException
    {
	if(start.getNext() == end)
	{
	    throw new NoSuchElementException("The list is empty");
	}
	return start.getNext().getData();
    }
    
    /**Returns whether or not the queue is empty
     * @return true if the (Null) start equals the (Null) end, false otherwise
     */
    
    public boolean isEmpty()
    {
	if(start.getNext() == end)
	{
	    return true;
	}
	else
	{
	    return false;
	}
    }
    /**
     * Find how many items are in the queue
     * @return the number of items in the queue
     */
    public int size()
    {
	return this.size;
    }
    /**
     * Clear out the data structure
     */
    public void clear()
    {
	start.setNext(end);
	end.setPrev(start);
    }

}