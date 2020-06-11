package v4_sleepOnly;

public class Order {
    // attributes
    private String order;

    // constructor
    public Order(String order) {
        setOrder(order);
    }

    public Order() {
        setOrder("no order");
    }

    // setter/getter
    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrder() {
        return this.order;
    }

    // other methods
}
