package v4_sleepOnly;

public class App {
    public static void main(String[] args) {
        Order order = new Order();
        Thread chef = new Thread(new Chef(order));
        chef.setName("Chef1");

        Thread waitress = new Thread(new Waitress(order));
        waitress.setName("Waitress1");

        chef.start();
        waitress.start();
    }
}
