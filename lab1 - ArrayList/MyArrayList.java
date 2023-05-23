/** Implements MyArrayList class
 * 
 * @author Aviva Blonder
 */

package lab2;

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
				
		if(index == this.size) this.data[this.size] = element;
		else{
			Type[] newarray = (Type[]) new Object[this.data.length];
			for(int n = 0; n < index; n++) newarray[n] = this.data[n];
			newarray[index] = element;
			for(int i = index; i < this.size; i++) newarray[i+1] = this.data[i];
			this.data = newarray;
		}
	}
	
	public boolean add(Type element){
		this.add(this.size, element);
		return true;
	}
	
	public Type get(int index){
		if(index >= this.size) throw new IndexOutOfBoundsException("Index out of bounds! You tried to get an element at index " + index + ", but the list is only " + this.size + " items long.");
		else return this.data[index];
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
