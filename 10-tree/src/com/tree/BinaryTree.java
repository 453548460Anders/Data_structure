package com.tree;

import java.util.LinkedList;
import java.util.Queue;
import com.printer.BinaryTreeInfo;

/**
 * 重构后的二叉搜索树
 * @author anderson
 *
 * @param <E>
 */
@SuppressWarnings("unchecked")
public class BinaryTree<E> implements BinaryTreeInfo {

	protected int size;
	protected Node<E> root;
	
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
	
	public static interface Visitor<E> {
		void visit(E element);
	}
	
	protected static class Node<E> {
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
		
		public boolean isLeftChild() {
			return parent != null && this == parent.left;
		}
		
		public boolean isRightChild() {
			return parent != null && this == parent.right;
		}

		public Node<E> sibling() {
			if (isLeftChild()) {
				return parent.right;
			}
			
			if (isRightChild()) {
				return parent.left;
			}
			
			return null;
		}
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
	 * 创建节点
	 */
	protected Node<E> createNode(E element, Node<E> parent) {
		return new Node<>(element, parent);
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
		return node;
	}
}
