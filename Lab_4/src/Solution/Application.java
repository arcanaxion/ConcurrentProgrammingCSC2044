package Solution;

public class Application {
	public static void main(String[] args) {
		Order order = new Order("no order");
		Waitress waitress = new Waitress(order);
		Chef chef = new Chef(order, "Chef", waitress);
		
		
		Thread waitressThread = new Thread(waitress);
		Thread chefThread = new Thread(chef);
		
		
		waitressThread.start();
		chefThread.start();
		
	}
}

