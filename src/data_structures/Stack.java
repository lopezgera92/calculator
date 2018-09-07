package data_structures;
/*
 * Gerardo Lopez
 * This class inherits methods from LinkedList and 
 * implements a stack using a singly linked list.
 */

import java.util.Iterator;

public class Stack<E extends Comparable <E>> implements Iterable<E> {
    private UnorderedListADT<E> list;
	
    public Stack() 
    {
    	list = new LinkedList<E>();
    }
    
    // Adds the Object obj to the first position in the list
    public void push(E obj) 
    {
    	list.addFirst(obj);
    }
    
    //  Removes and returns the first element in the list and null 
    //  if the list is empty.
    public E pop() 
    {
    	return list.removeFirst();
    }
    
    //  Returns the number of Objects currently in the list.
    public int size() 
    {
    	return list.size();
    }
    
    //  Returns true if the list is empty, otherwise false
    public boolean isEmpty() 
    {
    	return list.isEmpty();
    }
    
    //  Returns true if the list is full, otherwise false. 
    public boolean isFull() 
    {
    	return list.isFull();
    }
    
    //  Returns the object located at the first position.
	//  Throws a RuntimeException if the position does not map 
    //  to a valid position within the list.
    public E peek() 
    {
    	return list.get(1);
    }
    
    //  Returns true if the parameter object obj is in the list,
    //  false otherwise.
    public boolean contains(E obj) 
    {
    	return list.contains(obj);
    }
    
    //  The list is returned to an empty state.
    public void makeEmpty() 
    {
    	list.clear();
    }
    
    //  Returns an Iterator of the values in the list, presented in
	//  the same order as the list.  The list iterator is
	//  fail-fast.
    public Iterator<E> iterator() 
    {
    	return list.iterator();
    }                   
}    
