package Solution;

import java.util.Scanner;

public class Waitress implements Runnable {
	private Order order;
	private Scanner scanner;
	
	public Waitress(Order order) {
		this.order = order;
		this.scanner = new Scanner(System.in);
	}
	
	public void run() {
		while(true) {	
		System.out.println("Waitress is waiting for 3 seconds for the customer to place order");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
			System.out.println("Please place your order:");
			String newOrder = scanner.nextLine();
			synchronized (order) {
				order.setName(newOrder);
				order.addOrder();
				String orderName = order.getName();
				order.notifyAll();
				System.out.println("Waitress is notifying the chef to cook " + orderName);
			}
			
			synchronized (this) {				
					try {
						this.wait();					
					} catch (InterruptedException e) {
						e.printStackTrace();
					}				
			}
		}
	}
}

