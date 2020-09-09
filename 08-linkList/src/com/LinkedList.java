package com;

public class LinkedList<E> extends AbstractList<E>{

	private Node<E> first;
	
	private static class Node<E> {		
		E element;
		Node<E> next;
		public Node(E element, Node<E> next) {
			// TODO Auto-generated constructor stub
			this.element = element;
			this.next = next;
		}
	}

	@Override
	public void clear() {
		size = 0;
		first = null;
	}

	@Override
	public E get(int index) {
		return node(index).element;
	}

	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		Node<E> node = node(index);
		E old = node.element; 
		node.element = element;
		return old;
	}

	@Override
	public void add(int index, E element) {
		
		if (index == 0) {
			first = new Node<>(element, first);
		} else {
			Node<E> prevNode = node(index - 1);
			Node<E> addNode = new Node<>(element, prevNode.next);
			prevNode.next = addNode;
		}
		
		size++;
	}

	@Override
	public E remove(int index) {
		Node<E> node = first;
		if (index == 0) {
			first = first.next;
		} else {
			Node<E> prevNode = node(index - 1);
			node = prevNode.next;
			prevNode.next = node.next;
		}
		size--;
		return node.element;
	}

	@Override
	public int indexOf(E element) {
		if (element == null) { // 允许数组中传入null
			Node<E> node = first;
			for (int i = 0; i < size; i++) {
				if (node.element == null) return i;
				node = node.next;
			}
		}else {
			Node<E> node = first;
			for (int i = 0; i < size; i++) {
				if (node.element.equals(element)) return i;
				node = node.next;
			}
		}
		
		return ELEMENT_NOT_FOUND;
	}
	
	private Node<E> node(int index) {
		rangeCheck(index);
		Node<E> node = first;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node;
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("size=").append(size).append(", [");
		Node<E> node = first;
		
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				string.append(", ");
			}
			string.append(node.element);			
			node = node.next;
		}
		string.append("]");
		return string.toString();
	}
	
}
