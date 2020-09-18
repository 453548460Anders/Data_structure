package com.tree;

import java.util.Comparator;

public class BST<E> extends BinaryTree<E> {
	
	private Comparator<E> comparator;
	
	public BST() {
		this(null);
	}
	public BST(Comparator<E> comparator) {
		this.comparator = comparator;
	}
	
	public void add(E element) {
		elementNotNullCheck(element);
		if (root == null) { // 添加的是根节点
			root = createNode(element, null);
			size++;
			afterAdd(root);
			return;
		}
		
		Node<E> parent = root;
		Node<E> node = root;
		int res = 0;
		while (node != null) {
			res = compare(element, node.element);
			parent = node;
			if (res > 0) {
				node = node.right;
			} else if (res < 0) {
				node = node.left;
			} else {
				node.element = element; // 防止自定义对象重写compare方法, 导致出现的问题
				return;
			}
		}
		
		Node<E> addNode = createNode(element, parent);
		if (res > 0) {
			parent.right = addNode;
		}else {
			parent.left = addNode;
		}
		size++;
		
		afterAdd(addNode);
	}
	
	/**
	 * 添加node之后的调整
	 * @param node 新添的节点
	 */
	protected void afterAdd(Node<E> node) {}
	
	/**
	 * 删除node之后的调整
	 * @param node 新添的节点
	 */
	protected void afterRemove(Node<E> node, Node<E> replacement) {}
	
	public void remove(E element) {
		remove(node(element));
	}
	
	private void remove(Node<E> node) {
		
		if (node == null) return;
		
		size--;
		
		if (node.hasTwoChildren()) { // 度为2的节点
			// 1. 找到后继节点
			Node<E> sNode = successor(node);
			// 2. 用后继节点的值覆盖度为2的节点的值
			node.element = sNode.element;
			// 3. 删除后继节点
			node = sNode;
		}

		// 删除node节点(只剩下度为1和0的节点)
		Node<E> replaceNode = node.left != null ? node.left : node.right;
		if (replaceNode != null) { // node是度为1的节点
			replaceNode.parent = node.parent;
			if (node.parent == null) {
				root = replaceNode;
			}else if (node == node.parent.left){
				node.parent.left = replaceNode;
			}else if (node == node.parent.right) {
				node.parent.right = replaceNode;
			}
			// 要找到真正被删除的节点
			afterRemove(node, replaceNode);
		} else if (node.parent == null) { // node是叶子节点, 并且是根节点
			root = null;
			// 要找到真正被删除的节点
			afterRemove(node, null);
		} else { // node是叶子节点, 并且不是根节点
			if (node.parent.left == node) { // 判断node是左面
				node.parent.left = null;
			}else {
				node.parent.right = null;
			}
			// 要找到真正被删除的节点
			afterRemove(node, null);
		}
		
	}

	/**
	 * 根据 element 返回 Node 
	 * @param element
	 * @return
	 */
	public Node<E> node(E element) {
		Node<E> node = root;
		while (node != null) {
			int cmp = compare(element, node.element);
			if (cmp == 0) {
				return node;
			}else if (cmp > 0) {
				node = node.right;
			}else {
				node = node.left;
			}
		}
		return node;
	}
	
	public boolean contains(E element) {
		return node(element) != null;
	}
	
	private int compare(E e1, E e2) {
		if (comparator != null) {
			return comparator.compare(e1, e2);
		}
		return ((Comparable<E>)e1).compareTo(e2);
	}
	
	private void elementNotNullCheck(E element) {
		if (element == null) {
			throw new IllegalArgumentException("element must not be null");
		}
	}
}
