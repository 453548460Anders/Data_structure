package com.arr;

public class Main {
	
	public static void main(String[] args) {
		
		ArrayList<Integer> list = new ArrayList<>();
		
		list.add(99);
		list.add(88);
		list.add(77);
		list.add(66);
		list.add(55);
//		list.add(44);
//		list.add(33);
		
//		list.add(1, 100);
//		list.add(list.size(), 101);

		list.set(3, 101);
		Assert.test(list.get(3) == 101);
		
		Assert.test(list.size() == 5);

//		list.remove(0);
		list.remove(4);
		Assert.test(list.size() == 4);
		
		
		System.out.println(list);
	}
}
