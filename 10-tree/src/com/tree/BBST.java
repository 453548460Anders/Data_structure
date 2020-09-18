package com.tree;

import java.util.Comparator;

public class BBST<E> extends BST<E> {

	public BBST() {
		this(null);
	}

	public BBST(Comparator<E> comparator) {
		super(comparator);
	}

	/**
	 * left旋转
	 * 
	 * @param grand
	 */
	protected void rotateLeft(Node<E> grand) {
		System.out.println("rotateLeft");
		System.out.println(grand);
		Node<E> parent = grand.right;
		Node<E> child = parent.left;

		grand.right = child;
		parent.left = grand;

		afterRotate(grand, parent, child);
	}

	/**
	 * right旋转
	 * 
	 * @param grand
	 */
	protected void rotateRight(Node<E> grand) {
		Node<E> parent = grand.left;
		Node<E> child = parent.right;

		grand.left = child;
		parent.right = grand;

		afterRotate(grand, parent, child);
	}

	/**
	 * 更新parent
	 * 
	 * @param grand
	 */
	protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
		// 让parent称为子树的根节点
		parent.parent = grand.parent;
		if (grand.isLeftChild()) {
			grand.parent.left = parent;
		} else if (grand.isRightChild()) {
			grand.parent.right = parent;
		} else {
			root = parent;
		}

		// 更新child的parent
		if (child != null) {
			child.parent = grand;
		}

		// 更新grand的parent
		grand.parent = parent;

	}

	/**
	 * 旋转
	 * 
	 * @param grand
	 */
	protected void rotate(Node<E> r, Node<E> a, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f, Node<E> g) {
		d.parent = r.parent;
		if (r.isLeftChild()) {
			r.parent.left = d;
		} else if (r.isRightChild()) {
			r.parent.right = d;
		} else {
			root = d;
		}

		// a-b-c
		b.left = a;
		if (a != null) {
			a.parent = b;
		}

		b.right = c;
		if (c != null) {
			c.parent = b;
		}

		// e-f-g
		f.left = e;
		if (e != null) {
			e.parent = f;
		}

		f.right = g;
		if (g != null) {
			g.parent = f;
		}

		d.left = b;
		d.right = f;
		b.parent = b;
		f.parent = b;
	}
}
