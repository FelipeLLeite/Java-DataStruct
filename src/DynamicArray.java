/**
 * Program name:	DynamicArray.java
 * Author:			Felipe L. Leite
 * Student nª:		0920253
 * Date:			Aug. 16, 2020
 * Description:		This file is my representation of dynamic array (vector).
 */

import java.util.Iterator;
/**
 * @param <T> represent any data type or object passed (generic data type)
 */ 


@SuppressWarnings("unchecked")
public class DynamicArray <T> implements Iterable<T> {
	
	// Set the global variables
	private T[] arr;			// Internal static array of generic type T
	private int len = 0;		// Length that represent the amount of element in the array but not its capacity
	private int capacity = 0;	// Actual array size


	/**
	 * Method:		Constructor method: DynamicArray()
	 * Purpose:		It will set the capacity of the dynamic array based on the parameter passed.
	 * Date:		Aug. 16, 2020
	 */
	public DynamicArray(int capacity) {
		if (capacity < 0)
			throw new IllegalArgumentException("Illegal Capacity: " + capacity);
		this.capacity = capacity; // Set the new 
		this.arr = (T[]) new Object[capacity];	// Create a array of type Object and type cast to a generic type <T>
	}
	
	/**
	 * Method:		Constructor method: DynamicArray()
	 * Purpose:		It will set the capacity of the dynamic array to 16 and sent to the constructor that accept the parameter.
	 * Date:		Aug. 16, 2020
	 */
	public DynamicArray() { this(16); }

	
	/**
	 * Method:		size()
	 * Purpose:		To return the amount of element in the array 		
	 * Accepts:		Nothing.
	 * Returns:		int.
	 * Date:		Aug. 15, 2020
	 */
	public int size() { return this.len; }
	
	/**
	 * Method:		isEmpty()
	 * Purpose:		t will test the return from the size method with the zero and return a boolean.
	 * Accepts:		Nothing.
	 * Returns:		boolean.
	 * Date:		Aug. 15, 2020
	 */
	public boolean isEmpty() { return size() == 0; }
		
	/**
	 * Method:		get()
	 * Purpose:		To return the element in the index passed in the method's parameter.
	 * Accepts:		int
	 * Returns:		An object of type T (from the iterable interface).
	 * Date:		Aug. 15, 2020
	 */
	public T get(int idx) {
		if (idx < 0 || idx >= this.len) 
			throw new IndexOutOfBoundsException();
		return this.arr[idx];
	}
	
	/**
	 * Method:		set()
	 * Purpose:		Set the element of a particular index to the passed argument
	 * Accepts:		int for the index and an element of type T (generic representation of any data type)
	 * Returns:		void.
	 * Date:		Aug. 15, 2020
	 */
	public void set(int idx, T elem) {
		if (idx < 0 || idx >= this.len) 
			throw new IndexOutOfBoundsException();
		arr[idx] = elem;
	}

	
	/**
	 * Method:		clear()
	 * Purpose:		To delete/remove all elements in the array and set its length to zero
	 * Accepts:		Nothing.
	 * Returns:		void.
	 * Date:		Aug. 15, 2020
	 */
	public void clear() {
		/**
		 * if you try to use the for-each structure Java will start to send you some warnings related to unused variable
		 * 
		 *	for (@SuppressWarnings("unused") T i : this.arr) i = null;
		 * 
		 * NOTE: the suppressWarning ("unused") could be add at the header of the method instead of the for-each loop parameter
		 */
		for (int idx = 0; idx < len; idx++) this.arr[idx] = null;
		
		this.len = 0;
	}
	
	/**
	 * Method:		add()
	 * Purpose:		To add and new element at the last position of the array
	 * Accepts:		Element of type T (generic representation of any data type)
	 * Returns:		void.
	 * Date:		Aug. 15, 2020
	 */
	public void add(T elem) {
		// Resize block
		if (this.len + 1 > this.capacity) {
			if (this.capacity == 0) { this.capacity = 1; }
			else capacity *= 2;	// Double the real size of the array
			T[] new_arr = (T[]) new Object[this.capacity];	// Create a new array 
			for (int i = 0; i < len; i++) new_arr[i] = this.arr[i]; // Pass the old array to the new one
		    this.arr = new_arr; // The "old" array became the new and bigger one
		} // end of if statement
		this.arr[this.len++] = elem; // The element "elem" is added to the end of the array
	}
	
	/**
	 * Method:		add()
	 * Purpose:		Add a new element into the array in a particular index. It will throw a exception if the 
	 * 				passed index is out of bound.
	 * Accepts:		int and element of type T (generic representation of any data type)
	 * Returns:		void.
	 * Date:		Aug. 16, 2020
	 * NOTE:		This method is an overload version of the above "add" method.
	 */
	public void add(int idx, T elem) {
		if (idx < 0 || idx > this.len)
			throw new IndexOutOfBoundsException();
		else if (idx == this.len) 
			add(elem); // Since it is telling to add at the end of the array
		else {
			// Resize block
			if (this.len + 1 > this.capacity) {
				if (this.capacity == 0) { this.capacity = 1; }
				else capacity *= 2;	// Double the real size of the array
				T[] new_arr = (T[]) new Object[this.capacity];	// Create a new array 
				for (int i = 0, j = 0; i < len; i++, j ++) {
					if (i == idx) {
						i--; // Fixing i temporarily to add the elem into the new array
						new_arr[j] = elem;
					}
					else
					new_arr[i] = this.arr[i]; // Pass the old array to the new one
				}
			    this.arr = new_arr; // The "old" array became the new and bigger one
			} // end of if statement
			//this.arr[this.len++] = elem; // The element "elem" is added to the end of the array
		}
		
		
	}
	
	
	/**
	 * Method:		removeAt()
	 * Purpose:		Removes an element at the specified index in this array. 
	 * Accepts:		int
	 * Returns:		void
	 * Date:		Aug. 15, 2020
	 */
	public void removeAt(int idx) {
		// Check if the parameter "idx" out of bound
		if (idx < 0 || idx >= this.len) 
			throw new IndexOutOfBoundsException();
		//T data = this.arr[idx]; // Create a final data to store the 
		T[] new_arr = (T[]) new Object[this.len - 1];
		/**
		 * Two pointers were create to parse into the array. One will parse throughout the whole array
		 * the other will be use to copy every element except the one each match if the parameter passed.
		 */
		for (int i = 0, j = 0; i < this.len; i++, j++)
			if (i == idx) j--; // Skip over "idx" by fixing j temporarily
			else new_arr[j] = this.arr[i];
		this.arr = new_arr;
		this.capacity = --this.len; // Reset the capacity to the new length of the array
		//return data; // The return could be stored in a variable or discarded by the JVM
	}

	/**
	 * Method:		remove()
	 * Purpose:		Removes the first appearance of element that is passed in the function parameters. 
	 * Accepts:		Any type of object
	 * Returns:		boolean.
	 * Date:		Aug. 15, 2020
	 */
	public boolean remove(Object obj) {
		int idx = indexOf(obj); // Call the function indexOf to find the 
		// If get at the end of the array (idx == -1) return false (didn't find any object in array that match with parameters)
		if (idx == -1) {
			System.out.println("Object not found in the array.");
			return false;
		}
		removeAt(idx);
		return true;
	}

	/**
	 * Method:		indexOf()
	 * Purpose:		Returns the first appearance of the object passed as parameter. 
	 * 				It will return -1 (out of bound) if the pointer does not find any match
	 * 
	 * 				NOTE: the @SuppressWarnings was added due java continued warning about:
	 * 					"Null pointer access: The variable obj can only be null at this location"
	 * 				even after the previews if statement check if there was a null pointer.
	 * 
	 * Accepts:		Any type of object
	 * Returns:		int.
	 * Date:		Aug. 15, 2020
	 */
	//@SuppressWarnings("null")
	public int indexOf(Object obj) {
		// for-loop to tes if the passed object exist in the array.
		for (int i = 0; i < this.len; i++) {
			if (obj.equals(this.arr[i])) return i;
		}
		return -1;
	}

	/**
	 * Method:		contains()
	 * Purpose:		Check if the object exist in the array
	 * Accepts:		Any type of object
	 * Returns:		boolean.
	 * Date:		Aug. 15, 2020
	 */
	public boolean contains(Object obj) {
		// Call the function indexOf and test the returned value
		return indexOf(obj) != -1;
	}

	/**
	 * Method:		toString()
	 * Purpose:		This is a overridden method. It will create a string with the 
	 * Accepts:		Nothing.	
	 * Returns:		String.
	 * Date:		Aug. 16, 2020
	 */
	@Override
	public String toString() {
		if (len == 0) return "[]";
		else {
			// Create a object of StringBuilder class to store each element from the array into this string
			StringBuilder sb = new StringBuilder(len).append("[");
			for (int i = 0; i < this.len - 1; i++) sb.append(this.arr[i] + ", ");
			return sb.append(this.arr[this.len - 1] + "]").toString();
		}
	}
	

	/**
	 * Method:		iterator()
	 * Purpose:		This is a overridden method.
	 * Accepts:		Nothing
	 * Returns:		
	 * Date:		Aug. 15, 2020
	 */
	@Override
	public Iterator<T> iterator() {
		return new java.util.Iterator<T>() {
			int idx = 0;
			
			@Override
			public boolean hasNext() {
				return idx < len;
			}
			@Override
			public T next() {
				return arr[idx++];
			}
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
	
	






	
}
 // End class




































//