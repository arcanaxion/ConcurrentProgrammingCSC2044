package Jordan.Ex1;


public class Converter implements Runnable{
	private int[][] workingArray;
	private int[][] tempArray;
	
	public Converter(int[][] workingArray){
		this.workingArray = workingArray;
		tempArray = workingArray;
	}
	
	public void run() {
		for (int i = 0; i < tempArray.length; i++) {
			for(int j = 0; j < tempArray[i].length; j++) {
				if (tempArray[i][j] % 2 == 0) {
					tempArray[i][j] = 0;
				} else {
					tempArray[i][j] = 1;
				}
			}
		}
	}
	
	public int[][] getResult() {
		return tempArray;
	}
}
