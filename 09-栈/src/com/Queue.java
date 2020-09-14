package com;

import com.list.LinkedList;
import com.list.List;

public class Queue<E> {
	
	private List<E> list = new LinkedList<>();
	
	public void clear() {
		list.clear();
	}
	
	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public void enQueue(E element) {
		list.add(element);
	}


	public E deQueue() {
		return list.remove(list.size() - 1);
	}


	public E front() {
		return list.get(0);
	}
}
