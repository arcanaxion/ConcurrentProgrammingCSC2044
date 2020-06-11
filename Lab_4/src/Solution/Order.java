package Solution;

public class Order {
	private String name;
	private int number = 0;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Order(String order) {
		this.setName(order);
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void completeOrder() {
		this.number-=1;
	}
	
	public void addOrder() {
		this.number+=1;
	}
}

