package com.tree;

import java.util.Comparator;

@SuppressWarnings("unused")
public class AVLTree<E> extends BBST<E> {
	public AVLTree() {
		this(null);
	}
	
	public AVLTree(Comparator<E> comparator) {
		super(comparator);
	}
	
	/**
	 * 恢复平衡
	 */
	@Override
	protected void afterAdd(Node<E> node) {
		while ((node = node.parent) != null) {
			if (isBalanced(node)) { // node是否平衡
				// 更新高度
				updateHeight(node);
			} else {
				// 恢复平衡
				rebalance(node);
				break;
			}
		}
	}
	
	@Override
	protected void afterRemove(Node<E> node, Node<E> replacement) {
		while ((node = node.parent) != null) {
			if (isBalanced(node)) { // node是否平衡
				// 更新高度
				updateHeight(node);
			} else {
				// 恢复平衡
				rebalance(node);
			}
		}
	}
	
	@Override
	protected Node<E> createNode(E element, Node<E> parent) {
		return new AVLNode<E>(element, parent);
	}
	
	private boolean isBalanced(Node<E> node) {
		return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
	}
	
	private void updateHeight(Node<E> node) {
		AVLNode<E> avlNode = (AVLNode<E>)node;
		avlNode.updateHeight();
	}
	
	private void rebalance(Node<E> grand) {
		Node<E> parent = ((AVLNode<E>)grand).tabllerChild();
		Node<E> node = ((AVLNode<E>)parent).tabllerChild();
		
		if (parent.isLeftChild()) {
			if (node.isLeftChild()) { // LL
				rotateRight(grand);
			} else { //LR
				rotateLeft(parent);
				rotateRight(grand);
			}
		} else {
			if (node.isRightChild()) { // RR
				rotateLeft(grand);
			} else { //RL
				rotateRight(parent);
				rotateLeft(grand);
			}
		}
	}
	
	private void rebalance2(Node<E> grand) {
		Node<E> parent = ((AVLNode<E>)grand).tabllerChild();
		Node<E> node = ((AVLNode<E>)parent).tabllerChild();
		
		if (parent.isLeftChild()) {
			if (node.isLeftChild()) { // LL
				rotate(grand, node.left, node, node.right, parent, parent.right, grand, grand.right);
			} else { //LR
				rotate(grand, parent.left, parent, node.left, node, node.right, grand, grand.right);
			}
		} else {
			if (node.isRightChild()) { // RR
				rotate(grand, grand.left, grand, parent.left, parent, node.left, node, node.right);
			} else { //RL
				rotate(grand, grand.left, grand, node.left, node, node.right, parent, parent.right);
			}
		}
	}
	
	@Override
	protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
		// TODO Auto-generated method stub
		super.afterRotate(grand, parent, child);

		// 更新高度
		updateHeight(grand);
		updateHeight(parent);
	}
	
	@Override
	protected void rotate(Node<E> r, Node<E> a, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f, Node<E> g) {
		// TODO Auto-generated method stub
		super.rotate(r, a, b, c, d, e, f, g);

		updateHeight(b);
		updateHeight(f);
		updateHeight(d);
	}
	
	private static class AVLNode<E> extends Node<E> {
		int height = 0;
		
		public AVLNode(E element, Node<E> parent) {
			super(element, parent);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public String toString() {
			String parentString = "null";
			if (parent != null) {
				parentString = parent.element.toString();
			}
			return element + "_p(" + parentString + ")_h(" + height + ")";
		}
		
		/**
		 * 平衡因子
		 * @return
		 */
		public int balanceFactor() {
			int leftH = left == null ? 0 : ((AVLNode<E>)left).height;
			int rightH = right == null ? 0 : ((AVLNode<E>)right).height;
			return leftH - rightH;
		}
		
		public void updateHeight() {
			int leftH = left == null ? 0 : ((AVLNode<E>)left).height;
			int rightH = right == null ? 0 : ((AVLNode<E>)right).height;
			height = 1 + Math.max(leftH, rightH);
		}
		
		public Node<E> tabllerChild() {
			int leftH = left == null ? 0 : ((AVLNode<E>)left).height;
			int rightH = right == null ? 0 : ((AVLNode<E>)right).height;
			
			if (leftH > rightH) {
				return left;
			} else if (leftH < rightH) {
				return right;
			} else {
				return isLeftChild() ? left : right;
			}
		}
	}
}
