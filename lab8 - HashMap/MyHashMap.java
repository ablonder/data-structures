/**
 * Implementation of a HashMap using an array of linked lists
 * 
 * @author Aviva Blonder
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MyHashMap<K, V> {
	LinkedList<MyEntry>[] table;
	int size;
	float loadFactor;
	
	/**
	 * Initializes table as an array of capacity empty LinkedLists, size as 0,
	 * and loadFactor as loadFactor
	 * 
	 * @param capacity
	 * @param loadFactor
	 */
	public MyHashMap(int capacity, float loadFactor){
		this.loadFactor = loadFactor;
		table = newTable(capacity);
		size = 0;
	}
	
	/**
	 * Creates a new array of size capacity composed of new linked lists
	 * 
	 * @param capacity
	 * @return new empty table of size capacity
	 */
	public LinkedList<MyEntry>[] newTable(int capacity){
		LinkedList<MyEntry>[] newtable = (LinkedList<MyEntry>[]) new LinkedList[capacity];
		for(int i = 0; i < capacity; i++)
			newtable[i] = new LinkedList<MyEntry>();
		return newtable;
	}
	
	/** Constructs a new MyHashMap with capacity 11 and loadFactor .75 */
	public MyHashMap(){
		this(11, (float) .75);
	}
	
	/**
	 * Returns size
	 * 
	 * @return size
	 */
	public int size(){
		return size;
	}
	
	/**
	 * Returns whether the hashmap is empty
	 * @return true if size is 0 and false otherwise
	 */
	public boolean isEmpty(){
		if(size == 0) return true;
		return false;
	}
	
	/** clears each LinkedList in table */
	public void clear(){
		for(LinkedList<MyEntry> list : table) list.clear();
		size = 0;
	}
	
	/**
	 * Creates a string of the contents of table
	 * 
	 * @return toString() of each LinkedList in table
	 */
	public String toString(){
		String str = "";
		for(LinkedList<MyEntry> list : table){
			str += "[";
			for(MyEntry entry : list){
				str += entry.toString() + " ";
			}
			str += "] ";
		}
		return str;
	}
	
	/**
	 * Adds key-value pair to the correct LinkedList in table
	 * 
	 * @param key
	 * @param value
	 * @return previous value of key if it already exists, or null otherwise
	 * @throws NullPointerException if value is null
	 */
	public V put(K key, V value){
		if(value == null || key == null)
			throw new NullPointerException("That's not a valid value!");
		int index = Math.abs(key.hashCode()%table.length);
		for(MyEntry item : table[index]){
			if(item.equals(key)){
				V val = item.value;
				item.value = value;
				return val;
			}
		}
		size++;
		table[index].add(new MyEntry(key, value));
		if(size/table.length > loadFactor) resize();
		return null;
	}
	
	/** Double table's capacity and rehash items into the new array */
	private void resize(){
		int len = 2*table.length + 1;
		int i = 2;
		while(i < Math.sqrt(len)){
			if(len%i == 0){
				len += 2;
				i = 2;
			} else {
				i++;
			}
		}
		LinkedList<MyEntry>[] newtable = newTable(len);
		for(LinkedList<MyEntry> list : table){
			for(MyEntry thing : list){
				int index = Math.abs(thing.key.hashCode()%len);
				newtable[index].add(thing);
			}
		}
		table = newtable;
	}
	
	/**
	 * Returns the value of the MyEntry designated by key
	 * @param key
	 * @return value of the MyEntry designated by key or null if absent
	 */
	public V get(K key){
		int index = Math.abs(key.hashCode()%table.length);
		for(MyEntry item : table[index]){
			if(item.equals(key)){
				return item.value;
			}
		}
		return null;
	}
	
	/**
	 * Removes the MyEntry designated by key from table and returns its value
	 * @param key
	 * @return value of the MyEntry designated by key or null if absent
	 */
	public V remove(K key){
		int index = Math.abs(key.hashCode()%table.length);
		int i = 0;
		for(MyEntry item : table[index]){
			if(item.equals(key)){
				V val = item.value;
				table[index].remove(i);
				size--;
				return val;
			}
			i++;
		}
		return null;
	}
	
	/**
	 * Determines if a MyEntry exists designated by key
	 * @param key
	 * @return true if key designates a MyEntry or false if not
	 */
	public boolean containsKey(K key){
		int index = Math.abs(key.hashCode()%table.length);
		for(MyEntry item : table[index]){
			if(item.equals(key)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Determines if a MyEntry exists with value
	 * 
	 * @param value
	 * @return true if a MyEntry exists with value and false otherwise
	 */
	public boolean containsValue(V value){
		for(LinkedList<MyEntry> list : table){
			for(MyEntry item : list){
				if(item.value.equals(value)){
					return true;
				}
			}
		}
		return false;			
	}
	
	/**
	 * An iterator through the keys in the hashtable
	 * 
	 * @return iterator through the keys of table
	 */
	public Iterator<K> keys(){
		return (Iterator<K>) new iterator<K>("key");
	}
	
	/**
	 * An iterator through the values in the hashtable
	 * 
	 * @return iterator through the values of table
	 */
	public Iterator<V> values(){
		return (Iterator<V>) new iterator<V>("value");
	}
	
	/**
	 * Iterator through table
	 * 
	 * @param <T>
	 */
	private class iterator<T> implements Iterator<T>{
		int bucket;
		int next;
		String type;
		K last;
		
		/**
		 * Initializes new iterator with type of type, bucket 0, and next 0
		 * 
		 * @param type
		 */
		public iterator(String type){
			this.type = type;
			bucket = 0;
			next = 0;
			last = null;
		}
		
		/**
		 * Determines if there is a next item in the hashtable to iterate through
		 * @return false if bucket and next are greater than the last item in table and true otherwise
		 */
		public boolean hasNext(){
			boolean good = false;
			while(!good){
				if(bucket >= table.length) return false;
				if(next >= table[bucket].size()){
					bucket++;
					next = 0;
				} else return true;
			}
			return true;
		}
		
		/**
		 * Increments next and bucket if necessary and returns that item
		 * 
		 * @return the item after table[bucket].get(next) in the table
		 * @throws NoSuchElementException
		 */
		public T next(){
			if(!hasNext()) throw new NoSuchElementException("You've passed the end of the list");
			MyEntry item = table[bucket].get(next);
			last = item.key;
			next++;
			if(type.equals("key")) return (T) last;
			return (T) item.value;
		}
		
		/**
		 * Removes the last item in table
		 * 
		 * @return the type of last
		 * @throws IllegalStateException
		 */
		public void remove(){
			if(last == null) throw new IllegalStateException("There is no last element to remove!");
			MyHashMap.this.remove(last);
		}
	}
	
	/** Nodes of key-value pairs that serve as entries in the LinkedLists in table */
	private class MyEntry{
		K key;
		V value;
		
		/**
		 * Initializes key and value
		 * 
		 * @param key
		 * @param value
		 */
		public MyEntry(K key, V value){
			this.value = value;
			this.key = key;
		}
		
		/**
		 * Calculates the hashCode of key
		 * 
		 * @return hashCode() of key
		 */
		public int hashCode(){
			return Math.abs(key.hashCode());
		}
		
		/**
		 * Determines if two entries have the same key
		 * @param other
		 * @return true if other equals() key and false otherwise
		 */
		public boolean equals(Object other){
			return key.equals(other);
		}
		
		/**
		 * Returns MyEntry in string form
		 * 
		 *@return toString of key, toString of value
		 */
		public String toString(){
			return "(" + key.toString() + ", " + value.toString() + ")";
		}
	}
}
