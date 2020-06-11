package Solution;

public class Application {
	public static void main(String[] args) {
		Order order = new Order("no order");
		Waitress waitress = new Waitress(order);
		Chef chef1 = new Chef(order, "Chef 1", waitress);
		Chef chef2 = new Chef(order, "Chef 2", waitress);
		
		
		Thread waitressThread = new Thread(waitress);
		Thread chefThread1 = new Thread(chef1);
		Thread chefThread2 = new Thread(chef2);

		
		waitressThread.start();
		chefThread1.start();
		chefThread2.start();

	}
}

