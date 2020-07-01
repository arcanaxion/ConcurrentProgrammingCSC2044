package Jordan.Ex1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.ArrayList;
import java.util.Arrays;

public class Test {
	static ArrayList<int[][]> queue = new ArrayList<int[][]>();
	public static void printArray(int[][] array) {
		for (int[] innerArray: array) {
			for (int data: innerArray) {
				System.out.print(data + " ");
			}
			System.out.print('\n');
		}
	}
	
	public static void splitArray(int[][] array, int divisions) {
		int start = 0;
		for (int division = 0; division < divisions; division++) {
			int end = start + array.length / divisions;
			int[][] newarray = Arrays.copyOfRange(array, start, end);
			start = end;
			queue.add(newarray);
		}
	}
	
	public static void main (String[] args) {
		int[][] array = {
				{74, 17, 13, 74, 53, 17, 60, 26},
				{46, 73, 56, 11, 32, 26, 42, 93},
				{97, 29, 81, 48, 52, 30, 41, 3},
				{4, 64, 13, 12, 91, 27, 46, 78},
				{69, 92, 59, 34, 15, 34, 86, 46},
				{54, 67, 79, 36, 48, 78, 10, 66},
				{85, 17, 66, 87, 87, 43, 77, 64},
				{34, 70, 70, 28, 92, 37, 73, 16}
		};
		System.out.println("Input Matrix:");
		printArray(array);
		System.out.println();
		int divisions = 4;
		splitArray(array, divisions);		
		System.out.println();
		ExecutorService cPool = Executors.newFixedThreadPool(divisions);
		
		Converter c1 = new Converter(queue.get(0));
		Converter c2 = new Converter(queue.get(1));
		Converter c3 = new Converter(queue.get(2));
		Converter c4 = new Converter(queue.get(3));
		
		cPool.execute(c1);
		cPool.execute(c2);
		cPool.execute(c3);
		cPool.execute(c4);

		printArray(c1.getResult());
		printArray(c2.getResult());
		printArray(c3.getResult());
		printArray(c4.getResult());
		
	}
}
