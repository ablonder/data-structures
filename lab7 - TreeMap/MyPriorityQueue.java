import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Min-heap based implementation of a priority queue
 * 
 * @author Aviva Blonder
 * 
 */
public class MyPriorityQueue<Type> extends AbstractQueue<Type> {
	ArrayList<Type> heap;
	Comparator<Type> comparator;
	
	/**
	 * initializes compare to comp and heap as a new ArrayList
	 * 
	 * @param comp
	 */
	public MyPriorityQueue(Comparator<Type> comp){
		comparator = comp;
		heap = new ArrayList<Type>();
		heap.add(null);
	}
	
	/**
	 * returns the size of the heap
	 * 
	 * @return size() of heap
	 */
	public int size(){
		return heap.size()-1;
	}
	
	/**
	 * empties the heap
	 */
	public void clear(){
		heap.clear();
	}
	
	/**
	 * returns the smallest value in the heap
	 * 
	 * @return the value at index 1 in heap
	 */
	public Type peek(){
		return heap.get(1);
	}
	
	/**
	 * removes and returns the smallest value in the heap and reorganizes the heap
	 * 
	 * @return the value at index 1 in heap
	 */
	public Type poll(){
		swap(1, size());
		Type remove = heap.remove(size());
		percolateDown(1);
		return remove;
	}
	
	/**
	 * adds item to the correct place in the heap
	 * 
	 * @param item
	 * @return true
	 */
	public boolean offer(Type item){
		heap.add(item);
		percolateUp(this.size());
		heap.set(0, null);
		return true;
	}
	
	/**
	 * returns an iterator through the items in heap
	 * 
	 * @return a new HeapIterator
	 */
	public Iterator<Type> iterator(){
		return new HeapIterator<Type>();
	}
	
	/**
	 * sets comparator to comp
	 * 
	 * @param comp
	 */
	public void setComparator(Comparator<Type> comp){
		comparator = comp;
		for(int i = (this.size()-1)/2; i > 0; i--){
			percolateDown(i);
		}
		heap.set(0, null);
	}
	
	/**
	 * moves the value at index hole down until it is in a valid location in heap
	 * 
	 *  @param hole
	 */
	private void percolateDown(int hole){
		int left = leftChild(hole);
		int right = rightChild(hole);
		if(left > size());
		else if(right > size()){
			if(comparator.compare(heap.get(hole), heap.get(left)) > 0)
				swap(hole, left);
		} else {
			if((comparator.compare(heap.get(hole), heap.get(left)) > 0)||
					(comparator.compare(heap.get(hole), heap.get(right)) > 0)){
				if(comparator.compare(heap.get(left), heap.get(right)) < 0){
					swap(hole, left);
					percolateDown(left);
				} else {
					swap(hole, right);
					percolateDown(right);
				}
			}
		}
	}
	
	/**
	 * moves the value at index hole up through heap until it is in a valid location
	 * 
	 * @param hole
	 */
	private void percolateUp(int hole){
		if(hole > 1){
			int parent = parent(hole);
			if(comparator.compare(heap.get(hole), heap.get(parent)) < 0){
				swap(hole, parent);
				percolateUp(parent);
			}
		}
	}
	
	/**
	 * switches the values at indices thing1 and thing2
	 * 
	 * @param thing1
	 * @param thing2
	 */
	private void swap(int thing1, int thing2){
		heap.set(0, heap.get(thing1));
		heap.set(thing1, heap.get(thing2));
		heap.set(thing2, heap.get(0));
	}
	
	/**
	 * returns the index of the parent of index x
	 * 
	 * @param x
	 * @return x/2 if x is even and (x-1)/2 if x is odd
	 */
	private int parent(int x){
		if(x%2 == 0) return x/2;
		else return (x-1)/2;
	}
	
	/**
	 * returns the index of the left child of index x
	 * 
	 * @param x
	 * @return x*2
	 */
	private int leftChild(int x){
		return x*2;
	}
	
	/**
	 * returns the index of the right child of index x
	 * 
	 * @param x
	 * @return x*2 + 1
	 */
	private int rightChild(int x){
		return x*2 + 1;
	}
	
	/** Iterator for heap */
	private class HeapIterator<T> implements Iterator<T>{
		int next;
		int prev;
		int last;
		
		/** Initializes next to 1, and prev and last to invalid values */
		public HeapIterator(){
			next = 1;
			prev = 0;
			last = 0;
		}
		
		/**
		 * Returns whether there is a next item in the list
		 * 
		 * @return true if next is less than or equal to size() or false otherwise
		 */
		public boolean hasNext(){
			if(next > size()) return false;
			else return true;
		}
		
		/**
		 * Returns the next item in heap, sets last to next, and increments next and prev
		 * 
		 * @return the value in heap at index next
		 * @throws IndexOutOfBoundsException
		 */
		public T next() throws IndexOutOfBoundsException {
			if(next > size()) throw new IndexOutOfBoundsException("You've reached the end of the list.");
			else{
				last = next;
				next++;
				prev++;
				return (T) heap.get(last);
			}
		}
		
		/**
		 * Returns whether there is  previous item in the list
		 * 
		 * @return true if prev is greater than 0 and false otherwise
		 */
		public boolean hasPrevious(){
			if(prev < 1) return false;
			else return true;
		}
		
		/**
		 * Returns prev, sets last to prev, and decreases next and prev
		 * 
		 * @return prev
		 * @throws IndexOutOfBoundsException
		 */
		public T previous() throws IndexOutOfBoundsException {
			if(prev < 1) throw new IndexOutOfBoundsException("You've reached the beginning of the list.");
			else{
				last = prev;
				next--;
				prev--;
				return (T) heap.get(last);
			}
		}
		
		/**
		 * Removes the value associated with last and reorganizes the heap
		 * 
		 * @throws IllegalStateException
		 */
		public void remove() throws IllegalStateException{
			if(last > 0){
				swap(last, size());
				heap.remove(size());
				percolateDown(last);
			}
			else throw new IllegalStateException("No last item to remove.");
		}
	}
}
