/** Implements MyLinkedList class, extension of AbstractList
 * 
 * @author Aviva Blonder
 * 
 * @param<Type>
 */

package lab4;

import java.util.AbstractList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList<Type> extends AbstractList<Type> {
	
	/** Implements nested Node class that holds the data and connections of the linked list
	 *
	 * @param <Type>
	 */
	class Node {
		private Node next;
		private Node prev;
		private Type data;
		
		/**Constructor initializes data to the supplied value
		 * 
		 * @param value
		 */
		public Node(Type value){
			this.data = value;
		}
		
		/** Sets next to the supplied node
		 * 
		 * @param next
		 */
		public void setNext(Node next){
			this.next = next;
			if(next.getPrev() != this) next.setPrev(this);
		}
		
		/** Sets prev to the supplied node
		 * 
		 * @param prev
		 */
		public void setPrev(Node prev){
			this.prev = prev;
			if(prev.getNext() != this) prev.setNext(this);
		}
		
		/** returns next
		 * 
		 * @return next
		 */
		public Node getNext(){
			return this.next;
		}
		
		/** returns prev
		 * 
		 * @return prev
		 */
		public Node getPrev(){
			return this.prev;
		}
		
		public Type getVal(){
			return this.data;
		}
		
		/** replaces this.data with data and returns the previous value of this.data
		 * 
		 * @param data
		 * @return olddata
		 */
		public Type setVal(Type data){
			Type olddata = this.data;
			this.data = data;
			return olddata;
		}
	}
	
	
	private Node first;
	private Node last;
	private int size;
	private int modCount;
	
	/** Constructor initializes first and last as new nodes that point to each other and size to 0
	 */
	public MyLinkedList(){
		this.first = new Node(null);
		this.last = new Node(null);
		this.first.setNext(this.last);
		this.size = 0;
		this.modCount = 0;
	}
	
	/** Loops through the linked list to reach the node at index
	 * 
	 * @param index
	 * @return node
	 */
	private Node getNth(int index){
		if(index > this.size || index < 0) throw new IndexOutOfBoundsException("You tried to access item " + index + " but the list is only " + this.size + " long.");
		Node node = this.first;
		for(int i = 0; i <= index; i++){
			node = node.getNext();
		}
		return node;
	}
	
	/** adds data to the end of the list
	 * 
	 * @param data
	 * @return true
	 */
	public boolean add(Type data){
		add(this.size, data);
		return true;
	}
	
	/** adds data to index location in the list
	 * 
	 * @param index, data
	 */
	public void add(int index, Type data){
		if(data == null) throw new NullPointerException("Null is not a valid value!");
		
		Node nextnode;
		Node newnode = new Node(data);
		if(index == this.size) nextnode = this.last;
		else nextnode = getNth(index);
		Node prevnode = nextnode.getPrev();
		newnode.setNext(nextnode);
		newnode.setPrev(prevnode);
		this.size++;
	}
	
	/** returns value at index
	 * 
	 * @param index
	 * @return value
	 */
	public Type get(int index){
		return getNth(index).getVal();
	}
	
	/** replaces the value at index with data
	 * 
	 * @param index, data
	 * @return prevval
	 */
	public Type set(int index, Type data){
		if(data == null) throw new NullPointerException("Null is not a valid value!");
		return getNth(index).setVal(data);
	}
	
	/** closes node at index out of the list, subtracts one from size, and returns the node's value
	 * 
	 * @param index
	 * @return the value of the node at index
	 */
	public Type remove(int index){
		Node node = getNth(index);
		node.getNext().setPrev(node.getPrev());
		this.size = this.size - 1;
		return node.getVal();
	}
	
	/** sets size to 0 and has first and last point at each other, closing out all other nodes
	 */
	public void clear(){
		this.size = 0;
		this.first.setNext(this.last);
	}
	
	/** returns true if the first is pointing at last and false if it is not
	 */
	public boolean isEmpty(){
		if(this.first.getNext() == this.last) return true;
		else return false;
	}
	
	/** returns the size of the list
	 * 
	 * @return this.size
	 */
	public int size(){
		return this.size;
	}
	
	/** factory method that returns a new list iterator for this linked list
	 * 
	 * @return new MyLinkedListIterator(this)
	 * 
	 * Note: rename to ListIterator before handin
	 */
	public ListIterator<Type> listIterator(){
		MyLinkedListIterator<Type> i = new MyLinkedListIterator<Type>(this);
		return i;
	}
	
	/** factor method that returns a new iterator for this linked list
	 * 
	 * @return new MyLinkedListIterator(this)
	 * 
	 * Note: rename to ListIterator before handin
	 */
	public Iterator<Type> iterator(){
		MyLinkedListIterator<Type> i = new MyLinkedListIterator<Type>(this);
		return i;
	}
	
	/** Implements an iterator for LinkedList
	 */
	class MyLinkedListIterator<T> implements ListIterator<Type> {
		private Node next;
		private Node prev;
		private int element;
		private MyLinkedList<Type> list;
		private Node lastreturn;
		private boolean forward;
		private int modCount;
		
		/** Constructor initializes iterator with a list and next and prev as first, element as 0, and last return as null
		 * 
		 * @param list
		 */
		public MyLinkedListIterator(MyLinkedList<Type> list){
			this.list = list;
			this.next = list.first;
			this.prev = list.first;
			this.element = 0;
			this.lastreturn = null;
			this.modCount = this.list.modCount;
		}
		
		/**restarts going through the list from the beginning
		 * 
		 */
		public void reset(){
			this.next = this.list.first;
			this.prev = this.list.first;
			this.element = 0;
			this.lastreturn = null;
		}
		
		/** returns true if next is not equal to the last value of list and false if it is
		 */
		public boolean hasNext(){
			if(this.next == this.list.last)	return false;
			return true;
		}
		
		/** moves prev and next forward one, returns the value of element and returns the previous value of next
		 * 
		 * @return value of next
		 */
		public Type next(){
			if(this.modCount != this.list.modCount) throw new ConcurrentModificationException("This list has been modified by another iterator");
			if(!this.hasNext() || this.list.size == 0) throw new NoSuchElementException("There are no more elements to iterate through.");
			
			this.lastreturn = this.next;
			this.forward = true;
			this.prev = this.next;
			Type stuff = get(element);
			this.element++;
			this.next = getNth(element);
			return stuff;
		}
		
		/** returns false if prev equals the first node of list and true if it does not
		 */
		public boolean hasPrevious(){
			if(this.prev == this.list.first) return false;
			else return true;
		}
		
		/** moves next and prev back one, subtracts one from element, and returns the previous value of prev
		 * 
		 * @return the value of prev
		 */
		public Type previous(){
			if(!this.hasPrevious()) throw new NoSuchElementException("You are at the beginning of the list. There are no previous elements");
			if(this.modCount != this.list.modCount) throw new ConcurrentModificationException("This list has been modified by another iterator.");
			this.lastreturn = this.prev;
			this.forward = false;
			this.next = this.list.getNth(this.element-1);
			this.prev = this.next.getPrev();
			this.element = this.element - 1;
			return this.next.getVal();
		}
		
		/** returns the next index
		 * 
		 * @return element
		 */
		public int nextIndex(){
			return this.element;
		}
		
		/** returns the previous index
		 * 
		 * @return element - 1
		 */
		public int previousIndex(){
			return this.element - 1;
		}
		
		/** sets the value of lastreturn to data
		 * 
		 * @param data
		 */
		public void set(Type data){
			if(this.modCount != this.list.modCount) throw new ConcurrentModificationException("This list has been modified by another iterator.");
			if(this.lastreturn == null) throw new IllegalStateException("There is no valid last node returned to set.");
			
			this.lastreturn.setVal(data);
			this.list.modCount++;
			this.modCount++;
		}
		
		/** removes lastreturn from the list
		 */
		public void remove(){
			if(this.modCount != this.list.modCount) throw new ConcurrentModificationException("This list has been modified by another iterator.");
			if(this.lastreturn == null) throw new IllegalStateException("There is no vaild last node returned to remove.");
			
			if(this.forward){
				this.list.remove(this.element-1);
				this.prev = this.list.getNth(this.element-1);
				this.element = this.element-1;
			}else{
				this.list.remove(this.element);
				this.next = this.list.getNth(this.element);
				
			}
			this.list.modCount++;
			this.modCount++;
			this.lastreturn = null;
		}
		
		/** adds data after last return and before next
		 */
		public void add(Type data){
			if(this.modCount != this.list.modCount) throw new ConcurrentModificationException("This list has been modified by another iterator.");
			
			this.list.add(this.element, data);
			this.prev = this.next.getPrev();
			this.element++;
			this.list.modCount++;
			this.modCount++;
			this.lastreturn = null;
		}
	}
}