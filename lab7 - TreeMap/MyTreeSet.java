/**
 * Implementation of a set using MyTreeMap
 * 
 * @author Aviva Blonder
 */

import java.util.AbstractSet;
import java.util.Iterator;


public class MyTreeSet<T extends Comparable<? super T>> extends AbstractSet<T> {

    /** Backing storage for the set */
    private MyTreeMap<T, Boolean> set;
    
    /**
     * initializes the set as a new MyTreeMap
     */
    public MyTreeSet(){
    	set = new MyTreeMap<T, Boolean>();
    }
    
    /**
     * adds a node with key item and value true to set
     * 
     * @param item
     * @return true if the set does not contain a node with the key item and false otherwise
     */
    public boolean add(T item){
    	if(!set.containsKey(item)){
    		set.put(item, true);
    		return true;
    	}
    	return false;
    }
    
    /**
     * an iterator that goes through set inorder 
     * 
     * @return iterator through the keys of set
     */
    public Iterator<T> iterator(){
    	return set.keys("in");
    }
    
    /**
     * returns the size of set
     * 
     * @return size of set
     */
    public int size(){
    	return set.size;
    }
    
    /**
     * empties set by creating a new MyTreeMap
     */
    public void clear(){
    	set = new MyTreeMap<T, Boolean>();
    }

}
