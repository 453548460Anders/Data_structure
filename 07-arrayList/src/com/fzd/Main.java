package com.fzd;
import com.fzd.TimeTool.Task;

public class Main {

	/*
	 * 0 1 1 2 3 5 8 13 ...
	 * */
	public static int fib1(int n) {
		if (n <= 1)	return n;
		return fib1(n-1) + fib1(n-2);
	}
	
	public static int fib2(int n) {
		if (n <= 1)	return n;
		int first = 0;
		int second = 1;
		
		for (int i = 0; i < n-1; i++) {
			int sum = first + second;
			first = second;
			second = sum;
		}
		return second;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		
		int n = 35;
		TimeTool.check("fib1", new Task() {
			@Override
			public void execute() {
				System.out.println(fib1(n));
			}
		});
		
		TimeTool.check("fib2", new Task() {
			@Override
			public void execute() {
				System.out.println(fib2(n));
			}
		});
	}
}
