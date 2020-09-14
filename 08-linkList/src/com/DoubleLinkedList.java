package com;

public class DoubleLinkedList<E> extends AbstractList<E>{

	private Node<E> first;
	private Node<E> last;
	
	private static class Node<E> {		
		E element;
		Node<E> prev;
		Node<E> next;
		public Node(E element, Node<E> prev, Node<E> next) {
			this.element = element;
			this.prev = prev;
			this.next = next;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			if (prev != null) {
				sb.append(prev.element);
			}else {
				sb.append("null");
			}
			sb.append("_").append(element).append("_");
			if (next != null) {
				sb.append(next.element);
			}else {
				sb.append("null");
			}
			return sb.toString();
		}
	}

	@Override
	public void clear() {
		size = 0;
		first = null;
		last = null;
	}

	@Override
	public E get(int index) {
		return node(index).element;
	}

	@Override
	public E set(int index, E element) {
		Node<E> node = node(index);
		E old = node.element; 
		node.element = element;
		return old;
	}

	@Override
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		
		if (index == size) { // 向最后添加元素
			Node<E> prev = last;
			last = new Node<>(element, prev, null);
			if (prev == null) { // 如果是链表的第一个元素
				first = last;
			}else {
				prev.next = last;
			}
		}else {
			Node<E> next = node(index);
			Node<E> prev = next.prev;
			Node<E> node = new Node<>(element, prev, next);
			next.prev = node;
			
			if (prev == null) { // index == 0
				first = node;
			}else {
				prev.next = node;
			}
		}
		
		size++;
	}

	@Override
	public E remove(int index) {
		rangeCheck(index);
		
		Node<E> node = node(index);
		Node<E> prev = node.prev;
		Node<E> next = node.next;
		
		if (prev == null) {
			first = next;
		}else {
			prev.next = next;
		}
		
		if (next == null) {
			last = prev;
		}else {
			next.prev = prev;
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
		
		if (index < (size >> 1)) { // 索引<size一半, 在左边
			Node<E> node = first;
			for (int i = 0; i < index; i++) {
				node = node.next;
			}
			return node;
		}else {
			Node<E> node = last;
			for (int i = size - 1; i > index; i--) {
				node = node.prev;
			}
			return node;
		}
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
			string.append(node);			
			node = node.next;
		}
		string.append("]");
		return string.toString();
	}
	
}
