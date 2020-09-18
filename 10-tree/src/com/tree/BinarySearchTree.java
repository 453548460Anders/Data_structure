package com.tree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

import com.printer.BinaryTreeInfo;

public class BinarySearchTree<E> implements BinaryTreeInfo {
	private int size;
	private Node<E> root;
	
	private Comparator<E> comparator;
	
	public BinarySearchTree() {
		this(null);
	}
	public BinarySearchTree(Comparator<E> comparator) {
		this.comparator = comparator;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		root = null;
		size = 0;
	}
	
	public void add(E element) {
		elementNotNullCheck(element);
		if (root == null) { // 添加的是根节点
			root = new Node<>(element, null);
			size++;
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
		
		Node<E> addNode = new Node<>(element, parent);
		if (res > 0) {
			parent.right = addNode;
		}else {
			parent.left = addNode;
		}
		size++;
	}
	
	public void remove(E element) {
		remove(node(element));
	}
	
	private void remove(Node<E> node) {
		if (node == null) {
			return;
		}
		
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
		} else if (node.parent == null) { // node是叶子节点, 并且是根节点
			root = null;
		} else { // node是叶子节点, 并且不是根节点
			if (node.parent.left == node) { // 判断node是左面
				node.parent.left = null;
			}else {
				node.parent.right = null;
			}
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
	
	public static interface Visitor<E> {
		void visit(E element);
	}
	
	private static class Node<E> {
		E element;
		Node<E> left;
		Node<E> right;
		Node<E> parent;
		
		public Node(E element, Node<E> parent) {
			this.element = element;
			this.parent = parent;
		}
		
		public boolean isLeaf() {
			return left == null && right == null;
		}
		
		public boolean hasTwoChildren() {
			return left != null && right != null;
		}
	}
	
	@Override
	public Object root() {
		return root;
	}

	@Override
	public Object left(Object node) {
		return ((Node<E>)node).left;
	}

	@Override
	public Object right(Object node) {
		return ((Node<E>)node).right;
	}

	@Override
	public Object string(Object node) {
		Node<E> myNode = (Node<E>)node;
		String parentString = "null";
		if (myNode.parent != null) {
			parentString = myNode.parent.element.toString();
		}
		return myNode.element + "_p(" + parentString + ")";
	}
	
	/**
	 * 前序遍历
	 * @param node
	 * @return
	 */
	public void preorderTraversal(Visitor<E> visitor) {
		preorderTraversal(root, visitor);
	}
	public void preorderTraversal(Node<E> node, Visitor<E> visitor) {
		if (node == null) return;
		System.out.println(node.element);
		preorderTraversal(node.left, visitor);
		preorderTraversal(node.right, visitor);
	}

	/**
	 * 中序遍历
	 * @param node
	 * @return
	 */
	public void inOrderTraversal(Visitor<E> visitor) {
		inOrderTraversal(root, visitor);
	}
	public void inOrderTraversal(Node<E> node, Visitor<E> visitor) {
		if (node == null) return;
		inOrderTraversal(node.left, visitor);
		visitor.visit(node.element);
		inOrderTraversal(node.right, visitor);
	}
	
	/**
	 * 后序遍历
	 * @param node
	 * @return
	 */
	public void postOrderTraversal(Visitor<E> visitor) {
		postOrderTraversal(root, visitor);
	}
	public void postOrderTraversal(Node<E> node, Visitor<E> visitor) {
		if (node == null) return;
		postOrderTraversal(node.left, visitor);
		postOrderTraversal(node.right, visitor);
		visitor.visit(node.element);
	}
	
	/**
	 * 层序遍历
	 * @param node
	 * @return
	 */
	public void levelOrderTraversal(Visitor<E> visitor) {
		if (root == null) return;
		Queue<Node> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			visitor.visit(node.element);
			if (node.left != null) {
				queue.offer(node.left);
			}
			if (node.right != null) {
				queue.offer(node.right);
			}			
		}
	}
	
	/**
	 * 当前二叉树的高度
	 * @param node
	 * @return
	 */
	public int height() {
		if (root == null) return 0;
		int h = 0;
		int levelSize = 1;
		Queue<Node> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			levelSize--;
			if (node.left != null) {
				queue.offer(node.left);
			}
			if (node.right != null) {
				queue.offer(node.right);
			}
			if (levelSize == 0) {
				levelSize = queue.size();
				h++;
			}
		}
		return h;
	}

	public int height2() {
		height2(root);
		return 0;
	}
	
	public int height2(Node<E> node) {
		if (node == null) return 0;
		int h = 1 + Math.max(height2(node.left), height2(node.right));
		return h;
	}
	
	/**
	 * 判断当前二叉树是否是一个完全二叉树
	 * @return
	 */
	public boolean isComplete() {
		if (root == null) return false;

		Queue<Node> queue = new LinkedList<>();
		queue.offer(root);
		boolean leaf = false;
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			if (leaf && !node.isLeaf()) return false;
			if (node.hasTwoChildren()) {
				queue.offer(node.left);
				queue.offer(node.right);
			}else if (node.left == null && node.right != null) {
				return false;
			}else { // 后面遍历的节点必须都是叶子节点
				leaf = true;
			}
		}
		return true;
	}
	
	/**
	 * 前驱节点
	 * @param node
	 * @return
	 */
	protected Node<E> predecessor(Node<E> node) {
		if (node == null) return null;

		Node<E> pNode = node.left;
		if (pNode != null) {
			while (pNode.right != null) {
				pNode = pNode.right;
			}
			return pNode;
		}
		while (node.parent != null && node == node.parent.left) {
			node = node.parent;
		}
		return node.parent;
	}
	
	/**
	 * 后继节点
	 * @param node
	 * @return
	 */
	protected Node<E> successor(Node<E> node) {
		if (node == null) return null;
		Node<E> pNode = node.right;
		if (pNode != null) {
			while (pNode.left != null) {
				pNode = pNode.left;
			}
			return pNode;
		}
		while (node.parent != null && node == node.parent.right) {
			node = node.parent;
		}
		return node.parent;
	}
	
}
