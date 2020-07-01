package Jordan.Ex2;

import java.util.concurrent.Callable;

public class findMax2 implements Callable {
	int[] array;
	
	public findMax2 (int[] array) {
		this.array = array;
	}
	
	@Override
	public Object call() throws Exception {
		int max = 0;
		for (int element: array) {
			if (element > max) {
				max = element;
			}
		}		
		return max;
	}

}
