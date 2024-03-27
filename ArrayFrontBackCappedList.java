package projectAarray;

import java.lang.reflect.Array;
import java.util.*;

/**
 *@purpose: This file implements the FrontBackCappedList interface.
 *@author Courtney Poles (AKA C3PO)
*/
public class ArrayFrontBackCappedList<T> implements FrontBackCappedList<T> {

	
	private T[] list;
	private int numberOfElements;
	private int capacity;

	public ArrayFrontBackCappedList(int length) {
		this.capacity = length;
		list = (T[]) new Object[length];
	}

	@Override
	public String toString() {
		String contents = "[";
		for (int i = 0; i < this.size(); i++) {
			if (i + 1 == this.size()) { 
				contents += this.list[i];
			} else {
				contents += this.list[i] + ", ";
			}
		}
		return "size=" + this.size() + "; capacity=" + this.capacity + ";	" + contents + "]";
	}

	public boolean isEmpty() {
		if (this.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isFull() {
		if (this.capacity == this.size()) {
			return true;
		} else {
			return false;
		}
	}
	
	private void decrementSizeByOne() {
		this.numberOfElements -= 1;
	}
	
	private void incrementSizeByOne() {
		this.numberOfElements += 1;
	}

	public int size() {
		return this.numberOfElements;
	}

	private int isValidPosition(int position) {
		if (position >= 0 && position <= this.capacity - 1) {
			return position;
		} else {
			return -1;
		}
	}

	public T getEntry(int position) {
		T result;

		if (isValidPosition(position) == -1) {
			result = null;
			return result;
		} else {
			result = this.list[position];
			return result;

		}
	}

	public void clear() {
		if (this.isEmpty()) {
			return;
		} else {
			for (int i = 0; i < this.size(); i++) {
				this.list[i] = null;

			}
			this.numberOfElements = 0;
		}

	}

	public boolean addBack(T element) {
		boolean result = true;

		if (this.isFull()) {
			result = false;
		} else {
			this.list[this.size()] = element;
			this.incrementSizeByOne();
		}

		return result;

	}

	public boolean addFront(T element) {
		boolean result = true;

		if (this.isFull()) {
			result = false;
		} else if (this.size() == 0) {
			this.list[0] = element;
			this.incrementSizeByOne();

		} else {
			// shift elements over 1 starting from the back since we want to insert in the
			// front
			// >= to 0 because we may have an array that has only 1 value in it :)
			for (int i = this.size() - 1; i >= 0; i--) {
				T val = this.list[i]; // variable of generic type T
				this.list[i + 1] = val;
			}
			this.list[0] = element;
			this.incrementSizeByOne();

		}
		return result;
	}

	public T removeFront() {
		T val;

		if (this.isEmpty()) {
			val = null;
		} else if (this.size() == 1) {
			val = this.list[0];
			this.decrementSizeByOne();
		} else {
			val = this.list[0];

			for (int i = 1; i <= this.size() - 1; i++) {
				this.list[i - 1] = this.list[i];

			}
			this.decrementSizeByOne();
		}

		return val;

	}

	public T removeBack() {
		T val;

		if (this.isEmpty()) {
			val = null;
		} else if (this.size() == 1) {
			val = this.list[0];
			this.list[0] = null;
			this.decrementSizeByOne();

		} else {
			val = this.list[this.size() - 1];
			this.list[this.size() - 1] = null;
			this.decrementSizeByOne();
		}

		return val;
	}

	public boolean contains(T element) {
		boolean result = false;

		if (this.isEmpty()) {

		} else {
			for (int i = 0; i < this.size(); i++) {
				if (this.list[i].equals(element)) {
					result = true;
					break;
				}

			}

		}
		return result;
	}

	public int indexOf(T element) {
		int result = -1;

		if (this.isEmpty()) {

		} else if (this.size() == 1 && this.list[0].equals(element)) {
			result = 0;

		} else {

			for (int i = 0; i < this.size(); i++) {
				if (this.list[i].equals(element)) {
					result = i;
					break;
				}

			}
		}
		return result;
	}

	public int lastIndexOf(T element) {
		int result = -1;

		if (this.isEmpty()) {

		} else if (this.size() == 1 && this.list[0].equals(element)) {
			result = 0;

		} else {

			for (int i = 0; i < this.size(); i++) {
				if (this.list[i].equals(element)) {
					result = i;
				}
			}
		}
		return result;
	}

}
