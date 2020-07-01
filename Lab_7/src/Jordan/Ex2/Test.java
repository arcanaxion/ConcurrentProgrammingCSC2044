package Jordan.Ex2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Test {
	static ArrayList<int[]> queue = new ArrayList<int[]>();
	
	public static void printArray(int[] array) {
		for (int element: array) {
			System.out.println(element);
		}
	}
	
	public static void splitArray(int[] array, int divisions) {
		int start = 0;
		for (int division = 0; division < divisions; division++) {
			int end = start + array.length / divisions;
			int[] newarray = Arrays.copyOfRange(array, start, end);
			start = end;
			queue.add(newarray);
		}
	}
	public static void findMax(int[] array) {
		int max = 0;
		for (int element: array) {
			if (element > max) {
				max = element;
			}
		}
		
		System.out.println("The maximum value found is: " + max);
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Random rand = new Random();
		int[] array = new int[10000];
		
		for (int i = 0; i < array.length; i++) {
			array[i] = rand.nextInt(256);
		}
		
		printArray(array);
		findMax(array);
		
		splitArray(array, 4);
		
		int divisions = 4;
				
		ExecutorService pool = Executors.newFixedThreadPool(divisions);
		
		FutureTask[] workers = new FutureTask[queue.size()];
		
		int[] combined = new int[queue.size()];
		
		for (int i = 0; i < queue.size(); i++) {
			workers[i] = new FutureTask(new findMax2(queue.get(i)));
			pool.submit(workers[i]);
			combined[i] = (int) workers[i].get();
		}
		
		findMax(combined);
		
		pool.shutdown();
	}
}