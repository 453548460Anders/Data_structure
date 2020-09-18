package com.tree;

import java.util.Comparator;

import com.tree.BinaryTree.Node;

@SuppressWarnings("unused")
public class RBTree<E> extends BBST<E> {

	private static final boolean RED = false;
	private static final boolean BLACK = true;

	public RBTree() {
		this(null);
	}

	public RBTree(Comparator<E> comparator) {
		super(comparator);
	}

	private boolean colorOf(Node<E> node) {
		return (node == null) ? BLACK : ((RBNode<E>)node).color;
	}

	/**
	 * 是否为黑色节点
	 * 
	 * @param node
	 * @return
	 */
	private boolean isBlack(Node<E> node) {
		return colorOf(node) == BLACK;
	}

	/**
	 * 是否为红色节点
	 * 
	 * @param node
	 * @return
	 */
	private boolean isRED(Node<E> node) {
		return colorOf(node) == RED;
	}

	/**
	 * 染红
	 * 
	 * @param node 需染色节点
	 * @return
	 */
	private Node<E> red(Node<E> node) {
		return color(node, RED);
	}

	/**
	 * 染黑
	 * 
	 * @param node 需染色节点
	 * @return
	 */
	private Node<E> black(Node<E> node) {
		return color(node, BLACK);
	}

	/**
	 * 给节点染色
	 * 
	 * @param node  需要染色的节点
	 * @param color 颜色
	 * @return
	 */
	private Node<E> color(Node<E> node, boolean color) {
		if (node == null) {
			return node;
		}
		((RBNode<E>) node).color = color;
		return node;
	}

	@Override
	protected void afterAdd(Node<E> node) {
		Node<E> parent = node.parent;

		if (parent == null) { // 添加的是根节点
			black(node);
			return;
		}

		// 1. 如果父节点是黑色, 直接返回
		if (isBlack(parent)) {
			return;
		}

		Node<E> uncle = parent.sibling();
		Node<E> grand = red(parent.parent);

		// 2. 叔父节点是红色
		if (isRED(uncle)) {
			black(parent);
			black(uncle);
			afterAdd(grand);
			return;
		}

		// 3. 叔父节点是黑色
		if (parent.isLeftChild()) {
			if (node.isLeftChild()) { // LL
				black(parent);
			} else { // LR
				black(node);
				rotateLeft(parent);
			}
			rotateRight(grand);
		} else {
			if (node.isLeftChild()) { // RL
				black(node);
				rotateRight(parent);
			} else { // RR
				black(parent);
			}
			rotateLeft(grand);
		}
	}

	@Override
	protected void afterRemove(Node<E> node, Node<E> replacement) {
		// 如果删除的节点是红色
		if (isRED(node)) {
			return;
		}

		// 用以取代node的子节点是红色
		if (isRED(replacement)) {
			black(replacement);
			return;
		}

		// 删除的是 黑色的叶子节点
	}

	@Override
	protected Node<E> createNode(E element, Node<E> parent) {
		return new RBNode<>(element, parent);
	}

	private static class RBNode<E> extends Node<E> {
		boolean color = RED;

		public RBNode(E element, Node<E> parent) {
			super(element, parent);
		}

		@Override
		public String toString() {
			String str = "";
			if (color == RED) {
				str = "R_";
			} else {
				str = "B_";
			}
			return str + element.toString();
		}

	}

}
