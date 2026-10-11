import java.util.Objects;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

public class Product {
    private int id;
    private int availableQuantity;
    private int price;
    private StampedLock lock;

    public Product(int id, int availableQuantity, int price) {
        this.id = id;
        this.availableQuantity = availableQuantity;
        this.price = price;
        this.lock = new StampedLock();
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", availableQuantity=" + availableQuantity + ", price=" + price + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public StampedLock getLock() {
        return lock;
    }

    public boolean chekRequiredQuantity(int requiredQuantity) {
        if (requiredQuantity > availableQuantity) return false;
        else return true;
    }

    public boolean checkForBooking(int requiredQuantity) {
        long stamp = lock.tryOptimisticRead();
        if (!chekRequiredQuantity(requiredQuantity)) return false;
        return lock.validate(stamp);

    }

    public void removeProduct(int requiredQuantity) {
        availableQuantity -= requiredQuantity;
    }
}
