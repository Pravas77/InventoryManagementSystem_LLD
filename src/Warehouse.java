import java.util.List;

public class Warehouse {
    int id;
    private List<Product> products;
    private Address address;

    public Warehouse(int id, List<Product> products, Address address) {
        this.id = id;
        this.products = products;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Warehouse{" + "id=" + id + '}';
    }
}
