package Solution;

public class Chef implements Runnable {
	private Order order;
	private String name;
	private Waitress waitress;
	
	public Chef(Order order, String name, Waitress waitress) {
		this.order = order;
		this.name = name;
		this.waitress = waitress;
	}
	
	public void run() {
		while(true) {
			System.out.println(this.name + " is waiting for order");
			Boolean cooked = false;
			synchronized (order) {
				try {
					order.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				String orderName = order.getName();
				if (orderName != "no order") {
					System.out.println(this.name + " received the order from the waitress");
					order.setName("no order");
					System.out.println(this.name + " is going to cook " + orderName);						
					cooked = true;					
				}
			}
			
			if (cooked) {			
				order.completeOrder();
				synchronized (waitress) {
					waitress.notify();
				}
			}
		}
	}
}

