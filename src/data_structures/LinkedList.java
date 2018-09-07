package data_structures;
/*
 * Gerardo Lopez
 * This class implements the interface UnorderedListADT. 
 * The body of this class contains insertion and deletion methods 
 * used on an unsorted linked list containing generic objects.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class LinkedList<E extends Comparable<E>> 
						  implements UnorderedListADT<E>
{
	private class Node<E> 
	{
		E data;
		Node<E> next;
		
		public Node(E data)
		{
			this.data = data;
			next = null;
		}
	}
	
	private Node<E> head, tail;
	private int currSizeList;
	private long modificationCounter;
	
	public LinkedList()
	{
		head = tail = null;
		currSizeList = 0;
		modificationCounter = 0;
	}
	
	//  Adds the Object obj to the list in first position.
	@Override
	public void addFirst(E obj) 
	{
		Node<E> newNode = new Node<E>(obj);
		if(head == null)
		{
			head = tail = newNode;
		}
		else
		{
			newNode.next = head;
			head = newNode;
		}
		currSizeList++;
		modificationCounter++;
	}
	
	//  Adds the Object obj to the end of the list.
	@Override
	public void addLast(E obj) 
	{
		Node<E> newNode = new Node<E>(obj);
		if(head == null)
		{
			head = tail = newNode;
		}
		else
		{
			tail.next = newNode;
			tail = newNode;
		}
		currSizeList++;
		modificationCounter++;
	}
	
	//  Adds the Object obj to the list in the position indicated.  
	//  The list is one based, and the first element is at position 
	//  #1 (not zero).  If the position is currently occupied
	//  existing elements must be shifted over to make room for the 
	//  insertion.
	@Override
	public void add(E obj, int position) 
	{
		Node<E> newNode = new Node<E>(obj);
		Node<E> prevNode = null, currNode = head;
		int currPosition = 0;
		while(currPosition < position - 1 && currNode != null)
		{
			prevNode = currNode;
			currNode = currNode.next;
			currPosition++;
		}
		if(prevNode == null)
		{
			newNode.next = head;
			head = newNode;
		}
		else
		{
			prevNode.next = newNode;
			newNode.next = currNode;
		}
		currSizeList++;
		modificationCounter++;
	}

	//  Removes and returns the object located at the parameter 
	//  position. Throws a RuntimeException if the position 
	//  does not map to a valid position within the list.
	@Override
	public E remove(int position) 
	{
		if(position < 0 || position > currSizeList)
		{
			throw new RuntimeException("Invalid position");
		}
		Node<E> prevNode = null, currNode = head;
		int currPosition = 0;
		while(currPosition < position - 1 && currNode != null)
		{
			prevNode = currNode;
			currNode = currNode.next;
			currPosition++;
		}
		if(currNode == null)
		{
			return null;
		}
		if (prevNode == null)
		{
			head = head.next;
		}
		else
		{
			prevNode.next = currNode.next;
		}
		currSizeList--;
		modificationCounter++;
		return currNode.data;
	}

	//  Removes and returns the parameter object obj from the list 
	//  if the list contains it, null otherwise.  If more than one 
	//  element matches, the element is lowest position is removed
	//  and returned.
	@Override
	public E remove(E obj) 
	{
		Node<E> currNode = head, prevNode = null;
		while(currNode != null && 
			 ((Comparable<E>)obj).compareTo(currNode.data) != 0)
		{
			prevNode = currNode;
			currNode = currNode.next;
		}
		if(currNode == null)
		{
			return null;
		}
		if(prevNode == null)
		{
			head = head.next;
			if(head == null)
			{
				tail = null;
			}
		}
		else if(currNode == tail)
		{
			prevNode.next = null;
			tail = prevNode;
		}
		else 
		{
			prevNode.next = currNode.next;
		}
		currSizeList--;
		modificationCounter++;
		return currNode.data;
	}

	//  Removes and returns the first element in the list and null 
	//  if the list is empty.
	@Override
	public E removeFirst() 
	{
		if (head == null)
		{
			return null;
		}
		E temp = head.data;
		head = head.next;
		currSizeList--;
		modificationCounter++;
		return temp;
	}

	//  Removes and returns the last element in the list and null 
	//  if the it is empty.
	@Override
	public E removeLast() 
	{
		Node<E> prevNode = null;
		Node<E> currNode = head;
		if(head == null)
		{
			return null;
		}
		while(currNode != tail)
		{
			prevNode = currNode;
			currNode = currNode.next;
		}
		if(prevNode == null)
		{
			tail = head = null;
		}
		else 
		{
			prevNode.next = null;
			tail = prevNode;
		}
		currSizeList--;
		modificationCounter++;
		return currNode.data;
	}

	//  Returns the object located at the parameter position.
	//  Throws a RuntimeException if the position does not map 
	//  to a valid position within the list.
	@Override
	public E get(int position) 
	{
		if(position < 0 || position > currSizeList)
		{
			throw new RuntimeException("Invalid position");
		}
		Node<E> currNode = head;
		int currPosition = 0;
		while(currPosition < position - 1 && currNode != null)
		{
			currNode = currNode.next;
			currPosition++;
		}
		if(currNode == null)
		{
			return null;
		}
		
		return currNode.data;
	}

	//  Returns the list object that matches the parameter, and 
	//  null if the list is empty or if the element is not in 
	//  the list.  If obj matches more than one element, the 
	//  element with the lowest position is returned.
	@Override
	public E get(E obj) 
	{
		Node<E> currNode = head;
		while(currNode != null && 
			 ((Comparable<E>)obj).compareTo(currNode.data) != 0)
		{
			currNode = currNode.next;
		}
		if(currNode == null)
		{
			return null;
		}
		return currNode.data;
	}

	//  Returns the position of the first element that matches
	// the parameter obj and -1 if the item is not in the list.
	@Override
	public int find(E obj) 
	{
		Node<E> currNode = head;
		int currPosition = 1;
		while(currNode != null && 
			 ((Comparable<E>)obj).compareTo(currNode.data) != 0)
		{
			currNode = currNode.next;
			currPosition++;
		}
		if(currNode == null)
		{
			return -1;
		}
		return currPosition;
	}

	//  Returns true if the parameter object obj is in the list, 
	//  false otherwise.
	@Override
	public boolean contains(E obj) 
	{
		Node<E> currNode = head;
		while(currNode != null && 
			 ((Comparable<E>)obj).compareTo(currNode.data) != 0)
		{
			currNode = currNode.next;
		}
		if(currNode == null)
		{
			return false;	
		}
		return true;
	}

	//  The list is returned to an empty state.
	@Override
	public void clear() 
	{
		head = tail = null;
		currSizeList = 0;
		modificationCounter++;
	}

	//  Returns true if the list is empty, otherwise false
	@Override
	public boolean isEmpty() 
	{
		return head == null;
	}

	//  Returns true if the list is full, otherwise false.  
	@Override
	public boolean isFull() 
	{
		return false;
	}

	//  Returns the number of Objects currently in the list.
	@Override
	public int size() 
	{
		return currSizeList;
	}

	//  Returns an Iterator of the values in the list, presented in
	//  the same order as the list.  The list iterator is
	//  fail-fast.
	@Override
	public Iterator<E> iterator() 
	{
		return new IteratorHelper();
	}
	
	class IteratorHelper implements Iterator<E> 
	{
		private long stateCheck;
		private Node<E> currNode;
		
		public IteratorHelper()
		{
			currNode = head;
			stateCheck = modificationCounter;
		}
		public boolean hasNext()
		{
			if(stateCheck != modificationCounter)
			{
				throw new ConcurrentModificationException();
			}
			return currNode != null;
		}
		public E next()
		{
			if(!hasNext())
			{
				throw new NoSuchElementException();
			}
			E tmp = currNode.data;
			currNode = currNode.next;
			return tmp;
		}
		public void remove()
		{
			throw new UnsupportedOperationException();
		}	
	}
}
