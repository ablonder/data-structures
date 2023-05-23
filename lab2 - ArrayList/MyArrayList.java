/** Implements MyArrayList class, an extension of the AbstractList class
 * 
 * @author Aviva Blonder
 * 
 * I affirm that I have adhered to the Honor Code on this assignment.
 */


import java.util.AbstractList;

public class MyArrayList<Type> extends AbstractList<Type> {
	
	private int size;
	private Type[] data;
	
	@SuppressWarnings("unchecked")
	public MyArrayList(int initlen){
		int reallen = 1;
		while(reallen < initlen) reallen = 2*reallen;
		Type[] initarray = (Type[]) new Object[reallen];
		this.data = initarray;
		this.size = 0;
	}
	
	public MyArrayList(){
		this(2);
	}
	
	@SuppressWarnings("unchecked")
	private void resize(){
		Type[] newarray = (Type[]) new Object[this.data.length*2];
		for(int n = 0; n < this.data.length; n++) newarray[n] = this.data[n];
		this.data = newarray;
	}
	
	public int size(){
		return this.size;
	}
	
	@SuppressWarnings("unchecked")
	public void add(int index, Type element){
		if(index > this.size) throw new IndexOutOfBoundsException("Index out of bounds! You tried to add an element at " + index + ", but the list is only " + this.size + " long.");

		this.size++;
		
		if(this.size >= this.data.length) resize();
				
		int i = this.size;
		while(i > index){
			this.data[i] = this.data[i-1];
			i -= 1;
		}
		this.data[index] = element;
	}
	
	public boolean add(Type element){
		this.add(this.size, element);
		return true;
	}
	
	public Type get(int index){
		if(index >= this.size) throw new IndexOutOfBoundsException("Index out of bounds! You tried to get an element at index " + index + ", but the list is only " + this.size + " items long.");
		else return this.data[index];
	}
	
	public Type set(int index, Type element){
		if(index >= this.size) throw new IndexOutOfBoundsException("Index out of bounds! You tried to replace an element at " + index + " but the list is only " + this.size + " long.");
		else {
			Type previous = this.data[index];
			this.data[index] = element;
			return previous;
		}
	}
	
	public Type remove(int index){
		if(index >= this.size) throw new IndexOutOfBoundsException("Index out of bounds! You tried to remove an element at index " + index + " but the list is only " + this.size + "items long");
		else {
			Type removed = this.data[index];
			for(int i = index; i < this.size-1; i++){
				this.data[i] = this.data[i+1];
			}
			this.data[this.size-1] = null;
			this.size -= 1;
			return removed;
		}
	}
	
	public boolean isEmpty(){
		if(this.size == 0) return true;
		else return false;
	}
	
	public void clear(){
		for(int i = 0; i < this.size; i++) this.data[i] = null;
		this.size = 0;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}