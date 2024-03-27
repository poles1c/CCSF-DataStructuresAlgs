package module7Homework;
import java.util.Random;

public class LinkedMultiset<T> implements Multiset<T> {
	
	private Node head;
	private int size;
	
	public LinkedMultiset() {
		head = null; // head is null for an empty set
		size = 0;
	}

	@Override
	public boolean add(T element) {
		if(isEmpty()) {
			head = new Node(element);
		} else {
			Node newNode = new Node(element);
			newNode.next = head;
			head = newNode;
		}
		size++;
		return true;
	}

	@Override
	public T remove() {
		if(isEmpty()) {
			return null;
		} else {
			T removedData = head.data;
			head = head.next;
			size--;
			return removedData;
		}
	}

	@Override
	public boolean remove(T element) {
		Node current = head;
		while(current!=null) {
			if(current.data.equals(element)) {
				current.data = head.data;
				head = head.next;
				size--;
				return true;
			} else {
				current = current.next;
			}
		}
		return false;
	}

//	@Override
//	public int getOccurrencesOf(T element) {
//		int count = 0;
//		Node current = head;
//		while(current!=null) {
//			if(current.data.equals(element)) {
//				count++;
//			}
//			current = current.next;
//		}
//		return count;
//	}
	
	//CP author 3/1//2024
	private int getOccurrencesOfHelper(T element, Node node) {
		int count = 0;
		// set a local variable = to the current node in the chain so as not to accidentally change the value of head
		Node currentNode = node;
		//base case: if we have reached the end of the chain the .next of the last node will be null 
		if (currentNode != null) {
			//if the current node's data is equal to our target element make count = to 1
			if (currentNode.data.equals(element)) {
				count = 1;
			}
			// return count + previous method counts for occurrences identified; remember each activation record will return 
			// it's count, this gives us a rolling sum effect as each method ends and is taken off the runtime stack
			return count + getOccurrencesOfHelper(element,currentNode.next);
		} else {
			// if no occurrences of our target are ound return 0
			// this works for empty and singleton multisets 
			return count;
		}
	}
	
	
	public int getOccurrencesOf(T element) {
		return getOccurrencesOfHelper(element, this.head);
	}
	
	
	@Override
	public boolean contains(T element) {
		Node current = head;
		while(current!=null) {
			if(current.data.equals(element)) {
				return true;
			} else {
				current = current.next;
			}
		}
		return false;	
	}

	@Override
	public boolean isEmpty() {
		return size==0 && head==null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		while(head!=null) {
			head.data = null;
			head = head.next;
		}
		size = 0;
		
	}
	
	@Override
	public String toString() {
		String s = "[";
		Node currentNode = head;
		while(currentNode!=null) {
			s += currentNode.data + ", ";
			currentNode = currentNode.next;
		}
		if(!isEmpty()) {
			s = s.substring(0, s.length()-2);
		}
		s += "]";
		return s;
	}
	
	public T randomRemove( ) {
		if(isEmpty()) {
			return null;
		}
		Random generator = new Random();
		int randomIndex = generator.nextInt(size); // returns a random number from 0 (inclusive) to size (exclusive)
		
		Node current = head;
		for(int i=0; i<randomIndex; i++) {
			current = current.next;
		}
		T removedData = current.data;

		// removeNode(current); // invoke this instead of the line below if you want to use the practice version of removing the actual ndoe
		remove(removedData); // technically removes the first appearance of the randomly selected data
		
		return removedData;
		
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof LinkedMultiset<?>) {
			LinkedMultiset<T> otherSet = (LinkedMultiset<T>) obj;
			
			if(this.size!=otherSet.size) {
				return false;
			}
			
			// make a "copy" of the parameter set
			LinkedMultiset<T> copyOther = new LinkedMultiset<>();
			Node current = otherSet.head;
			while(current!=null) {
				copyOther.add(current.data);
				current = current.next;
			}
			
			current = this.head;
			while(current!=null) {
				T currentData = current.data;
				if(copyOther.remove(currentData)) {
					current = current.next;
				} else {
					return false;
				}
			}
			
			// other set is now empty because we first compared sizes
			
			// the sets have the same contents
			return true;
			
		} else {
			return false;
		}
	}
	
	
	
	private class Node {
		private T data;
		private Node next;
		
		public Node(T data) {
			this(data, null);
		}
		public Node(T data, Node next) {
			this.data = data;
			this.next = next;			
		}
		@Override
		public String toString() {
			return data.toString();
		}
	}
	
}
