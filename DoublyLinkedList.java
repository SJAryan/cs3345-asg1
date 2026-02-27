import java.util.Iterator;

public class DoublyLinkedList<T> implements List<T>, Iterable<T> {
	private Node head, tail;
	private int numberOfElements;

	public DoublyLinkedList() {
		head = null;
		tail = null;
		numberOfElements = 0;
	}
	
	@Override
	public void addLast(T item) {
		if(head==null){
			head = new Node(item);
			tail = head;
		} else {
			Node temp = tail; 
			tail = new Node(item);
			tail.previous = temp;
			temp.next = tail;
		}
		numberOfElements++;
			
	}

	@Override
	public void addFirst(T item) {
		if(head!=null){
			Node temp = head; 
			head = new Node(item);
			head.next = temp;
			head.previous = tail;
		} else {
			head = new Node(item);
			tail = head;
		}
		numberOfElements++;
		
			
	}

	@Override
	public T get(int position) {
		Node current = head;
		for(int i = 0; i < position; i++) {
			if(current == null) {
				return null; 
			}
			current = current.next;
		}

		if(current == null) {
			return null; 
		}
		return current.data;
	}

	@Override
	public void print() {
		Node current = head;
		while (current != null) {
			System.out.println(current.data);
			current = current.next;	
		}
		return; 
	}

	@Override
	public void printBackwards() {	
		if (tail == null) {
			System.out.println("List is empty");
			return;
		}
		Node current = tail;
		while (current != null) {
			System.out.println(current.data);
			current = current.previous; 
		}
	}

	@Override
	public boolean remove(T item) {
		if(isEmpty()){
			return false; 
		}
		Node current = head;
		while(current != null) {
			if(current.data.equals(item)) {
				if(current == head) {
					head = head.next;
					if(head != null) {
						head.previous = null;
					}
				} else if(current == tail) {
					tail = tail.previous;
					if(tail != null) {
						tail.next = null;
					}
				} else {
					current.previous.next = current.next;
					current.next.previous = current.previous;
				}
				numberOfElements--;
				return true;
			}
			current = current.next;
		}
	
		return false;
	}

	@Override
	public boolean isEmpty() {
		if(numberOfElements == 0) {
			return true; 
		}
		return false; 
	}

	@Override
	public int getLength() {
		return numberOfElements;
	
	}
	
	/** 
	 * Inner class representing a node in the linked list
	 */

	private class Node
	{
		private T data;
		private Node next, previous;

		private Node(T data) {
			this(data,null,null);
		}

		private Node (T data, Node next, Node prev) {
			this.data = data;
			this.next = next;
			this.previous = prev;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new DoublyLinkedListIterator();
	}

	private class DoublyLinkedListIterator implements Iterator<T> {
		private Node current;

		public DoublyLinkedListIterator() {
			current = head;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			T data = current.data;
			current = current.next;
			return data;
		}
		
	}


}

