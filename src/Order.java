import java.util.Map;

public class Order {
    private User user;
    private Map<Product, Integer> products;
    private Warehouse warehouse;
    private int cost;

    public Order(User user, Map<Product, Integer> products, Warehouse warehouse, int cost) {
        this.user = user;
        this.products = products;
        this.warehouse = warehouse;
        this.cost = cost;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Order{" + "user=" + user + ", products=" + products + ", warehouse=" + warehouse + ", cost=" + cost + '}';
    }
}
