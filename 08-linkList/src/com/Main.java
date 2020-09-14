package com;

import com.circle.CircleLinkedList;

public class Main {
	
	public static void main(String[] args) {
//		List<Integer> list = new LinkedList<Integer>();
		
//		List<Integer> list = new DoubleLinkedList<Integer>();
		
		List<Integer> list = new CircleLinkedList<Integer>();

		

		list.add(20);
		list.add(0, 10);
		list.add(30);
		list.add(list.size(), 40);
		
//		list.add(30);
		list.remove(1);
		
		System.out.println(list);
		
		
		
	}
}
