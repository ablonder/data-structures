/** implementation of (Most Recently Used) MRUList extension of MyLinkedList
 * 
 * @author Aviva Blonder
 */

package lab4;

public class MRUList<Type> extends MyLinkedList<Type> {
	
	/** adds new items to the front of the list
	 * 
	 * @return true
	 */
	public boolean add(Type data){
		super.add(0, data);
		return true;
	}
	
	/** adds new items to the front of the list
	 */
	public void add(int index, Type data){
		super.add(0, data);
	}
	
	/** checks to see if the list contains an object, if so, it moves that object to the front and returns true, otherwise it returns false
	 */
	public boolean contains(Object o){
		MyLinkedListIterator<Type> it = new MyLinkedListIterator<Type>(this);
		Type next;
		while(it.hasNext()){
			next = it.next();
			if(next == o){
				it.remove();
				this.add(next);
				return true;
			}
		}
		return false;
	}

}
